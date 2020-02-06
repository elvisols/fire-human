import axios from "axios";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import setJWTToken from "../security/setJWTToken";
import jwt_decode from "jwt-decode";

// export const createNewUser = (newUser, history) => async dispatch => {
//   try {
//     await axios.post("/api/persons", newUser);
//     history.push("/login");
//     dispatch({
//       type: GET_ERRORS,
//       payload: {}
//     });
//   } catch (err) {
//     dispatch({
//       type: GET_ERRORS,
//       payload: err.response.data
//     });
//   }
// };

export const login = LoginRequest => async dispatch => {
  try {
    const res = await axios.post("/api/persons/login", LoginRequest);

    const { token } = res.data;

    localStorage.setItem("jwtToken", token);

    // set Authorization header
    setJWTToken(token);

    // decode token on React
    const decoded = jwt_decode(token);

    console.log("decoded token is...");
    console.log(decoded);

    // set current user
    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded
    });
  } catch (err) {
    console.log("Oops!");
    console.log(err);

    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const logout = () => dispatch => {
  localStorage.removeItem("jwtToken");
  setJWTToken(false); // delete the 'Authorization' header
  dispatch({
    type: SET_CURRENT_USER,
    payload: {}
  });
};
