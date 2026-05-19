package pl.edu.pjatk.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.theme.lumo.LumoUtility;
import pl.edu.pjatk.domain.tasks.TaskDto;

class DoneTaskNameCellSpan extends Span {

    public DoneTaskNameCellSpan(TaskDto task) {
        super(task.getName());
        if (!task.isDone())
            throw new IllegalStateException(this.getClass().getSimpleName() + " is dedicated for done tasks!");
        addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.DISABLED);
        getStyle().set("text-decoration", "line-through");
    }
}
