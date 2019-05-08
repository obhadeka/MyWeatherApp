import {FETCH_FORECAST} from "../actions/types";

const initialState = {
    item: {}
};

export default function (state = initialState, action) {
    switch(action.type) {
        case FETCH_FORECAST:
            return {
                state,
                item: action.response
            };
        default:
            return state;
    }
}