import Toastr from 'modern-toastr';
import 'modern-toastr/dist/modern-toastr.css';

import 'bootstrap/dist/css/bootstrap.css';
import 'font-awesome/css/font-awesome.css';

import axiosDefaults from '_config/axios';

Toastr.setDefaultConfig({
  timeOut: 1000,
  progressBar: true,
});

Toastr.setPosition('toast-bottom-right');

axiosDefaults();
