import React from 'react';
import UserService from './services/UserService';

class Mypage extends React.Component {
  constructor(props) {
    super(props);

    this.request = new UserService();

    this.state = {

    };
  }

  componentWillMount() {
    this.request.user().then((res) => {
      console.log(res);
    }).catch((err) => {
      console.log(err);
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
                  IP
                </div>
                <div className="col-8">
                  192.168.0.0
                </div>
              </div>
              <hr />

              <div className="row">
                <div className="col">
                  username
                </div>
                <div className="col-8">
                  admin
                </div>
              </div>
              <hr />
            </div>
          </div>
        </div>
        <div className="col" />
      </div>
    );
  }
}

export default Mypage;
