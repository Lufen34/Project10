package com.openclassroom.bookservice.Controller;

import com.openclassroom.bookservice.Model.BookAndUser;
import com.openclassroom.bookservice.Model.Loan;
import com.openclassroom.bookservice.Model.Reserve;
import com.openclassroom.bookservice.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "library/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("book/reservations/{UserId}")
    public ResponseEntity<List<Reserve>> getReservationByUserId(@PathVariable(value = "UserId") String id) {
        return new ResponseEntity<List<Reserve>>(reservationService.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping("book/reservation/add")
    public void addReservation(@RequestBody Reserve reserve) {
        reservationService.saveReservation(reserve);
    }

    @PostMapping("/book/reservation/{id}")
    public ResponseEntity<Reserve> getReservation(@PathVariable("id") String id){
        Optional<Reserve> reserve = reservationService.findById(id);
        return new ResponseEntity<Reserve>(reserve.get(), HttpStatus.OK);
    }

    @PostMapping("book/reservation/delete")
    public ResponseEntity<String> deleteReservation(@RequestBody Reserve reserve) {
        reservationService.deleteReservation(reserve);
        return new ResponseEntity<String>("Reservation successfully deleted.", HttpStatus.OK);
    }

    @PostMapping("book/reservation/")
    public ResponseEntity<Reserve> getReservationByBookAndUser(@RequestBody BookAndUser bookAndUser) {
        return new ResponseEntity<Reserve>(reservationService.getReservationByUserAndBook(bookAndUser.getBook(), bookAndUser.getUser()), HttpStatus.OK);
    }
}
