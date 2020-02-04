import React, { Component } from "react";
import { connect } from "react-redux";
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

    const {
      id,
      first_name,
      last_name,
      age,
      favourite_color,
      hobby
    } = nextProps.person.person;

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
    this.setState({ [e.target.name]: e.target.value });
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
      hobby: this.state.hobby
    };

    this.props.editPerson(id, oldPerson, this.props.history);
  }

  render() {
    const { errors } = this.state;
    return (
      <div>
        <div className="person">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">
                  Edit [{this.state.first_name}] Form
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
                      disabled
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
