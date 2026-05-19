package pl.edu.pjatk.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.domain.tasks.TaskDto;

@Component
@UIScope
class TaskDetailsLayout extends VerticalLayout {

    private final TextField taskName = new TextField("Task name");
    private final DatePicker taskDueDate = new DatePicker("Due date");
    private final TextArea taskDescription = new TextArea("Description");

    public TaskDetailsLayout() {
        setVisible(false);
        setWidthFull();

        var closeBtn = new Button(VaadinIcon.CLOSE.create());
        closeBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeBtn.addClickListener(e -> setVisible(false));
        var detailsHeader = new H2("Task's details");
        detailsHeader.addClassNames(LumoUtility.FontWeight.BOLD, LumoUtility.FontSize.LARGE);
        var headerLayout = new HorizontalLayout(detailsHeader, closeBtn);
        headerLayout.setWidthFull();
        headerLayout.setAlignItems(Alignment.CENTER);
        headerLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        setAlignItems(Alignment.CENTER);
        addClassNames(LumoUtility.Border.ALL,
                LumoUtility.BorderColor.CONTRAST_20,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.BoxShadow.SMALL,
                LumoUtility.Background.BASE);

        taskName.setWidthFull();
        taskDueDate.setWidthFull();
        taskDescription.setWidthFull();
        taskDescription.setHeight("40%");
        add(headerLayout,
                taskName,
                taskDueDate,
                taskDescription);

    }

    public void showDetails(TaskDto task) {
        taskName.setValue(task.name());
        taskDescription.setValue(task.description());
        taskDueDate.setValue(task.dueDate().orElse(null));
        setVisible(true);
    }
}
