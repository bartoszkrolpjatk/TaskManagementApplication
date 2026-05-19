package pl.edu.pjatk.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.domain.tasks.TaskService;

@Component
@UIScope
class TaskManagementLayout extends VerticalLayout {

    public TaskManagementLayout(TaskService taskService, TaskGridLayout taskGridLayout) {
        setWidth("50%");
        setHeight("75%");
        setMinWidth("400px");
        setMinHeight("200px");
        setPadding(false);

        var addTaskBar = new HorizontalLayout();
        addTaskBar.setWidthFull();

        var newTaskTextField = new TextField();
        var addNewTaskButton = new Button(VaadinIcon.PLUS.create());

        newTaskTextField.addKeyDownListener(Key.ENTER, e -> addNewTaskButton.click());

        addNewTaskButton.addClickListener(e -> {
            taskService.addTask(newTaskTextField.getValue());
            taskGridLayout.refreshItems();
            newTaskTextField.clear();
        });

        addTaskBar.add(newTaskTextField, addNewTaskButton);
        addTaskBar.expand(newTaskTextField);

        add(taskGridLayout, addTaskBar);
    }
}
