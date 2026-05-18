package pl.edu.pjatk.domain.tasks;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Builder
@Accessors(fluent = true)
public class TaskDto {
    private final String name;
    private final String description;
    private final LocalDate dueDate;

    static TaskDto mapToDto(Task entity) {
        return TaskDto.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .dueDate(entity.getDueDate())
                .build();
    }
}
