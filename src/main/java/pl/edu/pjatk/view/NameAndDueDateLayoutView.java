package pl.edu.pjatk.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import pl.edu.pjatk.domain.tasks.TaskDto;

import java.time.LocalDate;

class NameAndDueDateLayoutView extends VerticalLayout {

    public NameAndDueDateLayoutView(TaskDto task) {
        setPadding(false);
        setSpacing(false);
        var nameSpan = new Span(task.name());
        var dueDateSpan = new Span("-");
        dueDateSpan.addClassName(LumoUtility.FontSize.SMALL);

        task.dueDate().ifPresent(dueDate -> {
            dueDateSpan.setText(dueDate.toString());
            var now = LocalDate.now();
            if (dueDate.isBefore(now) || dueDate.isEqual(now)) {
                dueDateSpan.addClassNames(LumoUtility.TextColor.ERROR, LumoUtility.FontWeight.BOLD);
            }
        });

        add(nameSpan, dueDateSpan);
    }
}
