import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import Select from "react-select";
import { createPerson } from "../../actions/personActions";
import classnames from "classnames";

class AddPerson extends Component {
  constructor() {
    super();

    this.state = {
      first_name: "",
      last_name: "",
      username: "",
      password: "",
      confirm_password: "",
      age: "",
      favourite_color: "",
      hobby: [{ name: "" }],
      selectedOption: "",
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

  onChange = e => {
    if (e.target == null) {
      this.setState({ e });
      this.setState({ favourite_color: e.value });
    } else {
      this.setState({ [e.target.name]: e.target.value });
    }
  };

  onSubmit(e) {
    e.preventDefault();
    const newPerson = {
      first_name: this.state.first_name,
      last_name: this.state.last_name,
      username: this.state.username,
      password: this.state.password,
      confirm_password: this.state.confirm_password,
      age: this.state.age,
      favourite_color: this.state.favourite_color,
      hobby: Array.from(this.state.hobby, x => x.name)
    };

    this.props.createPerson(newPerson, this.props.history);
  }

  handleHobbyChange = idx => evt => {
    const newHobby = this.state.hobby.map((item, sidx) => {
      if (idx !== sidx) return item;
      return { ...item, name: evt.target.value };
    });

    this.setState({ hobby: newHobby });
  };

  handleAddHobby = () => {
    this.setState({
      hobby: this.state.hobby.concat([{ name: "" }])
    });
  };

  handleRemoveHobby = idx => () => {
    this.setState({
      hobby: this.state.hobby.filter((s, sidx) => idx !== sidx)
    });
  };

  render() {
    const { errors } = this.state;
    const options = [
      { value: "red", label: "Red" },
      { value: "orange", label: "Orange" },
      { value: "yellow", label: "Yellow" },
      { value: "green", label: "Green" },
      { value: "blue", label: "Blue" },
      { value: "indigo", label: "Indigo" },
      { value: "violet", label: "Violet" },
      { value: "other", label: "Other" }
    ];

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
                        "is-invalid": errors.username
                      })}
                      placeholder="Person Account Username"
                      name="username"
                      value={this.state.username}
                      onChange={this.onChange}
                    />
                    {errors.username && (
                      <div className="invalid-feedback">{errors.username}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="password"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.password
                      })}
                      placeholder="Person Password"
                      name="password"
                      value={this.state.password}
                      onChange={this.onChange}
                    />
                    {errors.password && (
                      <div className="invalid-feedback">{errors.password}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="password"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.confirm_password
                      })}
                      placeholder="Confirm Password"
                      name="confirm_password"
                      value={this.state.confirm_password}
                      onChange={this.onChange}
                    />
                    {errors.confirm_password && (
                      <div className="invalid-feedback">
                        {errors.confirm_password}
                      </div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="number"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.age
                      })}
                      placeholder="Person Age"
                      name="age"
                      value={this.state.age}
                      onChange={this.onChange}
                    />
                    {errors.age && (
                      <div className="invalid-feedback">{errors.age}</div>
                    )}
                  </div>
                  <h6>
                    Favourite Colour
                    {errors.favourite_color && (
                      <div className="invalid-feedback">
                        {errors.favourite_color}
                      </div>
                    )}
                  </h6>

                  <div
                    className={classnames("form-group", {
                      "is-invalid": errors.favourite_color
                    })}
                  >
                    <Select options={options} onChange={this.onChange} />
                  </div>
                  {errors.favourite_color && (
                    <div className="invalid-feedback">
                      {errors.favourite_color}
                    </div>
                  )}
                  <br />
                  <h6>Hobbies</h6>
                  {this.state.hobby.map((item, idx) => (
                    <div className="form-group">
                      <input
                        type="text"
                        className={classnames("form-control form-control-lg", {
                          "is-invalid": errors.hobby
                        })}
                        name="hobby"
                        value={item.name}
                        placeholder={`Hobby #${idx + 1}`}
                        onChange={this.handleHobbyChange(idx)}
                      />
                      {errors.hobby && (
                        <div className="invalid-feedback">{errors.hobby}</div>
                      )}
                      <button
                        type="button"
                        onClick={this.handleRemoveHobby(idx)}
                        className="small"
                      >
                        -
                      </button>
                    </div>
                  ))}
                  <button
                    type="button"
                    onClick={this.handleAddHobby}
                    className="small"
                  >
                    Add Hobby
                  </button>
                  <input
                    type="submit"
                    className="btn custom-btn-primary btn-block mt-4"
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
  createPerson: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});

// connect the componenet to application states
export default connect(mapStateToProps, { createPerson })(AddPerson);
