package com.openclassroom.bookservice.Service;

import com.openclassroom.bookservice.Model.Books;
import com.openclassroom.bookservice.Model.Reserve;
import com.openclassroom.bookservice.Model.User;
import com.openclassroom.bookservice.Repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReserveRepository reserveRepository;

    public Optional<Reserve> findById(String id) {
        return  reserveRepository.findById(id);
    }

    public List<Reserve> findByUserId(String id) {
        return  reserveRepository.findByUserId(id);
    }

    public void saveReservation(Reserve reserve) {
        reserveRepository.save(reserve);
    }

    public void deleteReservation(Reserve reserve) {
        reserveRepository.delete(reserve);
    }

    public Reserve getReservationByUserAndBook(Books book, User user) {
        return reserveRepository.findByBookIdAndAndUserId(book.getId(), user.getId());
    }

    public void deleteReservationById(String id){
        reserveRepository.deleteById(id);
    }
}
