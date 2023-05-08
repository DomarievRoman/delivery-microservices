package com.domariev.identityservice.repository;

import com.domariev.identityservice.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    Optional<PaymentMethod> findByUserId(Long userId);

    List<PaymentMethod> findAllByUserId(Long userId);

}
