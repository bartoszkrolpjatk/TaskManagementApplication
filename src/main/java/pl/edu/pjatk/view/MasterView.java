package pl.edu.pjatk.view;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("ToDo")
class MasterView extends HorizontalLayout {

    public MasterView(TaskManagementLayout taskManagementLayout) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        taskManagementLayout.setWidth("70%");
        taskManagementLayout.setHeight("75%");
        taskManagementLayout.setMinWidth("400px");
        taskManagementLayout.setMinHeight("200px");

        add(taskManagementLayout);
    }
}
