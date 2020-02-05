import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store";
import Landing from "./components/Layout/Landing";
import Login from "./components/UserManagement/Login";
import AddPerson from "./components/Persons/AddPerson";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/" component={Landing} />
          <Route exact path="/register" component={AddPerson} />
          <Route exact path="/login" component={Login} />

          {
            // private route
          }
          {
            // <Switch>
            //   <SecuredRoute exact path="/dashboard" component={Dashboard} />
            //   <SecuredRoute exact path="/addProject" component={AddProject} />
            //   <SecuredRoute
            //     exact
            //     path="/editProject/:identifier"
            //     component={EditProject}
            //   />
            //   <SecuredRoute
            //     exact
            //     path="/projectBoard/:identifier"
            //     component={ProjectBoard}
            //   />
            //   <SecuredRoute
            //     exact
            //     path="/addProjectTask/:identifier"
            //     component={AddProjectTask}
            //   />
            //   <SecuredRoute
            //     exact
            //     path="/updateProjectTask/:backlog_id/:pt_id"
            //     component={UpdateProjectTask}
            //   />
            // </Switch>
          }
        </div>
      </Router>
    </Provider>
  );
}

export default App;
