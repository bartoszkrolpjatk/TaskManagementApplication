package pl.edu.pjatk.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.domain.tasks.TaskService;

@Component
@UIScope
class TaskManagementLayout extends HorizontalLayout {

    public TaskManagementLayout(TaskService taskService, TaskGridLayout taskGridLayout, TaskDetailsLayout taskDetailsLayout) {
        var verticalLayout = new VerticalLayout();
        verticalLayout.setPadding(false);

        var addTaskBar = new HorizontalLayout();
        addTaskBar.setWidthFull();
        addTaskBar.addClassName(LumoUtility.Padding.Right.MEDIUM);

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

        verticalLayout.add(taskGridLayout, addTaskBar);

        var splitLayout = new SplitLayout(verticalLayout, taskDetailsLayout);
        splitLayout.setSizeFull();
        splitLayout.setSplitterPosition(70);
        add(splitLayout);
    }
}
