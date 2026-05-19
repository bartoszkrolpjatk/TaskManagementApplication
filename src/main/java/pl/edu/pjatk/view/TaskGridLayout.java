package pl.edu.pjatk.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.domain.tasks.TaskDto;
import pl.edu.pjatk.domain.tasks.TaskService;

import java.util.Comparator;

@Component
@UIScope
class TaskGridLayout extends VerticalLayout {
    public static final String TASK_NAME_COLUMN = "Task's name";
    public static final String DONE_COLUMN = "Done";

    private final TaskService taskService;
    private final TaskDetailsLayout taskDetailsLayout;
    private final Grid<TaskDto> activeTasksGrid;
    private final Grid<TaskDto> doneTasksGrid;

    public TaskGridLayout(TaskService taskService, TaskDetailsLayout taskDetailsLayout) {
        this.taskService = taskService;
        this.taskDetailsLayout = taskDetailsLayout;
        this.activeTasksGrid = new Grid<>(TaskDto.class, false);
        this.doneTasksGrid = new Grid<>(TaskDto.class, false);

        setSizeFull();
        addClassNames(LumoUtility.Border.ALL,
                LumoUtility.BorderColor.CONTRAST_10,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Overflow.HIDDEN);

        setupActiveTasksGrid();
        setupDoneTasksGrid();

        var doneTasksDetails = new Details(new Text("Completed tasks"), doneTasksGrid);
        doneTasksDetails.setWidthFull();

        refreshItems();

        add(activeTasksGrid, doneTasksDetails, taskDetailsLayout);
    }

    public void refreshItems() {
        doneTasksGrid.setItems(taskService.getTasksByDone(true));
        activeTasksGrid.setItems(taskService.getTasksByDone(false));
    }

    private void setupDoneTasksGrid() {
        doneTasksGrid.addComponentColumn(DoneTaskNameCellSpan::new);
        doneTasksGrid.addComponentColumn(this::getDoneCheckbox)
                .setTextAlign(ColumnTextAlign.END)
                .setAutoWidth(true)
                .setFlexGrow(0);
    }

    private void setupActiveTasksGrid() {
        activeTasksGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.ROW_STRIPES);
        activeTasksGrid.addComponentColumn(ActiveTaskNameCellLayout::new)
                .setHeader(TASK_NAME_COLUMN)
                .setSortable(true)
                .setTextAlign(ColumnTextAlign.START)
                .setComparator(Comparator.comparing(TaskDto::getName, String.CASE_INSENSITIVE_ORDER));
        activeTasksGrid.addComponentColumn(this::getDoneCheckbox)
                .setHeader(DONE_COLUMN)
                .setTextAlign(ColumnTextAlign.END)
                .setAutoWidth(true)
                .setFlexGrow(0);
        activeTasksGrid.addSelectionListener(e -> e.getFirstSelectedItem()
                .ifPresent(taskDetailsLayout::showDetails));
    }

    private Checkbox getDoneCheckbox(TaskDto task) {
        var checkbox = new Checkbox(task.isDone());
        checkbox.addValueChangeListener(e -> {
            taskService.setDone(task.getId(), e.getValue());
            refreshItems();
        });
        return checkbox;
    }
}
