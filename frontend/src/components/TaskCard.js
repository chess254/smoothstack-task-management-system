import React from 'react';

const TaskCard = ({ task, onEdit, onDelete, onStatusChange }) => {
  return (
    <div className="bg-white p-2 mb-2 shadow">
      <h3>{task.title}</h3>
      <p>{task.description}</p>
      <p>Priority: {task.priority}</p>
      <p>Assignee: {task.assigneeId}</p>
      <button onClick={() => onEdit(task)} className="text-blue-500">Edit</button>
      <button onClick={() => onDelete(task.id)} className="text-red-500 ml-2">Delete</button>
      {task.status !== 'DONE' && (
        <button onClick={() => onStatusChange(task.id, task.status === 'TODO' ? 'IN_PROGRESS' : 'DONE')} className="text-green-500 ml-2">
          Advance
        </button>
      )}
    </div>
  );
};

export default TaskCard;