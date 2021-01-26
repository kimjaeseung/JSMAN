import { createInstance } from './index.js';

const instance = createInstance();
// const config = {
//   headers: { "access-token": localStorage.getItem("access-token") }
// };

function login(info, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );
  const user = {
    id: info.id,
    password: info.password,
  };

  instance
    .post('user/login', JSON.stringify(user))
    .then(success)
    .catch(fail);
}

function join(info, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );
  const user = {
    id: info.id,
    name: info.name,
    password: info.password,
  };

  instance
    .post('user/join', JSON.stringify(user))
    .then(success)
    .catch(fail);
}

// async function findById(userid, success, fail) {
//   instance.defaults.headers['access-token'] = window.localStorage.getItem(
//     'access-token'
//   );
//   await instance
//     .get(`/user/info/${userid}`)
//     .then(success)
//     .catch(fail);
// }

export { login, join };
