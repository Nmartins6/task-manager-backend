package br.com.nicolasmartins.task_manager_backend.controller;

import br.com.nicolasmartins.task_manager_backend.dto.TaskListDTO;
import br.com.nicolasmartins.task_manager_backend.model.TaskItem;
import br.com.nicolasmartins.task_manager_backend.model.TaskList;
import br.com.nicolasmartins.task_manager_backend.repository.TaskItemRepository;
import br.com.nicolasmartins.task_manager_backend.repository.TaskListRepository;
import br.com.nicolasmartins.task_manager_backend.service.TaskListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasklists")
@Validated
public class TaskListController {
    @Autowired
    private TaskListService taskListService;

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private TaskItemRepository taskItemRepository;

    @GetMapping
    public List<TaskListDTO> getAllTaskLists() {
        return taskListService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListDTO> getTaskListById(@PathVariable Long id) {
        Optional<TaskList> taskList = taskListService.findById(id);
        return taskList.map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TaskListDTO createTaskList(@Valid @RequestBody TaskListDTO taskListDTO) {
        TaskList taskList = convertToEntity(taskListDTO);
        return convertToDTO(taskListService.save(taskList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListDTO> updateTaskList(@PathVariable Long id, @Valid @RequestBody TaskListDTO taskListDTO) {
        Optional<TaskList> existingTaskList = taskListService.findById(id);
        if (!existingTaskList.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            TaskList taskList = existingTaskList.get();
            taskList.setName(taskListDTO.getName());

            return ResponseEntity.ok(convertToDTO(taskListService.save(taskList)));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long id) {
        Optional<TaskList> existingTaskList = taskListService.findById(id);
        if (!existingTaskList.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            taskListService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    private TaskListDTO convertToDTO(TaskList taskList) {
        TaskListDTO dto = new TaskListDTO();
        dto.setId(taskList.getId());
        dto.setName(taskList.getName());
        dto.setTaskItemIds(taskList.getItems().stream()
                .map(TaskItem::getId)
                .collect(Collectors.toSet()));
        return dto;
    }

    private TaskList convertToEntity(TaskListDTO dto) {
        TaskList taskList = new TaskList();
        taskList.setId(dto.getId());
        taskList.setName(dto.getName());

        return taskList;
    }
}
