package eu.russiantrade.util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Date;
import java.util.logging.*;

public class LogGenerator {
    private static final Logger logger = Logger.getLogger("Log");
    private static final Logger debugger = Logger.getLogger("Debug");
    private static final String S = FileSystems.getDefault().getSeparator();
    public static final String ABSOLUTE_PATH = S + "usr" + S + "share" + S + "tomcat" + S + "webapps" + S +
        "russiantrade" + S + "log" + S + "log.log";
    public static final String ABSOLUTE_PATH_DEBUG = S + "usr" + S + "share" + S + "tomcat" + S + "webapps" + S +
            "russiantrade" + S + "log" + S + "debug.log";

    static {
        FileHandler fileHandlerLog;
        FileHandler fileHandlerDebug;

        try {
            fileHandlerLog = new FileHandler(ABSOLUTE_PATH, true);
            fileHandlerDebug = new FileHandler(ABSOLUTE_PATH_DEBUG, true);
            SimpleFormatter formatter = new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public String formatMessage(LogRecord record) {
                    return String.format(format,
                            new Date(record.getMillis()),
                            record.getLevel().getLocalizedName(),
                            record.getMessage()
                    );
                }
            };

            fileHandlerLog.setFormatter(formatter);
            fileHandlerDebug.setFormatter(formatter);

            logger.addHandler(fileHandlerLog);
            debugger.addHandler(fileHandlerDebug);
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        logger.setUseParentHandlers(false);
        debugger.setUseParentHandlers(false);
    }

    public static void log(Level level, Class source, String string) {
        log(level, source.toString(), string);
    }

    public static void log(Level level, String source, String string) {
        System.out.println("logging: " + ABSOLUTE_PATH);
        logger.log(level, source + ": " + string);
    }

    public static void debug(Level level, Class source, String string) {
        debug(level, source.toString(), string);
    }

    public static void debug(Level level, String source, String string) {
        System.out.println("debugging: " + ABSOLUTE_PATH_DEBUG);
        debugger.log(level, source + ": " + string);
    }

}
