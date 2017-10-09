import ReactDOM from 'react-dom';
import React from 'react';
import { HashRouter as Router, Route, Link } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.css';
import 'font-awesome/css/font-awesome.css';

import Login from './components/Login';
import Mypage from './components/Mypage';

const Root = () => (
  <Router>
    <div className="container">
      <nav className="navbar navbar-light bg-light">
        <Link className="navbar-brand" to="/" href>Home</Link>
      </nav>

      <Route exact path="/" component={Mypage} />
      <Route path="/login" component={Login} />
    </div>
  </Router>
);

ReactDOM.render(
  <Root />,
  document.getElementById('app'),
);
