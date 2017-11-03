import React from 'react';
import PropTypes from 'prop-types';
import { Link as RouterLink } from 'react-router-dom';

const propTypes = {
  children: PropTypes.node.isRequired,
};

const Link = props => (<RouterLink replace {...props}>{props.children}</RouterLink>);

Link.propTypes = propTypes;

export default Link;
