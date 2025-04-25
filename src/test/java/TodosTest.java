import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.qa.domain.Epic;
import ru.netology.qa.domain.Meeting;
import ru.netology.qa.domain.SimpleTask;
import ru.netology.qa.domain.Task;
import ru.netology.qa.manager.Todos;

public class TodosTest {
    SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
    String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
    Epic epic = new Epic(2, subtasks);
    Meeting meeting = new Meeting(3, "Выкатка 3й версии приложения",
            "Приложение НетоБанка",
            "Во вторник после обеда");

    Todos todos = new Todos();

    @Test
    public void shouldAddThreeTasksOfDifferentType() {

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);


        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void shouldMatchInSimpleTask() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Позвонить родителям");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchInEpic() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {epic};
        Task[] actual = todos.search("Яйца");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchInMeetingTopic() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка 3й версии приложения");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchInMeetingProject() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Приложение НетоБанка");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchInMeetingTopicEqualsProject() {
        Meeting meeting2 = new Meeting(4, "Приложение НетоБанка",
                "Приложение НетоБанка",
                "Во вторник после обеда");
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting2);

        Task[] expected = {meeting2};
        Task[] actual = todos.search("Приложение НетоБанка");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotMatch() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Созвон");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchInSimpleTaskAndEpic() {
        String[] subtasks2 = {"Молоко", "Яйца", "Хлеб", "Позвонить родителям"};
        Epic epic2 = new Epic(5, subtasks2);
        todos.add(simpleTask);
        todos.add(epic2);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic2};
        Task[] actual = todos.search("Позвонить родителям");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchInSimpleTaskAndEpicAndMeeting() {
        String[] subtasks3 = {"Молоко", "Яйца", "Хлеб", "Позвонить родителям"};
        Epic epic3 = new Epic(5, subtasks3);
        Meeting meeting3 = new Meeting(8, "Позвонить родителям",
                "Позвонить родителям",
                "Во вторник после обеда");

        todos.add(simpleTask);
        todos.add(epic3);
        todos.add(meeting3);

        Task[] expected = {simpleTask, epic3, meeting3};
        Task[] actual = todos.search("Позвонить родителям");

        Assertions.assertArrayEquals(expected, actual);
    }
}
