package br.com.nicolasmartins.task_manager_backend.repository;

import br.com.nicolasmartins.task_manager_backend.model.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskItemRepository extends JpaRepository<TaskItem, Long> {
    List<TaskItem> findByTaskListId(Long taskListId);
}
