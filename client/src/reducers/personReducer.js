import { GET_PERSONS, GET_PERSON, DELETE_PERSON } from "../actions/types";

const initialState = {
  persons: [],
  person: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_PERSONS:
      return {
        ...state,
        persons: action.payload
      };
    case GET_PERSON:
      return {
        ...state,
        person: action.payload
      };
    case DELETE_PERSON:
      const p = { person: [] };
      p.person = state.persons.person.filter(
        person => person.id !== action.payload
      );

      return {
        ...state,
        persons: p
      };
    default:
      return state;
  }
}
