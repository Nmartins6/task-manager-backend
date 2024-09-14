package br.com.nicolasmartins.task_manager_backend.service;

import br.com.nicolasmartins.task_manager_backend.model.TaskList;
import br.com.nicolasmartins.task_manager_backend.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    public List<TaskList> findAll() {
        return taskListRepository.findAll();
    }

    public Optional<TaskList> findById(Long id) {
        return taskListRepository.findById(id);
    }

    public TaskList save(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public void deleteById(Long id) {
        taskListRepository.deleteById(id);
    }
}
