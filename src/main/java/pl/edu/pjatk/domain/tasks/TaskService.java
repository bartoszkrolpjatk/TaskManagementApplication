package pl.edu.pjatk.domain.tasks;

import java.util.List;

public interface TaskService {
    List<TaskDto> getTasksByDone(boolean done);
    void addTask(String name);
    void setDone(Long taskId, boolean value);
}
