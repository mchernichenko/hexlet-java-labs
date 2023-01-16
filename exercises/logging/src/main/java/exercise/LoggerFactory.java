package exercise;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerFactory {
    private static final String LOGGER_CONFIG_FILE_PATH = "log.config";
    private static final String LOGGER_CONFIG_FILE_PATH1 = "java/exercises/logging/log.config";

    private static boolean isLoggerConfigUploaded = false;

    public static Logger getLogger(Class clazz) {
        if (!isLoggerConfigUploaded) {
            try {
                uploadLogConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Logger.getLogger(clazz.getName());
    }

    private static void uploadLogConfig() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(LOGGER_CONFIG_FILE_PATH);
        LogManager.getLogManager().readConfiguration(fileInputStream);
        isLoggerConfigUploaded = true;
    }
}
