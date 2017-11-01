import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import { Authorize } from '_config/auth';

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
      <Authorize not>
        <Link replace className="navbar-brand" to="/login" href>Login</Link>
      </Authorize>
      <Authorize>
        <Link replace className="navbar-brand" to="/logout" href>Logout</Link>
      </Authorize>
    </nav>
    {props.children}
  </div>
);

Layout.propTypes = propTypes;
Layout.defaultProps = defaultProps;

export default Layout;
