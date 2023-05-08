package com.domariev.identityservice.repository;

import com.domariev.identityservice.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {

    PaymentCard findByNumber(String number);

}
