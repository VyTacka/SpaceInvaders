import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger extends Logger {

    public static final String file = ".log";

    public FileLogger() {
        this.level = Logger.DEBUG;
    }

    @Override
    public void print(String message) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write("FILE LOGGER [" + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "]: " + message);
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (bw != null) try {
                bw.close();
            } catch (IOException ioe2) {
                ioe2.printStackTrace();
            }
        }
    }
}
