import * as actions from './userActions';

const defaultProps = {
  username: null,
  isAllowed: false,
  isAnonymous: true,
  lastLoginDate: null,
};

const user = (state = defaultProps, action) => {
  switch (action.type) {
    // 업데이트할때는 항상 기존 데이터 초기화하기
    case actions.SET_USER: {
      return Object.assign(
        {}, defaultProps,
        {
          username: action.user.username,
          isAllowed: action.user.isAllowed,
          isAnonymous: action.user.isAnonymous,
          lastLoginDate: Date.now(),
        },
      );
    }
    case actions.DEL_USER:
      return Object.assign({}, defaultProps);
    default: {
      return state;
    }
  }
};

export default user;
export { actions };
