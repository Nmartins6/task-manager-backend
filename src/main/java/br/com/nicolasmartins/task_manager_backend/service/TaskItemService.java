package br.com.nicolasmartins.task_manager_backend.service;

import br.com.nicolasmartins.task_manager_backend.model.TaskItem;
import br.com.nicolasmartins.task_manager_backend.repository.TaskItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskItemService {

    @Autowired
    private TaskItemRepository taskItemRepository;

    public List<TaskItem> findAll() {
        return taskItemRepository.findAll();
    }

    public Optional<TaskItem> findById(Long id) {
        return taskItemRepository.findById(id);
    }

    public List<TaskItem> findByTaskListId(Long taskListId) {
        return taskItemRepository.findByTaskListId(taskListId);
    }

    public TaskItem save(TaskItem taskItem) {
        return taskItemRepository.save(taskItem);
    }

    public void deleteById(Long id) {
        taskItemRepository.deleteById(id);
    }
}
