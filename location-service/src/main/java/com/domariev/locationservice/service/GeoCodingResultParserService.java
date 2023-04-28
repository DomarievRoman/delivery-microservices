package com.domariev.locationservice.service;

import com.domariev.locationservice.dto.AddressDto;
import com.google.maps.model.GeocodingResult;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.domariev.locationservice.constants.CommonConstants.FULL_ADDRESS_REGEX;

@Service
public class GeoCodingResultParserService {

    private final static Pattern ADDRESS_PATTERN = Pattern.compile(FULL_ADDRESS_REGEX);

    public AddressDto parseToAddressDto(GeocodingResult geocodingResult) {
        AddressDto addressDto = new AddressDto();
        String fullAddress = geocodingResult.formattedAddress;
        addressDto.setFullAddress(fullAddress);
        Matcher matcher = ADDRESS_PATTERN.matcher(fullAddress);
        if (matcher.find()) {
            addressDto.setCity(matcher.group("city"));
            addressDto.setState(matcher.group("state"));
            addressDto.setZipCode(matcher.group("zip"));
        }
        return addressDto;
    }

}
