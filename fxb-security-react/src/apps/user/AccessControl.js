import React from 'react';
import PropTypes from 'prop-types';
import { Redirect, withRouter } from 'react-router-dom';
import ErrorPage from '_apps/page/ErrorPage';

import UserService from './services/UserService';

const propTypes = {
  location: PropTypes.shape({
    pathname: PropTypes.string,
  }).isRequired,
  children: PropTypes.node.isRequired,
  ignore: PropTypes.arrayOf(PropTypes.string),
};

const defaultProps = {
  ignore: [
    '/login',
  ],
};

class AccessControl extends React.Component {
  constructor(props) {
    super(props);

    this.userService = new UserService();

    this.state = {
      isAllowed: false,
      isAnonymous: true,
      success: false,
    };
  }

  componentWillMount() {
    this.chain(this.props);
  }

  componentWillReceiveProps(nextProps) {
    const { pathname } = nextProps.location;
    if (pathname !== this.props.location.pathname) {
      this.chain(nextProps);
    }
  }

  chain(props) {
    // if (!this.pathnameIgnore()) return;
    const { pathname } = props.location;
    this.userService.auth(pathname).then((res) => {
      const {
        isAllowed,
        isAnonymous,
      } = res.data.data;
      this.setState({
        isAllowed, isAnonymous, success: true,
      });
    });
  }

  pathnameIgnore() {
    return this.props.ignore.indexOf(this.props.location.pathname) === -1;
  }

  render() {
    const { isAllowed, isAnonymous, success } = this.state;
    if (!success) return null;

    // 현재 login 경로인 경우 리다이렉트 하지 않는 다. (무한반복 호출된다.)
    if (isAnonymous && this.pathnameIgnore()) {
      return (
        <Redirect to={{
            pathname: '/login',
            state: { from: this.props.location },
          }}
        />
      );
    }

    if (!isAllowed && !isAnonymous) {
      return <ErrorPage />;
    }

    return this.props.children;
  }
}

AccessControl.propTypes = propTypes;
AccessControl.defaultProps = defaultProps;

export default withRouter(AccessControl);
