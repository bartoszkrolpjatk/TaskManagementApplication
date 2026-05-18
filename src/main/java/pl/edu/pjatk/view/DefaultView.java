package pl.edu.pjatk.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("ToDo")
class DefaultView extends VerticalLayout {

    public DefaultView() {
        var addTaskBar = new HorizontalLayout();

        var addNewTaskButton = new Button("test");
        addTaskBar.add(addNewTaskButton);

        var newTaskTextField = new TextField();
        addTaskBar.add(newTaskTextField);

        add(addTaskBar);
    }
}
