package pl.edu.pjatk.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.edu.pjatk.domain.tasks.TaskDto;
import pl.edu.pjatk.domain.tasks.TaskService;

@Route("")
@PageTitle("ToDo")
class DefaultView extends VerticalLayout {

    public DefaultView(TaskService taskService) {
        var grid = new Grid<>(TaskDto.class, false);
        grid.addColumn(TaskDto::name)
                .setHeader("Task's name")
                .setSortable(true);
        grid.setItems(taskService.getAllTasks());

        var addTaskBar = new HorizontalLayout();

        var newTaskTextField = new TextField();
        addTaskBar.add(newTaskTextField);

        var addNewTaskButton = new Button("test");
        addNewTaskButton.addClickListener(e -> {
             taskService.addTask(newTaskTextField.getValue());
             grid.setItems(taskService.getAllTasks());
        });
        addTaskBar.add(addNewTaskButton);

        add(grid, addTaskBar);
    }
}
