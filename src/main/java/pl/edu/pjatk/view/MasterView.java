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
        add(taskManagementLayout);
    }
}
