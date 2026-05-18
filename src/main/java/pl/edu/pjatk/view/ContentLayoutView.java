package pl.edu.pjatk.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.domain.tasks.TaskService;

@Component
@UIScope
class ContentLayoutView extends VerticalLayout {

    public ContentLayoutView(TaskService taskService, GridLayoutView gridLayoutView) {
        setWidth("50%");
        setHeight("75%");
        setMinWidth("400px");
        setMinHeight("200px");
        setPadding(false);

        var addTaskBar = new HorizontalLayout();
        addTaskBar.setWidthFull();

        var newTaskTextField = new TextField();

        var addNewTaskButton = new Button("test");
        addNewTaskButton.addClickListener(e -> {
            taskService.addTask(newTaskTextField.getValue());
            gridLayoutView.refreshItems();
            newTaskTextField.clear();
        });

        addTaskBar.add(newTaskTextField, addNewTaskButton);
        addTaskBar.expand(newTaskTextField);

        add(gridLayoutView, addTaskBar);
    }
}
