import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { Route, Redirect } from 'react-router-dom';

const propTypes = {
  component: PropTypes.func.isRequired,

  isAllowed: PropTypes.bool.isRequired,
  isAnonymous: PropTypes.bool.isRequired,
};

class PrivateRoute extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
    };
  }

  render() {
    console.log('-------------------------2', this.props);

    const {
      component: Component, isAnonymous, isAllowed, ...rest
    } = this.props;

    return (
      <Route
        {...rest}
        render={(attr) => {
          console.log('-------------------------3');
          let pathname = null;
          if (isAnonymous) {
            pathname = '/login';
          }

          if (!isAllowed && pathname === null) {
            pathname = '/error';
          }

          console.log(pathname, attr.location, isAnonymous, isAllowed);
          if (pathname !== null) {
            return <Redirect to={{ pathname, state: { from: attr.location } }} />;
          }
          return <Component {...attr} />;
        }}
      />
    );
  }
}

PrivateRoute.propTypes = propTypes;

const mapStateToProps = state => ({
  isAllowed: state.user.isAllowed,
  isAnonymous: state.user.isAnonymous,
});

export default connect(mapStateToProps, undefined)(PrivateRoute);
