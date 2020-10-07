package com.openclassroom.bookservice.Repository;

import com.openclassroom.bookservice.Model.Loan;
import com.openclassroom.bookservice.Model.Reserve;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends MongoRepository<Reserve, String> {

    Optional<Reserve> findById(String id);
    List<Reserve> findByUserId(String id);
}
