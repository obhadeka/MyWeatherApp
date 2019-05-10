'use strict';

import {FETCH_FORECAST} from "./types";

export const fetchForecast = (cityName) => dispatch => {
    console.log('fetching...');
    fetch('http://localhost:3002/v1/weather-forecasts?cityName=' + cityName)
        .then(res => res.json())
        .then(forecast => dispatch({
            type: FETCH_FORECAST,
            response: forecast
        }));
};