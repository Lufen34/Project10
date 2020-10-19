package com.openclassrom.batchservice;

import com.openclassrom.batchservice.bookservicebeans.LoanBean;
import com.openclassrom.batchservice.proxy.BookServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
@EnableDiscoveryClient
@Controller
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

	@Scheduled(cron = "* * */336 * * ?") // Send every 2 weeks (336hours)
	public void ReminderMessage() throws MessagingException { // Il faut Dé-commenter le code dans run et ajouter un param String Email lorsque la demonstration sera fini
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);;
		//SimpleMailMessage mailMessage = new SimpleMailMessage();
		helper.setTo("lufen34@gmail.com"); // remplacer avec l'argument EMAIL, ici hardcoded pour la démonstration.
		helper.setSubject("Openclassroom Library Reminder");
		helper.setFrom("project7school@outlook.com");
		helper.setText("Dear user,\n\n" +
				"We would like to remind you that the end of your period of borrowing is about to reach.\n"+
				"You can choose to extend your loan, in other case you are invited to return the book at the date mentionned in your personnal space.\n\n\n" +
				"Fondly yours, the OPC Library team.", true);
		javaMailSender.send(mailMessage);
	}

	@PostMapping("sendAcceptMail/")
	public ResponseEntity<String> sendAcceptMail(@RequestBody String email) throws MessagingException {
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);;
		//SimpleMailMessage mailMessage = new SimpleMailMessage();
		helper.setTo(email);
		helper.setSubject("Openclassroom Library Reminder");
		helper.setFrom("project7school@outlook.com");
		helper.setText("Dear user,\n\n" +
				"We would like to announce you that you have 2 open days to accept the requested book.\n"+
				"You can choose to accept your reservation, otherwise you are invited to cancel the reservation of the book in your personnal space.\n\n\n" +
				"Fondly yours, the OPC Library team.", true);
		javaMailSender.send(mailMessage);
		return new ResponseEntity<String>("Email sent successfully.", HttpStatus.OK);
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