import { combineReducers, createStore, compose } from 'redux';
// 사용자 인증과 권한 정보를 관리한다.
import user, * as userActions from '_apps/user/reducers/userReducer';

const reducers = combineReducers({ user });

const store = () => {
  if (process.env.NODE_ENV !== 'production') {
    const enhancers = compose(window.devToolsExtension ? window.devToolsExtension() : f => f);
    return createStore(reducers, enhancers);
  }
  return createStore(reducers);
};

export default store;
export { userActions };
