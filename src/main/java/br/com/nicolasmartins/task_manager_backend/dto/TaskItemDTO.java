package br.com.nicolasmartins.task_manager_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskItemDTO {

    private Long id;

    @NotBlank(message = "Titulo precisa ser definido") //Title cannot be blank
    private String title;

    @NotNull(message = "Status precisa ser especificado") //Status must be specified
    private Boolean priority;

    private Long taskListId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Titulo precisa ser definido") String getTitle() /*Title cannot be blank*/ {
        return title;
    }

    public void setTitle(@NotBlank(message = "Titulo precisa ser definido") String title /*Title cannot be blank*/ ) {
        this.title = title;
    }

    public @NotNull(message = "Status precisa ser especificado") Boolean getPriority() {
        return priority;
    }

    public void setPriority(@NotNull(message = "Status precisa ser especificado") Boolean priority) {
        this.priority = priority;
    }

    public Long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Long taskListId) {
        this.taskListId = taskListId;
    }
}
