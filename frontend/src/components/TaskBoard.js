import React from 'react';
import TaskCard from './TaskCard';

const TaskBoard = ({ tasks, onEdit, onDelete, onStatusChange }) => {
  const columns = ['TODO', 'IN_PROGRESS', 'DONE'];

  return (
    <div className="flex space-x-4">
      {columns.map(status => (
        <div key={status} className="bg-gray-100 p-4 w-1/3">
          <h2 className="font-bold mb-2">{status}</h2>
          {tasks.filter(t => t.status === status).map(task => (
            <TaskCard key={task.id} task={task} onEdit={onEdit} onDelete={onDelete} onStatusChange={onStatusChange} />
          ))}
        </div>
      ))}
    </div>
  );
};

export default TaskBoard;