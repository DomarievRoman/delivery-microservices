package com.domariev.identityservice.api;

import com.domariev.identityservice.dto.PaymentCardDto;
import com.domariev.identityservice.dto.PaymentMethodDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PaymentMethodApi {

    @PostMapping(value = "/new", produces = {"application/json"})
    ResponseEntity<PaymentMethodDto> addPaymentMethod(@RequestBody @Valid PaymentCardDto paymentCardDto);

    @GetMapping(value = "/all", produces = {"application/json"})
    ResponseEntity<List<PaymentMethodDto>> getAllPaymentMethods();

}
