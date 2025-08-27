import React, { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';
import api from '../services/api';
import TaskBoard from '../components/TaskBoard';
import TaskModal from '../components/TaskModal';
import TaskForm from '../components/TaskForm';

const Dashboard = () => {
  const { logout, user } = useAuth();
  const [tasks, setTasks] = useState([]);
  const [users, setUsers] = useState([]);
  const [isModalOpen, setModalOpen] = useState(false);
  const [editingTask, setEditingTask] = useState(null);
  const [filters, setFilters] = useState({ status: '', assignee: '' });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchTasks();
    fetchUsers();
  }, [filters]);

  const fetchTasks = async () => {
    setLoading(true);
    try {
      const params = new URLSearchParams(filters);
      const res = await api.get(`/api/tasks?${params}`);
      setTasks(res.data);
    } catch (err) {
      setError('Failed to load tasks');
    } finally {
      setLoading(false);
    }
  };

  const fetchUsers = async () => {
    try {
      const res = await api.get('/api/users');
      setUsers(res.data);
    } catch (err) {
      setError('Failed to load users');
    }
  };

  const handleCreate = () => {
    setEditingTask(null);
    setModalOpen(true);
  };

  const handleEdit = (task) => {
    setEditingTask(task);
    setModalOpen(true);
  };

  const handleSubmit = async (data) => {
    try {
      data.creatorId = user.id;
      if (editingTask) {
        await api.put(`/api/tasks/${editingTask.id}`, data);
      } else {
        await api.post('/api/tasks', data);
      }
      fetchTasks();
      setModalOpen(false);
    } catch (err) {
      setError('Failed to save task');
    }
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/api/tasks/${id}`);
      fetchTasks();
    } catch (err) {
      setError('Failed to delete task');
    }
  };

  const handleStatusChange = async (id, newStatus) => {
    const optimisticTasks = tasks.map(t => t.id === id ? { ...t, status: newStatus } : t);
    setTasks(optimisticTasks);
    try {
      await api.put(`/api/tasks/${id}`, { status: newStatus });
    } catch (err) {
      setError('Failed to update status');
      fetchTasks(); // Rollback
    }
  };

  const handleFilterChange = (e) => {
    setFilters({ ...filters, [e.target.name]: e.target.value });
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="p-4">
      <div className="flex justify-between mb-4">
        <h1>Task Dashboard</h1>
        <button onClick={handleCreate} className="bg-green-500 text-white p-2">Create Task</button>
        <button onClick={logout} className="bg-red-500 text-white p-2">Logout</button>
      </div>
      <div className="mb-4">
        <input name="status" onChange={handleFilterChange} placeholder="Status" className="border p-2 mr-2" />
        <select name="assignee" onChange={handleFilterChange} className="border p-2">
          <option value="">All Assignees</option>
          {users.map(u => <option key={u.id} value={u.id}>{u.username}</option>)}
        </select>
      </div>
      <TaskBoard tasks={tasks} onEdit={handleEdit} onDelete={handleDelete} onStatusChange={handleStatusChange} />
      <TaskModal isOpen={isModalOpen} onClose={() => setModalOpen(false)}>
        <TaskForm onSubmit={handleSubmit} defaultValues={editingTask} users={users} />
      </TaskModal>
    </div>
  );
};

export default Dashboard;