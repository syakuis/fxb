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

  onLogin() {
    if (this.state.username !== '' && this.state.password !== '') {
      this.request.post('/member/signin', qs.stringify({
        username: this.state.username,
        password: this.state.password,
      })).then((res) => {
        console.log(res);
      });
    }
  }

  render() {
    return (
      <div className={classnames('row', s.login)}>
        <div className="col-md-4 col-md-offset-4">
          <div>
            <form className="form">
              <h2 className="form-signin-heading">
                <i className="fa fa-sign-in" /> 로그인
              </h2>
              <p>
                <input type="text" className="form-control" placeholder="아이디" name="username" onChange={this.onChange} value={this.state.username} />
              </p>
              <p>
                <input type="password" className="form-control" placeholder="비밀번호" name="password" onChange={this.onChange} value={this.state.password} />
              </p>
              <div className="checkbox">
                <label className="checkbox-inline" htmlFor>
                  <input type="checkbox" id="remember_user_id" value="Y" /> 아이디 저장
                </label>
              </div>
              <button className="btn btn-lg btn-primary btn-block" type="button" onClick={this.onLogin}>로그인</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Login;
