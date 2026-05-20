package pl.edu.pjatk.domain.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.exception.TaskNotFoundException;

import java.util.List;
import java.util.Objects;

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

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    //used by REST API
    @Override
    public void updateTask(TaskDto taskDto) {
        var task = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new RuntimeException("Task not exists by id"));
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription().orElse(null));
        task.setDueDate(taskDto.getDueDate().orElse(null));
        taskRepository.save(task);
    }

    @Override
    public TaskDto findTaskById(long id) {
        return taskRepository.findById(id)
                .map(TaskDto::mapToDto)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public List<TaskDto> findAllTasks(SearchRequest searchRequest) {
        return taskRepository.findAll().stream()
                .filter(t -> Objects.isNull(searchRequest.name()) || searchRequest.name().equalsIgnoreCase(t.getName()))
                .filter(t -> Objects.isNull(searchRequest.maxDueDate()) || Objects.isNull(t.getDueDate()) || t.getDueDate().isBefore(searchRequest.maxDueDate()) || t.getDueDate().isEqual(searchRequest.maxDueDate()))
                .filter(t -> Objects.isNull(searchRequest.minDueDate()) || Objects.isNull(t.getDueDate()) || t.getDueDate().isAfter(searchRequest.minDueDate()) || t.getDueDate().isEqual(searchRequest.minDueDate()))
                .map(TaskDto::mapToDto)
                .toList();
    }

    @Override
    public TaskDto createTask(CreateTaskDto createTaskDto) {
        return TaskDto.mapToDto(taskRepository.save(createTaskDto.mapToEntity()));
    }

    @Override
    public void updateTask(long id, UpdateTaskDto updateTaskDto) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        updateTaskDto.mapIntoEntity(task);
        taskRepository.save(task);
    }

    @Override
    public void deleteTaskIfExists(Long id) {
        taskRepository.delete(taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id)));
    }
}
