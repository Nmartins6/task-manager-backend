package br.com.nicolasmartins.task_manager_backend.controller;

import br.com.nicolasmartins.task_manager_backend.dto.TaskItemDTO;
import br.com.nicolasmartins.task_manager_backend.model.TaskItem;
import br.com.nicolasmartins.task_manager_backend.model.TaskList;
import br.com.nicolasmartins.task_manager_backend.service.TaskItemService;
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
@RequestMapping("/taskitems")
@Validated
public class TaskItemController {

    @Autowired
    private TaskItemService taskItemService;

    @Autowired
    private TaskListService taskListService;

    @GetMapping
    public List<TaskItemDTO> getAllTaskItems() {
        return taskItemService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<TaskItemDTO>> getTaskItemsByListId(@PathVariable("id") Long taskListId) {
        List<TaskItem> taskItems = taskItemService.findByTaskListId(taskListId);
        List<TaskItemDTO> taskItemDTOs = taskItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskItemDTOs);
    }

    @PostMapping
    public ResponseEntity<TaskItemDTO> createTaskItem(@Valid @RequestBody TaskItemDTO taskItemDTO) {
        Optional<TaskList> taskList = taskListService.findById(taskItemDTO.getTaskListId());
        if (!taskList.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        TaskItem taskItem = convertToEntity(taskItemDTO);
        taskItem.setTaskList(taskList.get());

        TaskItem savedItem = taskItemService.save(taskItem);
        return ResponseEntity.ok(convertToDTO(savedItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskItemDTO> updateTaskItem(@PathVariable Long id, @Valid @RequestBody TaskItemDTO taskItemDTO) {
        if (!taskItemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            TaskItem taskItem = convertToEntity(taskItemDTO);
            taskItem.setId(id);
            return ResponseEntity.ok(convertToDTO(taskItemService.save(taskItem)));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskItem(@PathVariable Long id) {
        if (!taskItemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            taskItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    private TaskItem convertToEntity(TaskItemDTO taskItemDTO) {
        TaskItem taskItem = new TaskItem();
        taskItem.setTitle(taskItemDTO.getTitle());
        taskItem.setStatus(taskItemDTO.getStatus());
        taskItem.setPriority(taskItemDTO.getPriority());

        if (taskItemDTO.getTaskListId() != null) {
            TaskList taskList = taskListService.findById(taskItemDTO.getTaskListId())
                    .orElseThrow(() -> new IllegalArgumentException("TaskList não encontrado"));
        } else {
            throw new IllegalArgumentException("TaskListId não pode ser null");
        }

        return taskItem;
    }

    private TaskItemDTO convertToDTO(TaskItem taskItem) {
        TaskItemDTO taskItemDTO = new TaskItemDTO();
        taskItemDTO.setId(taskItem.getId());
        taskItemDTO.setTitle(taskItem.getTitle());
        taskItemDTO.setStatus(taskItem.getStatus());
        taskItemDTO.setPriority(taskItem.isPriority());
        taskItemDTO.setTaskListId(taskItem.getTaskList().getId());

        return taskItemDTO;
    }
}
