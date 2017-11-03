import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { Route, withRouter } from 'react-router-dom';

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
    const {
      component: Component, isAnonymous, isAllowed, ...rest
    } = this.props;

    return (
      <Route
        {...rest}
        render={(attr) => {
          if (!isAnonymous && isAllowed) return <Component {...attr} />;
          return null;
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

export default withRouter(connect(mapStateToProps, undefined)(PrivateRoute));
