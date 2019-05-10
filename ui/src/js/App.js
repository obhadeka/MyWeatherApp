'use strict';

import React, {Component} from 'react';
import '../styles/App.css';
import Weather from './WeatherComponent/components/Weather';
import {Provider} from 'react-redux';
import store from '../store';

export default class App extends Component {
    render() {
        return (
            <Provider store={store}>
                <div className="App">
                    <header className="App-header">
                        <h1 className="App-title">Welcome to Weather Forecast</h1>
                    </header>
                    <Weather/>
                </div>
            </Provider>
        )
    }
}