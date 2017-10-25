import axios from 'axios';
import qs from 'qs';

class UserService {
  constructor() {
    this.request = axios;
  }

  login(username, password) {
    return this.request.post('/member/signin', qs.stringify({
      username,
      password,
    }));
  }

  user() {
    return this.request.get('/member/user');
  }

  auth(url) {
    return this.request.get('/member/authentication', { params: { url } });
  }
}

export default UserService;
