import ReactDOM from 'react-dom';
import React from 'react';
import { HashRouter as Router, Route } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.css';
import 'font-awesome/css/font-awesome.css';

import Login from './components/Login';
import Mypage from './components/Mypage';

const Root = () => (
  <Router>
    <div>
      <Route exact path="/" component={Mypage} />
      <Route path="/login" component={Login} />
    </div>
  </Router>
);

ReactDOM.render(
  <Root />,
  document.getElementById('app'),
);
