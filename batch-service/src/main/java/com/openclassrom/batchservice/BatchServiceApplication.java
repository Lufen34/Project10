package com.openclassrom.batchservice;

import com.openclassrom.batchservice.bookservicebeans.LoanBean;
import com.openclassrom.batchservice.proxy.BookServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootApplication
@EnableFeignClients("com.openclassrom.batchservice")
@EnableScheduling
@EnableEurekaClient
public class BatchServiceApplication implements CommandLineRunner {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private BookServiceProxy bookServiceProxy;

	public static void main(String[] args) {
		SpringApplication.run(BatchServiceApplication.class, args);
	}


	public List<LoanBean> listOfLoanToRemind(){
		List<LoanBean>  loans = bookServiceProxy.getALlLoans();
		List<LoanBean>  loanToRemind = new ArrayList<>();

		for (LoanBean loan : loans) {
			GregorianCalendar expiration = new GregorianCalendar();
			expiration = GregorianCalendar.from(loan.getEnd().toZonedDateTime());
			expiration.roll(Calendar.DAY_OF_MONTH, -3);

			GregorianCalendar init = new GregorianCalendar();

			if (init.getTime().equals(expiration.getTime()))
				loanToRemind.add(loan);
		}
		return loanToRemind;
	}

	@Scheduled(fixedRate = 2000)
	public void ReminderMessage() throws MessagingException { // Il faut Dé-commenter le code dans run et ajouter un param String Email lorsque la demonstration sera fini
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);;
		//SimpleMailMessage mailMessage = new SimpleMailMessage();
		helper.setTo("project7school@outlook.com"); // remplacer avec l'argument EMAIL, ici hardcoded pour la démonstration.
		helper.setSubject("Openclassroom Library Reminder");
		helper.setFrom("project7school@outlook.com");
		helper.setText("Dear user,\n\n" +
				"We would like to remind you that the end of your periode of borrowing is about to reach.\n"+
				"You can choose to extend your loan, in other case you are invited to return the book at the date mentionned in your personnal space.\n\n\n" +
				"Fondly yours, the OPC Library team.", true);
		javaMailSender.send(mailMessage);
	}

	@Override
	public void run(String... args) throws Exception {
		List<LoanBean>  loanToRemind = listOfLoanToRemind();
		System.out.println("Sending Email...");
		/*for (LoanBean loan: loanToRemind) {
			ReminderMessage(loan.getUser().getEmail()); // argument avec le mail
		}*/
		System.out.println("Done");
	}
}