import React from 'react';
import { useNavigate } from 'react-router-dom';
import AuthForm from '../components/AuthForm';
import api from '../services/api';

const Register = () => {
  const navigate = useNavigate();

  const handleSubmit = async (data) => {
    try {
      await api.post('/api/auth/register', data);
      navigate('/login');
    } catch (err) {
      alert('Registration failed');
    }
  };

  return (
    <div className="p-4">
      <h1>Register</h1>
      <AuthForm onSubmit={handleSubmit} isRegister={true} />
    </div>
  );
};

export default Register;