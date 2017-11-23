import React from 'react';
import { Route } from 'react-router-dom';
import Link from '_components/router/Link';

import AccessManager from './containers/AccessManager';

class Grant extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <div>
        <ul className="nav nav-pills">
          <li className="nav-item">
            <Link className="nav-link active" to="/grant/accessManager">AccessManager</Link>
          </li>
          <li className="nav-item">
            <a className="nav-link" href>그룹 생성</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href>Link</a>
          </li>
          <li className="nav-item">
            <a className="nav-link disabled" href>Disabled</a>
          </li>
        </ul>

        <Route path="/grant/accessManager" component={AccessManager} />
      </div>
    );
  }
}

export default Grant;
