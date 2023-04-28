package com.domariev.locationservice.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonConstants {

    public final static String GOOGLE_MAPS_API_KEY = "GOOGLE_MAPS_API_KEY";
    public static final String FULL_ADDRESS_REGEX = "^(?<street>.+),\\s*(?<city>.+),\\s*(?<state>[A-Z]{2})\\s*(?<zip>\\d{5}),\\s*(?<country>.+)$";


}
