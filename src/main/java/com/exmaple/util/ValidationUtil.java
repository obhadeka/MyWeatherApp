package com.exmaple.util;

import com.exmaple.constants.Errors;
import com.exmaple.constants.WeatherAppConstants;
import com.exmaple.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {
    public void validateCityName(String cityName) throws ValidationException {
        if (StringUtils.isBlank(cityName)) {
            throw new ValidationException(Errors.CITY_NAME_BLANK);
        } else {
            validateForPrintableAsciiChars(WeatherAppConstants.QUERY_PARAM_CITY_NAME, cityName);
            validateQueryParamMaxLength(cityName, WeatherAppConstants.QUERY_PARAM_CITY_NAME_MAX_LENGTH);
        }
    }

    private void validateForPrintableAsciiChars(String key, String value) {
        if (key != null && !StringUtils.isAsciiPrintable(key)) {
            throw new ValidationException(Errors.QUERY_PARAM_CONTAINS_NON_PRINTABLE_ASCII);
        } else if (!StringUtils.isAsciiPrintable(value)) {
            throw new ValidationException(Errors.QUERY_PARAM_CONTAINS_NON_PRINTABLE_ASCII);
        }
    }

    private void validateQueryParamMaxLength(String value, int allowedMaxLength) {
        if (value.length() > allowedMaxLength) {
            throw new ValidationException(Errors.QUERY_PARAM_INVALID_LENGTH);
        }
    }
}
