import React from "react";
import { Link } from "react-router-dom";

const CreatePersonButton = () => {
  return (
    <React.Fragment>
      <Link to="/addPerson" className="btn btn-lg btn-info">
        Register a new Person
      </Link>
    </React.Fragment>
  );
};

export default CreatePersonButton;
