package br.com.nicolasmartins.task_manager_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_items")
public class TaskItem {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    public enum Status {
        PENDENTE, //PENDING
        EM_ANDAMENTO, //IN_PROGRESS
        CONCLUIDO //COMPLETED
    }

    @Enumerated (EnumType.STRING)
    @Column(nullable = false)
    private  Status status;

    @Column (nullable = false)
    private boolean priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}