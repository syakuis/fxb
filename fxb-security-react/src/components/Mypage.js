import React from 'react';
import { Link } from 'react-router-dom';

const Mypage = () => (
  <div>
    <nav className="navbar navbar-light bg-light">
      <Link className="navbar-brand" to="/" href>Home</Link>
    </nav>
  </div>
);

export default Mypage;
