package pl.edu.pjatk.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("ToDo")
class DefaultView extends VerticalLayout {

    public DefaultView(ContentLayoutView contentLayoutView) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(contentLayoutView);
    }
}
