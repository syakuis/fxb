import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import { connect } from 'react-redux';
import { setUser } from '_apps/user/reducers/userActions';

import { auth } from '_apps/user/services/UserService';

const propTypes = {
  history: PropTypes.shape({
    push: PropTypes.func.isRequired,
  }).isRequired,
  location: PropTypes.shape({
    pathname: PropTypes.string.isRequired,
  }).isRequired,
  children: PropTypes.node.isRequired,
  setUser: PropTypes.func.isRequired,

  ignoreUrl: PropTypes.arrayOf(PropTypes.string),
};

const defaultProps = {
  ignoreUrl: [
    '/login',
    '/error',
  ],
};

class Authentication extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      success: false,
    };
  }
  componentWillMount() {
    this.chain(this.props);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.location.pathname !== nextProps.location.pathname) this.chain(nextProps);
  }

  chain(props) {
    auth(props.location.pathname).then((res) => {
      const {
        isAllowed,
        isAnonymous,
        username,
      } = res.data.data;

      this.props.setUser({
        username,
        isAllowed,
        isAnonymous,
      });

      this.setState({ success: true });

      if (this.props.ignoreUrl.indexOf(props.location.pathname) === -1) {
        let pathname = null;
        if (isAnonymous) {
          pathname = '/login';
        }

        if (!isAllowed && pathname === null) {
          pathname = '/error';
        }

        if (pathname !== null) {
          this.props.history.push(pathname);
        }
      }
    });
  }

  render() {
    if (!this.state.success) return null;
    return this.props.children;
  }
}

Authentication.propTypes = propTypes;
Authentication.defaultProps = defaultProps;

const mapStateToProps = state => ({
  isAllowed: state.user.isAllowed,
  isAnonymous: state.user.isAnonymous,
});

const mapDispatchToProps = dispatch => ({
  setUser: user => dispatch(setUser(user)),
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Authentication));
