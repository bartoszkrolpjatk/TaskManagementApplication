package pl.edu.pjatk.domain.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskDto::mapToDto)
                .toList();
    }

    @Override
    public void addTask(String name) {
        taskRepository.save(Task.builder()
                .name(name)
                .build());
    }
}
