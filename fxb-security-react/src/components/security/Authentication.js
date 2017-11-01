import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import { connect } from 'react-redux';
import { setUser } from '_apps/user/reducers/userActions';

import { auth } from '_apps/user/services/UserService';

const propTypes = {
  setUser: PropTypes.func.isRequired,
};

class Authentication extends React.Component {
  componentWillMount() {
    this.chain(this.props);
  }

  componentWillReceiveProps(nextProps) {
    this.chain(nextProps);
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
      console.log('-------------------- a');
    });
  }

  render() {
    console.log('-------------------- a2');
    return null;
  }
}

Authentication.propTypes = propTypes;

const mapDispatchToProps = dispatch => ({
  setUser: user => dispatch(setUser(user)),
});

export default withRouter(connect(undefined, mapDispatchToProps)(Authentication));
