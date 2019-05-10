'use strict';

import React, {Component} from 'react';
import '../../../styles/App.css';
import {connect} from 'react-redux';
import {fetchForecast} from '../../../actions/forecastActions';
import PropTypes from 'prop-types';

class Weather extends Component {
    constructor(props) {
        super(props);

        this._getForecastsByCity = this._getForecastsByCity.bind(this);
    }

    componentWillMount() {
        this.props.fetchForecast('mountain view');
    }

    render() {
        let city = this.props.forecast.city;
        let forecasts = this.props.forecast.forecasts;
        console.log(this.props.forecast);
        const cities = [
            {label: "Mountain View", value: "mountain view"},
            {label: "Redwood City", value: "redwood city"},
            {label: "Fremont", value: "fremont"},
            {label: "Sunnyvale", value: "sunnyvale"}
        ];
        let options = [];
        cities.map((item, i) => {
            options.push(<option key={i} onClick={this._setSelectedCity} value={item.value}>{item.label}</option>);
        });

        let cityDiv = [];
        if (city) {
            cityDiv.push(<a key = {city.id}>{city.name}, {city.region}</a>);
        }

        let wForecasts = [];
        if (forecasts) {
            forecasts.map(forecast => {
                wForecasts.push(
                    <div className = "day-forecast" key= {forecast.id}>
                        <div><label>Date: </label>{forecast.date}</div>
                        <br/>
                        <div><label>Day: </label>{forecast.day}</div>
                        <br/>
                        <div><label>Current: </label>{forecast.currentCondition}</div>
                        <br/>
                        <div><label>High: </label>{forecast.tempHigh}</div>
                        <br/>
                        <div><label>Low: </label>{forecast.tempLow}</div>
                        <br/>
                        <br/>
                    </div>
                );
            });
        }

        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="col-md-4">
                            <div>
                                <label>Select city:</label>
                            </div>
                            <select onChange={this._getForecastsByCity}>
                                {options}
                            </select>
                        </div>
                        <div>
                            <p>The weather forecast for next 10 days for {cityDiv}</p>
                        </div>
                        {wForecasts}
                    </div>
                </div>
            </div>
        )
    }

    _getForecastsByCity(e) {
        const cityName = e.target.value;
        this.props.fetchForecast(cityName);
    }
}

Weather.propTypes = {
    fetchForecast: PropTypes.func.isRequired,
    forecast: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    forecast: state.forecast.item
});

export default connect(mapStateToProps, { fetchForecast })(Weather);