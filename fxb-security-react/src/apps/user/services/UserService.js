import axios from 'axios';
import qs from 'qs';

export const login = (username, password) => (
  axios.post('/member/signin', qs.stringify({
    username,
    password,
  }))
);

export const logout = () => axios.post('/member/logout');

export const user = () => axios.get('/member/user');

export const auth = url => axios.get('/member/authentication', { params: { url } });

