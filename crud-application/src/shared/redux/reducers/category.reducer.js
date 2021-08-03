import {GET_AND_SAVE_CATEGORIES, POST_BULK_CATEGORIES} from "../actionTypes/category.types";

const INITIAL_STATE = {
  allCategories: [],
  status: ""
}

const categoryReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case GET_AND_SAVE_CATEGORIES:
      return {...state, allCategories: action.payload.data, status: action.payload.status};
    case POST_BULK_CATEGORIES:
      return {...state, allCategories: action.payload.data, status: action.payload.status};
    default:
      return state;
  }
};

export default categoryReducer;