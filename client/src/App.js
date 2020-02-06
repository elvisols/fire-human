import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store";
import Landing from "./components/Layout/Landing";
import SecuredRoute from "./security/SecureRoute";
import jwt_decode from "jwt-decode";
import setJWTToken from "./security/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";
import Login from "./components/UserManagement/Login";
import AddPerson from "./components/Persons/AddPerson";
import EditPerson from "./components/Persons/EditPerson";
import PersonDetail from "./components/Persons/PersonDetail";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decode_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decode_jwtToken
  });

  const currentTime = Date.now() / 1000;
  // check token validity
  if (decode_jwtToken.exp < currentTime) {
    // handle logout
    store.dispatch(logout());
    window.location.href = "/";
  }
}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/" component={Landing} />
          <Route
            exact
            path={["/register", "/addPerson"]}
            component={AddPerson}
          />
          <Route exact path="/login" component={Login} />
          {
            <Switch>
              <SecuredRoute exact path="/dashboard" component={Dashboard} />
              <SecuredRoute
                exact
                path="/editPerson/:id"
                component={EditPerson}
              />
              <SecuredRoute
                exact
                path="/viewPerson/:id"
                component={PersonDetail}
              />
            </Switch>
          }
        </div>
      </Router>
    </Provider>
  );
}

export default App;
