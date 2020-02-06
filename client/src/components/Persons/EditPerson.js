import React, { Component } from "react";
import { connect } from "react-redux";
import Select from "react-select";
import { getPerson } from "../../actions/personActions";
import { editPerson } from "../../actions/personActions";
import PropTypes from "prop-types";
import classnames from "classnames";

class EditPerson extends Component {
  constructor() {
    super();

    this.state = {
      id: "",
      first_name: "",
      last_name: "",
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

    const {
      id,
      first_name,
      last_name,
      age,
      favourite_color
    } = nextProps.person.person;

    let { hobby } = nextProps.person.person;

    const hobbies = [...hobby];
    hobby = Array.from(hobbies, x => ({ name: x }));

    this.setState({
      id,
      first_name,
      last_name,
      age,
      favourite_color,
      hobby
    });
  }

  componentDidMount() {
    const { params } = this.props.match;
    this.props.getPerson(params.id, this.props.history);
  }

  onChange(e) {
    if (e.target == null) {
      this.setState({ e });
      this.setState({ favourite_color: e.value });
    } else {
      this.setState({ [e.target.name]: e.target.value });
    }
  }

  onSubmit(e) {
    e.preventDefault();
    const id = this.state.id;
    const oldPerson = {
      id: this.state.id,
      first_name: this.state.first_name,
      last_name: this.state.last_name,
      age: this.state.age,
      favourite_color: this.state.favourite_color,
      hobby: Array.from(this.state.hobby, x => x.name)
    };

    this.props.editPerson(id, oldPerson, this.props.history);
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
                <h5 className="display-4 text-center">
                  Edit [{this.state.first_name}'s] Detail
                </h5>
                <hr />

                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.id
                      })}
                      placeholder="Person ID"
                      name="Auto Generated ID"
                      value={this.state.id}
                      onChange={this.onChange}
                      disabled
                    />
                    {errors.id && (
                      <div className="invalid-feedback">{errors.id}</div>
                    )}
                  </div>
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
                    <Select
                      options={options}
                      onChange={this.onChange}
                      value={options
                        .filter(x => x.value === this.state.favourite_color)
                        .pop()}
                    />
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

EditPerson.propTypes = {
  person: PropTypes.object.isRequired,
  getPerson: PropTypes.func.isRequired,
  editPerson: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  person: state.person,
  errors: state.errors
});

export default connect(mapStateToProps, { getPerson, editPerson })(EditPerson);
