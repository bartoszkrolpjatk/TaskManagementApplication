package pl.edu.pjatk.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import pl.edu.pjatk.domain.tasks.TaskDto;

import java.time.LocalDate;

class ActiveTaskNameCellLayout extends VerticalLayout {

    public ActiveTaskNameCellLayout(TaskDto task) {
        if (task.isDone())
            throw new IllegalStateException(this.getClass().getSimpleName() + " is dedicated for active tasks!");

        setPadding(false);
        setSpacing(false);
        var nameSpan = new Span(task.getName());
        var dueDateSpan = new Span("-");
        dueDateSpan.addClassName(LumoUtility.FontSize.SMALL);

        task.getDueDate().ifPresent(dueDate -> {
            dueDateSpan.setText(dueDate.toString());
            var now = LocalDate.now();
            if (dueDate.isBefore(now) || dueDate.isEqual(now)) {
                dueDateSpan.addClassNames(LumoUtility.TextColor.ERROR, LumoUtility.FontWeight.BOLD);
            }
        });

        add(nameSpan, dueDateSpan);
    }
}
