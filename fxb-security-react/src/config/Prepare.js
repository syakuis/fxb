import { withRouter } from 'react-router-dom';
import store from 'store';
import storage from 'store/storages/cookieStorage';
import axios from 'axios';

const cookie = store.createStore(storage);

const Prepare = () => {
  const authorization = cookie.get('authorization');

  // if (authorization) {
  //   axios.defaults.headers.Authorization = `Basic ${authorization.key}`;
  // } else {
  //   axios.defaults.headers.Authorization = null;
  // }

  return null;
};

export default withRouter(Prepare);
