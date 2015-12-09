public class LoggerChain {
    private static LoggerChain ourInstance = new LoggerChain();

    public static LoggerChain getInstance() {
        return ourInstance;
    }

    Logger loggerChain;

    private LoggerChain() {

        Logger fileLogger = new FileLogger();
        Logger consoleLogger = new ConsoleLogger();

        fileLogger.setNext(consoleLogger);

        loggerChain = fileLogger;
    }

    public Logger getChain() {
        return loggerChain;
    }
}
