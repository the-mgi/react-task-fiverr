import axios from "axios";
import {API_BASE_URL, API_STATUSES} from "./constants";
import {getStore} from "./redux/store";

const dispatch = getStore().dispatch

const API = async (apiConfig) => {
  const {url, body, method} = apiConfig;
  const apiUrl = `${API_BASE_URL}${url}`
  let apiResponse;
  try {
    apiResponse = await axios.request({
      method: method || "GET",
      url: apiUrl,
      data: body,
    });

  } catch (error) {

  }
  console.log('apiResponse', apiResponse);

  return apiResponse?.data;
};

export const callApiWithRedux = async ({actionType, apiConfig}) => {
  // api loading starts
  dispatch({
    type: actionType,
    payload: {status: API_STATUSES.LOADING, data: null}
  });

  const apiResponse = await API(apiConfig);

  if (apiResponse) {
    // api data fetch success
    dispatch({
      type: actionType,
      payload: {status: API_STATUSES.SUCCESS, data: apiResponse}
    });
  } else {
    // api data fetch failed
    dispatch({
      type: actionType,
      payload: {status: API_STATUSES.ERROR, data: null}
    });
  }
}

export default API;