import React from 'react';
import { useForm } from 'react-hook-form';

const TaskForm = ({ onSubmit, defaultValues, users }) => {
  const { register, handleSubmit, formState: { errors } } = useForm({ defaultValues });

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
      <input {...register('title', { required: true })} placeholder="Title" className="border p-2 w-full" />
      {errors.title && <span className="text-red-500">Required</span>}
      <textarea {...register('description')} placeholder="Description" className="border p-2 w-full" />
      <select {...register('status', { required: true })} className="border p-2 w-full">
        <option value="TODO">TODO</option>
        <option value="IN_PROGRESS">IN_PROGRESS</option>
        <option value="DONE">DONE</option>
      </select>
      <input type="number" {...register('priority', { required: true })} placeholder="Priority" className="border p-2 w-full" />
      {errors.priority && <span className="text-red-500">Required</span>}
      <select {...register('assigneeId')} className="border p-2 w-full">
        <option value="">None</option>
        {users.map(user => (
          <option key={user.id} value={user.id}>{user.username}</option>
        ))}
      </select>
      <button type="submit" className="bg-blue-500 text-white p-2 w-full">Save</button>
    </form>
  );
};

export default TaskForm;