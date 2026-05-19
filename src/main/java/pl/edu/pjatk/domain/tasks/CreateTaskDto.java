package pl.edu.pjatk.domain.tasks;

import java.time.LocalDate;

public record CreateTaskDto(String name, String description, LocalDate dueDate) {

    Task mapToEntity() {
        return Task.builder()
                .name(this.name)
                .description(this.description)
                .dueDate(this.dueDate)
                .build();
    }
}
