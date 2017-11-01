import React from 'react';
import { user } from './services/UserService';

class Mypage extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: '',
      remoteAddress: '',
      sessionId: '',
    };
  }

  componentWillMount() {
    user().then((res) => {
      const { details, principal } = res.data.data;
      this.setState({
        username: principal.username,
        remoteAddress: details.remoteAddress,
        sessionId: details.sessionId,
      });
    });
  }

  render() {
    return (
      <div className="row">
        <div className="col" />
        <div className="col-6">
          <div className="card">
            <div className="card-body">

              <div className="row">
                <div className="col">
                  username
                </div>
                <div className="col-8">
                  {this.state.username}
                </div>
              </div>
              <hr />

              <div className="row">
                <div className="col">
                  IP
                </div>
                <div className="col-8">
                  {this.state.remoteAddress}
                </div>
              </div>
              <hr />

              <div className="row">
                <div className="col">
                  session Id
                </div>
                <div className="col-8">
                  {this.state.sessionId}
                </div>
              </div>

            </div>
          </div>
        </div>
        <div className="col" />
      </div>
    );
  }
}

export default Mypage;
