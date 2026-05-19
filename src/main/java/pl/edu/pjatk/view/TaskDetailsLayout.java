package pl.edu.pjatk.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.domain.tasks.TaskDto;
import pl.edu.pjatk.domain.tasks.TaskService;

@Component
@UIScope
class TaskDetailsLayout extends VerticalLayout {

    private final TaskService taskService;
    private final TaskGridLayout taskGridLayout;

    private TaskDto currentTask;
    private final TextField taskName = new TextField("Task name");
    private final DatePicker taskDueDate = new DatePicker("Due date");
    private final TextArea taskDescription = new TextArea("Description");

    public TaskDetailsLayout(TaskService taskService, @Lazy TaskGridLayout taskGridLayout) {
        this.taskService = taskService;
        this.taskGridLayout = taskGridLayout;

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

        var editBtn = new Button("Edit", VaadinIcon.EDIT.create());
        editBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        editBtn.addClickListener(e -> updateTask());
        var deleteBtn = new Button("Delete", VaadinIcon.TRASH.create());
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        deleteBtn.addClickListener(e -> openDeleteConfirmationDialog());
        var footerLayout = new HorizontalLayout(editBtn, deleteBtn);
        footerLayout.setPadding(true);
        footerLayout.setSpacing(true);
        footerLayout.setWidthFull();
        footerLayout.setJustifyContentMode(JustifyContentMode.END);
        footerLayout.getStyle().set("margin-top", "auto");

        taskName.setWidthFull();
        taskDueDate.setWidthFull();
        taskDescription.setWidthFull();
        taskDescription.setHeight("40%");

        add(headerLayout,
                taskName,
                taskDueDate,
                taskDescription,
                footerLayout);

    }

    public void showDetails(TaskDto task) {
        this.currentTask = task;
        taskName.setValue(task.name());
        taskDescription.setValue(task.description());
        taskDueDate.setValue(task.dueDate().orElse(null));
        setVisible(true);
    }

    private void updateTask() {
        taskService.updateTask(TaskDto.builder()
                .id(currentTask.id())
                .name(taskName.getValue())
                .description(taskDescription.getValue())
                .dueDate(taskDueDate.getValue())
                .done(currentTask.done())
                .build());
        taskGridLayout.refreshItems();
    }

    private void deleteTask() {
        taskService.deleteTask(currentTask.id());
        taskGridLayout.refreshItems();
    }

    private void openDeleteConfirmationDialog() {
        var dialog = new Dialog();
        dialog.setHeaderTitle("Delete task");
        dialog.add(new Text("This operation cannot be undone!"));
        var cancelButton = new Button("Cancel", e -> dialog.close());
        var confirmButton = new Button("Delete", e -> {
            deleteTask();
            setVisible(false);
            dialog.close();
        });
        confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        dialog.getFooter().add(cancelButton, confirmButton);
        dialog.open();
    }
}
