import React from 'react';
import PropTypes from 'prop-types';
import classnames from 'classnames';
import Toastr from 'modern-toastr';

import store from 'store';
import storage from 'store/storages/cookieStorage';

import s from './resources/login.module.css';
import UserService from './services/UserService';

const cookie = store.createStore(storage);

const propTypes = {
  history: PropTypes.shape({
    push: PropTypes.func.isRequired,
  }).isRequired,
};

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.onLogin = this.onLogin.bind(this);
    this.onChange = this.onChange.bind(this);

    this.user = new UserService();

    this.state = {
      username: '',
      password: '',
    };
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onLogin(e) {
    e.preventDefault();
    if (this.state.username !== '' && this.state.password !== '') {
      this.user.login(this.state.username, this.state.password).then((res) => {
        const { error, data } = res.data;
        if (error) {
          const message = '로그인을 실패하였습니다.';
          Toastr.error(message);
        } else {
          cookie.set('authorization', `${this.state.username}:${this.state.password}`);
          const { redirectUrl } = data;
          this.props.history.push(redirectUrl);
        }
      });
    }
  }

  render() {
    return (
      <div className={s.loginContainer}>
        <div className={s.background} />
        <div className={classnames(s.login, s.center)}>
          <h3 className={s.title}>
            <i className="fa fa-sign-in" /> 로그인
          </h3>
          <form onSubmit={this.onLogin}>
            <div className="from-group">
              <input type="text" className="form-control" placeholder="아이디" name="username" onChange={this.onChange} value={this.state.username} />
            </div>
            <div className="from-group">
              <input type="password" className="form-control" placeholder="비밀번호" name="password" onChange={this.onChange} value={this.state.password} />
            </div>

            <div className="form-check form-check-inline">
              <label htmlFor=" " className="form-check-label">
                <input type="checkbox" className="form-check-input" /> 아이디 저장
              </label>
            </div>

            <div className="form-check form-check-inline">
              <label htmlFor=" " className="form-check-label">
                <input type="checkbox" className="form-check-input" /> 아이디 저장
              </label>
            </div>

            <p />

            <button type="submit" className="btn btn-light btn-block">로그인</button>
          </form>
        </div>
      </div>

    );
  }
}

Login.propTypes = propTypes;

export default Login;
