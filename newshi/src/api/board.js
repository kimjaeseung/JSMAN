import { createInstance } from './index.js';

const instance = createInstance();
// const config = {
//   headers: { "access-token": localStorage.getItem("access-token") }
// };

function boardList(id, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .get('/boardList', {
      params: {
        id: id,
      },
    })
    .then(success)
    .catch(fail);
}

export { boardList };
