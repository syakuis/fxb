import axios from 'axios';
import Qs from 'qs';

import NProgress from 'nprogress';
import 'nprogress/nprogress.css';

let requestCount = 0;

const progress = (go) => {
  if (go === undefined) {
    requestCount = 0;
    NProgress.done();
  }

  if (go) {
    if (requestCount === 0) {
      NProgress.start();
    }
    requestCount += 1;
  } else {
    if (requestCount > 0) requestCount -= 1;
    if (requestCount === 0) {
      NProgress.done();
    }
  }
};

const getRequestCount = () => requestCount;

const axiosDefaults = () => {
  axios.defaults.baseURL = '/api';
  axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest';
  axios.defaults.withCredentials = true;
  axios.defaults.paramsSerializer = params => Qs.stringify(params, { arrayFormat: 'repeat' });

  axios.interceptors.request.use((request) => {
    progress(true);
    return request;
  }, (error) => {
    progress(undefined);
    return Promise.reject(error);
  });

  axios.interceptors.response.use((response) => {
    progress(false);
    return response;
  }, (error) => {
    progress(undefined);
    return Promise.reject(error);
  });
};

export default axiosDefaults;
export { progress, getRequestCount };
