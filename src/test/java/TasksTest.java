import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.qa.domain.Epic;
import ru.netology.qa.domain.Meeting;
import ru.netology.qa.domain.SimpleTask;
import ru.netology.qa.domain.Task;
import ru.netology.qa.manager.Todos;

public class TasksTest {

    @Test
    public void MatchSimpleTask() {
        Task simpleTask = new SimpleTask(1, "Накормить кота");

        boolean expectedMatch = true;
        boolean actualMatch = simpleTask.matches("Накормить кота");

        boolean expectedNotMatch = false;
        boolean actualNotMatch = simpleTask.matches("Позвонить родителям");

        Assertions.assertEquals(expectedMatch, actualMatch);
        Assertions.assertEquals(expectedNotMatch, actualNotMatch);
    }

    @Test
    public void MatchEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб", "Яблоко", "Хлеб"};
        Task epic = new Epic(2, subtasks);

        boolean expectedMatch = true;
        boolean actualMatch = epic.matches("Хлеб");

        boolean expectedNotMatch = false;
        boolean actualNotMatch = epic.matches("Шоколад");

        Assertions.assertEquals(expectedMatch, actualMatch);
        Assertions.assertEquals(expectedNotMatch, actualNotMatch);
    }

    @Test
    public void MatchMeeting() {
        Task meeting = new Meeting(3, "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");

        boolean expectedMatchTopic = true;
        boolean actualMatchTopic = meeting.matches("Выкатка 3й версии приложения");

        boolean expectedMatchProject = true;
        boolean actualMatchProject = meeting.matches("Приложение НетоБанка");

        boolean expectedNotMatch = false;
        boolean actualNotMatch = meeting.matches("Созвон");

        Assertions.assertEquals(expectedMatchTopic, actualMatchTopic);
        Assertions.assertEquals(expectedMatchProject, actualMatchProject);
        Assertions.assertEquals(expectedNotMatch, actualNotMatch);
    }

    @Test
    public void MatchMeetingTopicEqualsProject() {
        Task meeting = new Meeting(3, "Приложение НетоБанка",
                "Приложение НетоБанка",
                "Во вторник после обеда");

        boolean expected = true;
        boolean actual = meeting.matches("Приложение НетоБанка");

        Assertions.assertEquals(expected, actual);
    }


}
