package com.domariev.identityservice.service;

import com.domariev.identityservice.dto.PaymentCardDto;
import com.domariev.identityservice.dto.PaymentMethodDto;
import com.domariev.identityservice.exception.PaymentCardAlreadyExistsException;
import com.domariev.identityservice.mapper.PaymentMethodMapper;
import com.domariev.identityservice.model.PaymentCard;
import com.domariev.identityservice.model.PaymentMethod;
import com.domariev.identityservice.repository.PaymentCardRepository;
import com.domariev.identityservice.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final UserDetailsServiceImpl userDetailsService;
    private final PaymentMethodMapper paymentMethodMapper;

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentCardRepository paymentCardRepository;

    @Transactional
    public PaymentMethodDto createPaymentMethod(PaymentCardDto paymentCardDto, Long userId) {
        PaymentMethod paymentMethod = new PaymentMethod();
        PaymentCard paymentCard = new PaymentCard();
        String number = paymentCardDto.getNumber();
        checkCardExistence(number);
        if (paymentMethodRepository.findByUserId(userId).isEmpty()) {
            paymentMethod.setActive(true);
        }
        paymentCard.setNumber(number);
        paymentCard.setExpirationDate(paymentCardDto.getExpirationDate());
        paymentCard.setCvvCode(Integer.valueOf(paymentCardDto.getCvvCode()));
        paymentCardRepository.save(paymentCard);
        paymentMethod.setPaymentCard(paymentCard);
        paymentMethodRepository.save(paymentMethod);
        userDetailsService.addPaymentMethod(userId, paymentMethod);
        return paymentMethodMapper.modelToDto(paymentMethod);
    }

    public List<PaymentMethodDto> getAllPaymentMethodsByUserId(Long userId) {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAllByUserId(userId);
        return paymentMethods.stream()
                .map(paymentMethodMapper::modelToDto)
                .collect(Collectors.toList());
    }

    private void checkCardExistence(String number) {
        PaymentCard existingCard = paymentCardRepository.findByNumber(number);
        if (Objects.nonNull(existingCard)) {
            throw new PaymentCardAlreadyExistsException("Provided card is already exists");
        }
    }

}
