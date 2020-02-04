import axios from "axios";
import { GET_ERRORS, GET_PERSONS, GET_PERSON, DELETE_PERSON } from "./types";

export const createPerson = (person, history) => async dispatch => {
  try {
    const res = await axios.post("/api/persons", person);
    history.push("/dashboard");
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

// TODO:: add person Id to edit
export const editPerson = (id, person, history) => async dispatch => {
  try {
    const res = await axios.put(`/api/persons/${id}`, person);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getPersons = () => async dispatch => {
  const res = await axios.get("/api/persons");
  dispatch({
    type: GET_PERSONS,
    payload: res.data
  });
};

export const getPerson = (identifier, history) => async dispatch => {
  try {
    const res = await axios.get(`/api/persons/${identifier}`);
    dispatch({
      type: GET_PERSON,
      payload: res.data
    });
  } catch (err) {
    history.push("/dashboard");
  }
};

export const deletePerson = (identifier, history) => async dispatch => {
  if (
    window.confirm(
      "Are you sure? This will delete the person [" +
        identifier +
        "] and all the hobby related to it"
    )
  ) {
    try {
      const res = await axios.delete(`/api/persons/${identifier}`);
      dispatch({
        type: DELETE_PERSON,
        payload: identifier
      });
    } catch (err) {
      console.log("error is " + err);
    }
  }
};
