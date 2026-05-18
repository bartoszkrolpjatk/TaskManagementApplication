package pl.edu.pjatk.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import pl.edu.pjatk.domain.tasks.TaskDto;

class DoneTaskLayoutView extends VerticalLayout {

    public DoneTaskLayoutView(TaskDto task) {
        if (!task.done())
            throw new IllegalStateException(this.getClass().getSimpleName() + " is dedicated for done tasks!");

        setPadding(false);
        setSpacing(false);
        var nameSpan = new Span(task.name());
        nameSpan.addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.DISABLED);
        nameSpan.getStyle().set("text-decoration", "line-through");
        add(nameSpan);
    }
}
