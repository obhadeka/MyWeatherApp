package com.example.util;

import com.exmaple.constants.Errors;
import com.exmaple.constants.WeatherAppConstants;
import com.exmaple.exception.ValidationException;
import com.exmaple.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.fail;

public class ValidationUtilTest {
    @InjectMocks
    private ValidationUtil validationUtil;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * testValidateCityName
     */
    @Test
    public void testValidateCityName_CityNameNull_ThrowsException() throws Exception {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(Errors.CITY_NAME_BLANK.getMessage());
        expectedException.expectMessage(Errors.CITY_NAME_BLANK.getCode());
        validationUtil.validateCityName(null);
        // Fail if exception is not thrown
        fail();
    }

    @Test
    public void testValidateCityName_CityNameEmptyString_ThrowsException() throws Exception {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(Errors.CITY_NAME_BLANK.getMessage());
        expectedException.expectMessage(Errors.CITY_NAME_BLANK.getCode());
        validationUtil.validateCityName("");
        // Fail if exception is not thrown
        fail();
    }

    @Test
    public void testValidateCityName_CityNameContainsASCIIChar_ThrowsException() throws Exception {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(Errors.QUERY_PARAM_CONTAINS_NON_PRINTABLE_ASCII.getMessage());
        expectedException.expectMessage(Errors.QUERY_PARAM_CONTAINS_NON_PRINTABLE_ASCII.getCode());
        validationUtil.validateCityName("∫");
        // Fail if exception is not thrown
        fail();
    }

    @Test
    public void testValidateCityName_CityNameExceedsMaxLength_ThrowsException() throws Exception {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(Errors.QUERY_PARAM_INVALID_LENGTH.getMessage());
        expectedException.expectMessage(Errors.QUERY_PARAM_INVALID_LENGTH.getCode());
        validationUtil.validateCityName(getAlphaNumericString(WeatherAppConstants.QUERY_PARAM_CITY_NAME_MAX_LENGTH + 1));
        // Fail if exception is not thrown
        fail();
    }

    private String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
