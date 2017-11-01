export const SET_USER = 'SET_USER';
export const DEL_USER = 'DEL_USER';

export const setUser = user => ({ type: SET_USER, user });
export const delUser = () => ({ type: DEL_USER });
