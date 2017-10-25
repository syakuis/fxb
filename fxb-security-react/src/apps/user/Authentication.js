import React from 'react';
import PropTypes from 'prop-types';
import { withRouter, Redirect } from 'react-router-dom';
import axios from 'axios';

import UserService from './services/UserService';
import ErrorPage from '_apps/page/ErrorPage';

const propTypes = {
  location: PropTypes.shape({
    pathname: PropTypes.string,
  }).isRequired,
  // children: PropTypes.node.isRequired,
};

class Authentication extends React.Component {
  constructor(props) {
    super(props);
    console.log(this.props);
    this.userService = new UserService();

    this.state = {
      message: '',
      code: 200,
      credentials: null,
      details: null,
      isAllowed: false,
      isAnonymous: true,
      principal: null,
    };
  }

  componentWillMount() {
    const { pathname } = this.props.location;

    this.userService.auth(pathname).then((res) => {
      const { message, code, data } = res.data;
      const {
        credentials,
        details,
        isAllowed,
        isAnonymous,
        principal,
      } = data;

      this.setState({
        message, code, credentials, details, isAllowed, isAnonymous, principal,
      });
      console.log(res);
    }).catch((err) => {
      console.log(err);
    });
  }

  render() {
    const { pathname } = this.props.location;
    if (!this.state.isAllowed && this.state.isAnonymous && pathname !== '/login') {
      return (
        <Redirect
          to={{ pathname: '/login', state: { from: this.props.location } }}
        />
      );
    }

    if (!this.state.isAllowed) {
      return <ErrorPage message={this.state.message} />;
    }

    return null;
  }
}

Authentication.propTypes = propTypes;

export default withRouter(Authentication);
