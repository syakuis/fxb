import ReactDOM from 'react-dom';
import React from 'react';
import { HashRouter as Router, browserHistory, Route } from 'react-router-dom';
import '_config/globals';
import Layout from '_layouts/basic';

import Login from '_apps/user/Login';
import Mypage from '_apps/user/Mypage';

import { AccessControl } from './interface';

const Root = () => (
  <Router history={browserHistory}>
    <Layout>
      <AccessControl>
        <Route exact path="/" component={Mypage} />
        <Route path="/login" component={Login} />
      </AccessControl>
    </Layout>
  </Router>
);

ReactDOM.render(
  <Root />,
  document.getElementById('app'),
);
