package pl.edu.pjatk.domain.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> getTasksByDone(boolean done) {
        return taskRepository.findAllByDone(done).stream()
                .map(TaskDto::mapToDto)
                .toList();
    }

    @Override
    public void addTask(String name) {
        taskRepository.save(Task.builder()
                .name(name)
                .build());
    }

    @Override
    public void setDone(Long taskId, boolean value) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not exists by id"));
        task.setDone(value);
        taskRepository.save(task);
    }
}
