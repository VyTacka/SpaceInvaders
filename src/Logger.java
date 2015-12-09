public abstract class Logger {
    public static int INFO = 1;
    public static int DEBUG = 2;

    protected Logger next;
    protected int level;

    public abstract void print(String message);

    public void setNext(Logger next) {
        this.next = next;
    }

    public void log(int level, String message) {
        if (this.level <= level) {
            print(message);
        }

        if (next != null) {
            next.log(level, message);
        }
    }
}
