import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger extends Logger {

    public ConsoleLogger() {
        this.level = Logger.INFO;
    }

    @Override
    public void print(String message) {
        System.out.println("CONSOLE LOGGER [" + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "]: " + message);
    }
}
