import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import store from 'store';
import storage from 'store/storages/cookieStorage';
import axios from 'axios';

const cookie = store.createStore(storage);

const propTypes = {
  children: PropTypes.node.isRequired,
};

const Prepare = (props) => {
  const authorization = cookie.get('authorization');
  console.log(authorization);

  if (authorization) {
    axios.defaults.headers.Authorization = `Basic ${authorization}`;
  } else {
    axios.defaults.headers.Authorization = null;
  }

  return React.createElement(
    'div',
    {
      ...props,
    },
    props.children,
  );
};

Prepare.propTypes = propTypes;

export default withRouter(Prepare);
