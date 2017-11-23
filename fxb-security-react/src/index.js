import ReactDOM from 'react-dom';
import React from 'react';
import { HashRouter as Router, browserHistory, Route } from 'react-router-dom';
import { Provider } from 'react-redux';

import '_config';
import createStore from '_config/store';

import Layout from '_layouts/basic';

import Login from '_apps/user/Login';

import Logout from '_apps/user/Logout';
import Mypage from '_apps/user/Mypage';
import ErrorPage from '_apps/page/ErrorPage';
import Grant from '_apps/grant';

import Authentication from './components/security/Authentication';
import PrivateRoute from './components/security/PrivateRoute';

const store = createStore();

const Root = () => (
  <Provider store={store}>
    <Router history={browserHistory}>
      <Layout>
        <Authentication>
          <Route path="/login" component={Login} />
          <Route path="/error" component={ErrorPage} />
          <Route path="/grant" component={Grant} />
          <PrivateRoute exact path="/" component={Mypage} />
          <PrivateRoute path="/logout" component={Logout} />
        </Authentication>
      </Layout>
    </Router>
  </Provider>
);

ReactDOM.render(
  <Root />,
  document.getElementById('app'),
);
