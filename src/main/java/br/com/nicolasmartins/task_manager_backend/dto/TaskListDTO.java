package br.com.nicolasmartins.task_manager_backend.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class TaskListDTO {
private Long id;

@NotBlank(message = "Nome precisa ser definido")
    private String name;

    private Set<Long> taskItemIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome precisa ser definido") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Nome precisa ser definido") String name) {
        this.name = name;
    }

    public Set<Long> getTaskItemIds() {
        return taskItemIds;
    }

    public void setTaskItemIds(Set<Long> taskItemIds) {
        this.taskItemIds = taskItemIds;
    }
}