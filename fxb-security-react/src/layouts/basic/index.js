import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

const propTypes = {
  children: PropTypes.node,
};

const defaultProps = {
  children: null,
};

const Layout = props => (
  <div>
    <nav className="navbar navbar-light bg-light">
      <Link replace className="navbar-brand" to="/" href>Home</Link>
      <Link replace className="navbar-brand" to="/login" href>Login</Link>
    </nav>
    {props.children}
  </div>
);

Layout.propTypes = propTypes;
Layout.defaultProps = defaultProps;

export default Layout;
