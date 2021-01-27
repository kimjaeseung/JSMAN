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

  const tags = info.tags;

  instance
    .post('user/join', JSON.stringify(user), {
      params: {
        tags: tags,
      },
    })
    .then(success)
    .catch(fail);
}

function emailTest(id, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .post('user/emailCheck', JSON.stringify(id))
    .then(success)
    .catch(fail);
}

function emailValidTest(num, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .post('user/emailValidCheck', JSON.stringify(num))
    .then(success)
    .catch(fail);
}

function nameTest(name, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .post('user/nameCheck', JSON.stringify(name))
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

export { login, join, emailTest, emailValidTest, nameTest };
