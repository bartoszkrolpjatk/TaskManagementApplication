package pl.edu.pjatk.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.domain.tasks.TaskDto;
import pl.edu.pjatk.domain.tasks.TaskService;

@Component
@UIScope
class ContentLayoutView extends VerticalLayout {

    public ContentLayoutView(TaskService taskService) {
        setWidth("50%");
        setHeight("75%");
        setMinWidth("400px");
        setMinHeight("200px");
        setPadding(false);

        var grid = new Grid<>(TaskDto.class, false);
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.ROW_STRIPES);

        grid.addComponentColumn(NameAndDueDateLayoutView::new)
                .setHeader("Task's name")
                .setSortable(true)
                .setTextAlign(ColumnTextAlign.START);
        grid.setItems(taskService.getAllTasks());
        grid.addComponentColumn(task -> {
                    var checkbox = new Checkbox(task.done());
                    checkbox.addValueChangeListener(e -> taskService.setDone(task.id(), e.getValue()));
                    return checkbox;
                })
                .setHeader("Done")
                .setTextAlign(ColumnTextAlign.END)
                .setAutoWidth(true)
                .setFlexGrow(0);

        var addTaskBar = new HorizontalLayout();
        addTaskBar.setWidthFull();

        var newTaskTextField = new TextField();

        var addNewTaskButton = new Button("test");
        addNewTaskButton.addClickListener(e -> {
            taskService.addTask(newTaskTextField.getValue());
            grid.setItems(taskService.getAllTasks());
            newTaskTextField.clear();
        });

        addTaskBar.add(newTaskTextField, addNewTaskButton);
        addTaskBar.expand(newTaskTextField);

        add(grid, addTaskBar);
    }
}
