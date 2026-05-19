package pl.edu.pjatk.domain.tasks;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Optional;

@Data
@Builder
public class TaskDto {
    private final Long id;
    private final String name;
    private final String description;
    private final LocalDate dueDate;
    private final boolean done;

    static TaskDto mapToDto(Task entity) {
        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .dueDate(entity.getDueDate())
                .done(entity.isDone())
                .build();
    }

    public Optional<LocalDate> getDueDate() {
        return Optional.ofNullable(dueDate);
    }
}
