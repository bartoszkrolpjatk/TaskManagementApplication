package pl.edu.pjatk.domain.tasks;

import java.time.LocalDate;

public record SearchRequest(String name, LocalDate maxDueDate, LocalDate minDueDate) {
}
