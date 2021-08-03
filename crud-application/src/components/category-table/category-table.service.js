import API, {callApiWithRedux} from "../../shared/api";
import {GET_AND_SAVE_CATEGORIES} from "../../shared/redux/actionTypes/category.types";

export const getAllCategories = async () => {
  return await callApiWithRedux({
    actionType: GET_AND_SAVE_CATEGORIES,
    apiConfig: {url: "category"}
  });
};

export const postBulkCategories = async (allCategories) => {
  return await API({url: "/category/bulk/save", body: allCategories, method: "POST"});
};

export const updateCategory = async (body, categoryId) => {
  return await API({url: `category/${categoryId}`, body, method: "PUT"});
};

export const deleteCategory = async (categoryId) => {
  return await API({url: `category/${categoryId}`, method: "DELETE"});
};

export const postNewCategory = async (body) => {
  return await API({url: "category", method: "POST", body});
};