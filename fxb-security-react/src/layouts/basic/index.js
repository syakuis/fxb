import React from 'react';
import PropTypes from 'prop-types';
import Link from '_components/router/Link';
import Authorize from '_components/security/Authorize';

const propTypes = {
  children: PropTypes.node,
};

const defaultProps = {
  children: null,
};

const Layout = props => (
  <div>
    <nav className="navbar navbar-light bg-light">
      <Link className="navbar-brand" to="/" href>Home</Link>
      <Authorize not>
        <Link className="navbar-brand" to="/login" href>Login</Link>
      </Authorize>
      <Authorize>
        <Link className="navbar-brand" to="/logout" href>Logout</Link>
      </Authorize>
    </nav>
    {props.children}
  </div>
);

Layout.propTypes = propTypes;
Layout.defaultProps = defaultProps;

export default Layout;
