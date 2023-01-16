
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.util.logging.LogManager;
// import java.util.logging.Logger;

// public class LoggerFactory {
//     // Путь до файла с конфигурацией логгера
//     private static final String LOGGER_CONFIG_FILE_PATH = "log.config";
//     private static boolean isLoggerConfigUploaded = false;

//     // Метод получения логгера
//     public static Logger getLogger(Class clazz) {
//         // Загружаем конфигурацию логгера
//         if (!isLoggerConfigUploaded) {
//             try {
//                 uploadLogConfig();
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//         // Возвращаем настроенный логгер
//         return Logger.getLogger(clazz.getName());
//     }

//     // Метод загрузки конфигурации логгера
//     private static void uploadLogConfig() throws IOException {
//         FileInputStream fileInputStream = new FileInputStream(LOGGER_CONFIG_FILE_PATH);
//         LogManager.getLogManager().readConfiguration(fileInputStream);
//         isLoggerConfigUploaded = true;
//     }
// }

// // Файл конфигурации

// // Обработчики
// // Вывод логов в файл и в консоль
// handlers = java.util.logging.FileHandler, java.util.logging.ConsoleHandler

// // Настраиваем обработчик FileHandler, который пишет логи в файл
// // Уровень логгирования
// java.util.logging.FileHandler.level     = INFO
// // Формат вывода
// java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter
// java.util.logging.FileHandler.append    = true
// // Файл, в который будет происходить запись лога
// java.util.logging.FileHandler.pattern   = log.txt


// // Сервлет

// public class WelcomeServlet extends HttpServlet {
//     // Получаем логгер
//     private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeServlet.class);

//     @Override
//     public void doGet(HttpServletRequest request,
//                       HttpServletResponse response)
//                 throws IOException, ServletException {
//         // Производим запись лога с уровнем INFO
//         // При каждом GET запросе на стартовую страницу, в файл будет записываться лог
//         LOGGER.log(Level.INFO, "Страница загружена");

//         PrintWriter out = response.getWriter();
//         out.println("Hello, Hexlet!");
//     }
// }
