import React, { Component } from "react";
import PersonList from "./Persons/PersonList";
import CreatePersonButton from "./Persons/CreatePersonButton";
import { connect } from "react-redux";
import { getPersons } from "../actions/personActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  constructor() {
    super();
    this.state = { data: [], loading: false };
  }
  componentDidMount() {
    this.props.getPersons();
  }

  render() {
    const { persons } = this.props.person;

    if (this.state.loading) {
      return (
        <React.Fragment>
          <i>
            <b>loading . . .</b>
          </i>
        </React.Fragment>
      );
    } else {
      return (
        <div className="persons">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <h1 className="display-4 text-center">Registered Persons</h1>
                <br />
                <CreatePersonButton />
                <br />
                <hr />
                {persons.person &&
                  persons.person.map(person => (
                    <PersonList key={person.id} psn={person} />
                  ))}
              </div>
            </div>
          </div>
        </div>
      );
    }
  }
}

Dashboard.propTypes = {
  person: PropTypes.object.isRequired,
  getPersons: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  person: state.person
});

export default connect(mapStateToProps, { getPersons })(Dashboard);
