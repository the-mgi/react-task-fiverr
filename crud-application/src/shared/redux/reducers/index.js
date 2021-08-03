import {combineReducers} from "redux";
import categoryReducer from "./category.reducer";
import productReducer from "./product.reducer";

export default combineReducers({
  categoryReducer,
  productReducer
});