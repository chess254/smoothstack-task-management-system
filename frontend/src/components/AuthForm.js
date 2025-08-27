import React from 'react';
import { useForm } from 'react-hook-form';

const AuthForm = ({ onSubmit, isRegister }) => {
  const { register, handleSubmit, formState: { errors } } = useForm();

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
      {isRegister && (
        <div>
          <input {...register('username', { required: true })} placeholder="Username" className="border p-2 w-full" />
          {errors.username && <span className="text-red-500">Required</span>}
        </div>
      )}
      <div>
        <input {...register('email', { required: true })} placeholder="Email" className="border p-2 w-full" />
        {errors.email && <span className="text-red-500">Required</span>}
      </div>
      <div>
        <input type="password" {...register('password', { required: true })} placeholder="Password" className="border p-2 w-full" />
        {errors.password && <span className="text-red-500">Required</span>}
      </div>
      <button type="submit" className="bg-blue-500 text-white p-2 w-full">Submit</button>
    </form>
  );
};

export default AuthForm;