import { createInstance } from './index.js';
// import 'url-search-params-polyfill';

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
    name: null,
    password: info.password,
    thumbnail_path: null,
    platform_type: null,
  };

  instance
    .post('user/login', user)
    .then(success)
    .catch(fail);
}

function socialLogin(info, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );
  const user = {
    id: info.id,
    name: info.name,
    password: null,
    thumbnail_path: info.thumbnail_path,
    platform_type: info.platform_type,
  };

  instance
    .post('user/socialLogin', user)
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
    thumbnail_path: info.thumbnail_path,
    platform_type: null,
  };
  const tag = info.tags;
  // var params = new URLSearchParams();
  // params.append('tag', tag);

  instance
    .post('join', user, {
      params: {
        tag: tag,
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
    .get('idcheck', {
      params: {
        id: id,
      },
    })
    .then(success)
    .catch(fail);
}

function emailValidTest(id, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .get('emailauth', {
      params: {
        id: id,
      },
    })
    .then(success)
    .catch(fail);
}

function nameTest(name, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .get('namecheck', {
      params: {
        name: name,
      },
    })
    .then(success)
    .catch(fail);
}

function getInfo(id, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .post('user/getInfo', JSON.stringify(id))
    .then(success)
    .catch(fail);
}

function changePassword(info, success, fail) {
  const user = {
    id: info.id,
    password: info.password,
  };

  instance
    .post('user/changePw', JSON.stringify(user))
    .then(success)
    .catch(fail);
}

export {
  login,
  join,
  emailTest,
  emailValidTest,
  nameTest,
  getInfo,
  socialLogin,
  changePassword,
};
