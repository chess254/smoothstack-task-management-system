import axios from 'axios';
import { useAuth } from '../context/AuthContext'; // Note: This is not a hook in export, but for interceptor

const api = axios.create({
  baseURL: '/'
});

// Interceptor to add token
api.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;