import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createPerson } from "../../actions/personActions";
import classnames from "classnames"; // npm i classnames

class AddPerson extends Component {
  constructor() {
    super();

    this.state = {
      first_name: "",
      last_name: "",
      age: "",
      favourite_color: "",
      hobby: [],
      errors: {}
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newPerson = {
      first_name: this.state.first_name,
      last_name: this.state.last_name,
      age: this.state.age,
      favourite_color: this.state.favourite_color,
      hobby: this.state.hobby
    };

    this.props.createPerson(newPerson, this.props.history);
  }

  render() {
    const { errors } = this.state;

    return (
      <div>
        <div className="person">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Create Person Form</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.first_name
                      })}
                      placeholder="Person First Name"
                      name="first_name"
                      value={this.state.first_name}
                      onChange={this.onChange}
                    />
                    {errors.first_name && (
                      <div className="invalid-feedback">
                        {errors.first_name}
                      </div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.last_name
                      })}
                      placeholder="Person Last Name"
                      name="last_name"
                      value={this.state.last_name}
                      onChange={this.onChange}
                    />
                    {errors.last_name && (
                      <div className="invalid-feedback">{errors.last_name}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.age
                      })}
                      placeholder="Person Age"
                      name="age"
                      value={this.state.age}
                      onChange={this.onChange}
                      disabled
                    />
                    {errors.age && (
                      <div className="invalid-feedback">{errors.age}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.favourite_color
                      })}
                      placeholder="Person Favourite Colour"
                      name="favourite_color"
                      value={this.state.favourite_color}
                      onChange={this.onChange}
                      disabled
                    />
                    {errors.favourite_color && (
                      <div className="invalid-feedback">
                        {errors.favourite_color}
                      </div>
                    )}
                  </div>
                  <h6>Hobbies</h6>
                  <div className="form-group">
                    <input
                      type="select"
                      className="form-control form-control-lg"
                      name="hobby"
                      value={this.state.hobby}
                      onChange={this.onChange}
                    />
                  </div>

                  <input
                    type="submit"
                    className="btn btn-primary btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

AddPerson.propTypes = {
  createPerson: PropTypes.func.isRequired, // i.e createPerson is a required propType func => setting constrainst ensuring it must be there
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});

// connect the componenet to application states
export default connect(mapStateToProps, { createPerson })(AddPerson);
