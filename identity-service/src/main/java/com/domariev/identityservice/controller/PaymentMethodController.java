package com.domariev.identityservice.controller;

import com.domariev.identityservice.api.PaymentMethodApi;
import com.domariev.identityservice.dto.PaymentCardDto;
import com.domariev.identityservice.dto.PaymentMethodDto;
import com.domariev.identityservice.model.User;
import com.domariev.identityservice.service.PaymentMethodService;
import com.domariev.identityservice.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/payment")
@RequiredArgsConstructor
public class PaymentMethodController implements PaymentMethodApi {

    private final PaymentMethodService paymentMethodService;

    @Override
    public ResponseEntity<PaymentMethodDto> addPaymentMethod(PaymentCardDto paymentCardDto) {
        User userDetails = SecurityUtils.getAuthenticatedUserDetails();
        return new ResponseEntity<>(paymentMethodService.createPaymentMethod(paymentCardDto, userDetails.getId()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PaymentMethodDto>> getAllPaymentMethods() {
        User userDetails = SecurityUtils.getAuthenticatedUserDetails();
        return ResponseEntity.ok(paymentMethodService.getAllPaymentMethodsByUserId(userDetails.getId()));
    }

}
