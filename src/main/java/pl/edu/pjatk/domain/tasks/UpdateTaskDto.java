package pl.edu.pjatk.domain.tasks;

import java.time.LocalDate;

public record UpdateTaskDto(String name, String description, LocalDate dueDate, boolean done) {

    void mapIntoEntity(Task entity) {
        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setDueDate(this.dueDate);
        entity.setDone(this.done);
    }
}
