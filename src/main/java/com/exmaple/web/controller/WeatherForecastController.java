package com.exmaple.web.controller;

import com.exmaple.constants.Errors;
import com.exmaple.entity.ForecastView;
import com.exmaple.entity.WeatherForecast;
import com.exmaple.error.ErrorInfo;
import com.exmaple.exception.ApplicationException;
import com.exmaple.exception.ServiceException;
import com.exmaple.exception.ValidationException;
import com.exmaple.mapper.ForecastMapper;
import com.exmaple.repositoty.database.mysql.model.City;
import com.exmaple.service.CityService;
import com.exmaple.service.ForecastService;
import com.exmaple.util.ValidationUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Controller
public class WeatherForecastController {
    @Autowired
    private CityService cityService;

    @Autowired
    private ForecastService forecastService;

    @Autowired
    private ValidationUtil validationUtil;

    @SuppressWarnings("rawtypes")
    @RequestMapping(
            value = "/v1/weather-forecasts",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public DeferredResult<ResponseEntity> getWeatherForecasts(
            @RequestParam(value = "cityName") final String cityName) {

        DeferredResult<ResponseEntity> response = new DeferredResult<>();
        CompletionStage <City> cityCompletionStage;

        try {
            validationUtil.validateCityName(cityName);
            cityCompletionStage = cityService.getCityByName(cityName);

            cityCompletionStage.whenComplete((city, exception) -> {
                WeatherForecast weatherForecast = new WeatherForecast();
                if (exception != null) {
                    handleResponseWithException(response, exception);
                } else {
                    forecastService.getAllForecastsByCityId(String.valueOf(city.getId()))
                            .whenComplete((forecasts, throwable) -> {
                                if (throwable != null) {
                                    handleResponseWithException(response, throwable);
                                } else {
                                    weatherForecast.setCity(city);
                                    List<ForecastView> forecastViewList = ForecastMapper.convertForecastsToForecastViews(forecasts);
                                    weatherForecast.getForecasts().addAll(forecastViewList);
                                    response.setResult(new ResponseEntity<>(weatherForecast, HttpStatus.OK));
                                }
                            });
                }
            });
        } catch (Exception e) {
            if (e instanceof ValidationException) {
                response.setErrorResult(
                        new ResponseEntity<>(((ValidationException) e).getErrorInfo(), HttpStatus.BAD_REQUEST));
            } else {
                setGenericErrorIntoResponse(response);
            }
        }

        return response;
    }

    /**
     * Extracts error message from throwable and sets it in ResponseEntity along with desired HttpStatus
     *
     * @param response
     * @param throwable
     */
    private void handleResponseWithException(DeferredResult<ResponseEntity> response, Throwable throwable) {
        int applicationExceptionIndex = ExceptionUtils.indexOfType(throwable, ApplicationException.class);
        int serviceExceptionIndex = ExceptionUtils.indexOfType(throwable, ServiceException.class);
        int validationExceptionIndex = ExceptionUtils.indexOfType(throwable, ValidationException.class);
        if (serviceExceptionIndex > -1) {
            ServiceException se = (ServiceException) ExceptionUtils.getThrowableList(throwable).get(serviceExceptionIndex);
            response.setErrorResult(
                    new ResponseEntity<>(se.getErrorInfo(), HttpStatus.INTERNAL_SERVER_ERROR));
        } else if (validationExceptionIndex > -1) {
            ValidationException ve = (ValidationException) ExceptionUtils.getThrowableList(throwable).get(validationExceptionIndex);
            response.setErrorResult(
                    new ResponseEntity<>(ve.getErrorInfo(), HttpStatus.BAD_REQUEST));
        } else if (applicationExceptionIndex > -1) {
            ApplicationException ae = (ApplicationException) ExceptionUtils.getThrowableList(throwable).get(applicationExceptionIndex);
            response.setErrorResult(
                    new ResponseEntity<>(ae.getErrorInfo(), ae.getHttpStatus()));
        } else {
            setGenericErrorIntoResponse(response);
        }
    }

    private void setGenericErrorIntoResponse(DeferredResult<ResponseEntity> response) {
        // Can be moved to CommonUtil
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode(Errors.GENERIC_ERROR.getCode());
        errorInfo.setMessage(Errors.GENERIC_ERROR.getMessage());

        response.setErrorResult(
                new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
