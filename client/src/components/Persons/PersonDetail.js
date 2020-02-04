import React, { Component } from "react";
import { connect } from "react-redux";
import { getPerson } from "../../actions/projectActions";
import PropTypes from "prop-types";

class PersonDetail extends Component {
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

  render() {
    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">
                  [{this.state.first_name}] Detail
                </h5>
                <hr />
                <h6>First Name:</h6>
                <div className="form-group">{this.state.first_name}</div>
                <h6>First Last Name:</h6>
                <div className="form-group">{this.state.last_name}</div>
                <h6>Age</h6>
                <div className="form-group">{this.state.age}</div>
                <h6>Favourite Colour</h6>
                <div className="form-group">{this.state.favourite_color}</div>
                <h6>Hobby</h6>
                <div className="form-group">{this.state.hobby}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

PersonDetail.propTypes = {
  person: PropTypes.object.isRequired,
  getPerson: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  person: state.person,
  errors: state.errors
});

export default connect(mapStateToProps, { getPerson })(PersonDetail);
