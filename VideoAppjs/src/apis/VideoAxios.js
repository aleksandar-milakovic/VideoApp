import axios from 'axios';
import {logout} from "../services/auth"

var VideoAxios = axios.create({
  baseURL: 'http://localhost:8080/api',
  /* other custom settings */
});

VideoAxios.interceptors.request.use(
  function success(config){
    const jwt = window.localStorage['jwt'];
    if(jwt){
      config.headers['Authorization'] = 'Bearer ' + jwt;
    }
    return config;
  }
);

VideoAxios.interceptors.response.use(
  function success(response){
      return response;
  },
  function failure(error){
    let jwt = window.localStorage['jwt'];
    if(jwt){
      if(error.response && error.response.status == 403){
        logout();
      }
    }
    
    throw error;
  }
);

export default VideoAxios;