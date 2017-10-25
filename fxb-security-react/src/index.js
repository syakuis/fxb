import ReactDOM from 'react-dom';
import React from 'react';
import { HashRouter as Router, Route } from 'react-router-dom';

import '_config/globals';
import Prepare from '_config/Prepare';
import Authentication from '_apps/user/Authentication';
import Layout from '_layouts/basic';

import Login from '_apps/user/Login';
import Mypage from '_apps/user/Mypage';

const Root = () => (
  <Router>
    <Prepare>
      <Layout>
        <Authentication />
        <Route exact path="/" component={Mypage} />
        <Route path="/login" component={Login} />
      </Layout>
    </Prepare>
  </Router>
);

ReactDOM.render(
  <Root />,
  document.getElementById('app'),
);
