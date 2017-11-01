import React from 'react';
import PropTypes from 'prop-types';
import { Redirect, withRouter } from 'react-router-dom';
import { connect } from 'react-redux';
import { setUser } from './reducers/userActions';

import { auth } from './services/UserService';

const propTypes = {
  location: PropTypes.shape({
    pathname: PropTypes.string,
  }).isRequired,
  children: PropTypes.node.isRequired,
  ignore: PropTypes.arrayOf(PropTypes.string),

  isAllowed: PropTypes.bool.isRequired,
  isAnonymous: PropTypes.bool.isRequired,
  setUser: PropTypes.func.isRequired,
};

const defaultProps = {
  ignore: [
    '/login', '/error',
  ],
};

class AccessControl extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isAllowed: props.isAllowed,
      isAnonymous: props.isAnonymous,
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
    const { pathname } = props.location;
    auth(pathname).then((res) => {
      const {
        isAllowed,
        isAnonymous,
        username,
      } = res.data.data;
      console.log(res);
      this.setState({
        isAllowed, isAnonymous, success: true,
      });

      this.props.setUser({
        username,
        isAllowed,
        isAnonymous,
      });
    });
  }

  pathnameIgnore() {
    return this.props.ignore.indexOf(this.props.location.pathname) > -1;
  }

  render() {
    const { isAllowed, isAnonymous, success } = this.state;

    // 현재 login 경로인 경우 리다이렉트 하지 않는 다. (무한반복 호출된다.)

    if (!success) return null;
    console.log(this.props.location.pathname, isAnonymous, isAllowed, success);
    if (this.pathnameIgnore()) return this.props.children;

    if (!isAllowed && isAnonymous) {
      return (
        <Redirect to={{
            pathname: '/login',
            state: { from: this.props.location },
          }}
        />
      );
    }

    if (!isAllowed) {
      return (
        <Redirect to={{
            pathname: '/error',
            state: { from: this.props.location },
          }}
        />
      );
    }

    return this.props.children;
  }
}

AccessControl.propTypes = propTypes;
AccessControl.defaultProps = defaultProps;

const mapStateToProps = state => ({
  isAllowed: state.user.isAllowed,
  isAnonymous: state.user.isAllowed,
});

const mapDispatchToProps = dispatch => ({
  setUser: user => dispatch(setUser(user)),
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(AccessControl));
