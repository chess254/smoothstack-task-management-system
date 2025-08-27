import React from 'react';
import { useNavigate } from 'react-router-dom';
import AuthForm from '../components/AuthForm';
import api from '../services/api';
import { useAuth } from '../context/AuthContext';

const Login = () => {
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (data) => {
    try {
      const res = await api.post('/api/auth/login', data);
      login(res.data);
      navigate('/');
    } catch (err) {
      alert('Login failed');
    }
  };

  return (
    <div className="p-4">
      <h1>Login</h1>
      <AuthForm onSubmit={handleSubmit} isRegister={false} />
    </div>
  );
};

export default Login;