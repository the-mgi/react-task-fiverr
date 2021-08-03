import {applyMiddleware, createStore} from "redux";
import rootReducer from "../reducers"
import {composeWithDevTools} from "redux-devtools-extension";
import thunk from "redux-thunk";

let store = null;

export const getStore = () => {
  if (store === null) {
    return configureStore();
  }
  return store;
};

export const configureStore = () => {
  store = createStore(rootReducer, composeWithDevTools(applyMiddleware(thunk)));
  return store;
}