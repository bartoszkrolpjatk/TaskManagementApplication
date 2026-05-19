package pl.edu.pjatk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.pjatk.domain.tasks.CreateTaskDto;
import pl.edu.pjatk.domain.tasks.SearchRequest;
import pl.edu.pjatk.domain.tasks.TaskDto;
import pl.edu.pjatk.domain.tasks.TaskService;
import pl.edu.pjatk.domain.tasks.UpdateTaskDto;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) LocalDate maxDueDate,
                                                     @RequestParam(required = false) LocalDate minDueDate) {
        return ResponseEntity.ok(taskService.findAllTasks(new SearchRequest(name, maxDueDate, minDueDate)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findTaskById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(CreateTaskDto createTaskDto) {
        var task = taskService.createTask(createTaskDto);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(task.getId())
                .toUri())
                .body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable long id, UpdateTaskDto updateTaskDto) {
        taskService.updateTask(id, updateTaskDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        taskService.deleteTaskIfExists(id);
        return ResponseEntity.noContent().build();
    }
}
