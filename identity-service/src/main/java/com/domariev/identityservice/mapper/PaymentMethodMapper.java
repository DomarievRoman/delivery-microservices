package com.domariev.identityservice.mapper;

import com.domariev.identityservice.dto.PaymentCardDto;
import com.domariev.identityservice.dto.PaymentMethodDto;
import com.domariev.identityservice.model.PaymentCard;
import com.domariev.identityservice.model.PaymentMethod;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMapper {

    public PaymentMethodDto modelToDto(PaymentMethod paymentMethod) {
        ModelMapper mapper = new ModelMapper();
        TypeMap<PaymentMethod, PaymentMethodDto> typeMap = mapper.createTypeMap(PaymentMethod.class, PaymentMethodDto.class);
        Converter<PaymentCard, PaymentCardDto> paymentCardConverter = context -> populatePaymentCard(context.getSource());
        typeMap.addMappings(mapping -> mapping.using(paymentCardConverter)
                .map(PaymentMethod::getPaymentCard, PaymentMethodDto::setPaymentCardDto));
        return mapper.map(paymentMethod, PaymentMethodDto.class);
    }

    private PaymentCardDto populatePaymentCard(PaymentCard paymentCard) {
        return PaymentCardDto.builder()
                .number(paymentCard.getNumber())
                .expirationDate(paymentCard.getExpirationDate())
                .cvvCode(String.valueOf(paymentCard.getCvvCode()))
                .build();
    }


}
