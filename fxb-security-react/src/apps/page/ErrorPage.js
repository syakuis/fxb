import React from 'react';
import PropTypes from 'prop-types';
import classnames from 'classnames';
import s from './resources/error-page.module.css';

const propTypes = {
  message: PropTypes.string,
  content: PropTypes.string,
};

const defaultProps = {
  message: '',
  content: '',
};

const ErrorPage = props => (
  <div className={s.errorPageContainer}>
    <div className={classnames('card', s.errorPage)}>
      <h4 className="card-header"><i className="fa fa-sign-in" /> 오류</h4>
      <div className="card-body">
        <h4 className="card-title">{props.message}</h4>
        <p className="card-text">{props.content}</p>
      </div>
    </div>
  </div>
);

ErrorPage.propTypes = propTypes;
ErrorPage.defaultProps = defaultProps;

export default ErrorPage;
