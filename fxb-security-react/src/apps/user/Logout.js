import React from 'react';
import PropTypes from 'prop-types';
import { Redirect } from 'react-router-dom';
import { logout } from './services/UserService';

const propTypes = {
  location: PropTypes.shape().isRequired,
};

class Logout extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      success: false,
    };
  }

  componentWillMount() {
    logout().then(() => this.setState({ success: true }));
  }

  render() {
    if (!this.state.success) return null;
    return <Redirect to={{ pathname: '/', state: this.props.location }} />;
  }
}

Logout.propTypes = propTypes;

export default Logout;
