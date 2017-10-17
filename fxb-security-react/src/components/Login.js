import React from 'react';
import qs from 'qs';
import Request from '_commons/Request';
import classnames from 'classnames';
import s from '_resources/login.module.css';

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.request = Request.instance();

    this.onLogin = this.onLogin.bind(this);
    this.onChange = this.onChange.bind(this);

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
      this.request.post('/member/signin', qs.stringify({
        username: this.state.username,
        password: this.state.password,
      })).then((res) => {
        const { error } = res.data;

        if (error) {
          const message = '로그인을 실패하였습니다.';
          console.log(message);
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
                <input type="checkbox" className="form-check-input" />
                아이디 저장
              </label>
            </div>

            <div className="form-check form-check-inline">
              <label htmlFor=" " className="form-check-label">
                <input type="checkbox" className="form-check-input" />
                아이디 저장
              </label>
            </div>

            <p />

            <button type="submit" className="btn btn-primary btn-block">로그인</button>
          </form>
        </div>
      </div>

    );
  }
}

export default Login;
