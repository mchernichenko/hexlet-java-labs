package exercise;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;
import java.io.IOException;

/* Класс используется только build.gradle, для генерации миграционных скриптов по модели (классов @Entity)
   Запускать скрипты нужно отдельно или, например, прикручивать liquibase.
   Скрипты создаются в src/main/resources/dbmigration
   в dbmigration/model - liquibase xml-файл, а в dbmigration/<db_name> - нативный sql
 */
public final class MigrationGenerator {

    public static void main(String[] args) throws IOException {
        // Создаём миграцию
        DbMigration dbMigration = DbMigration.create();

        // Указываем платформу, в нашем случае H2 и папку, в которой сформируется DDL
        // по умолчанию путь к папке 'resources/dbmigration'
        dbMigration.addPlatform(Platform.H2, "h2");
       // dbMigration.addPlatform(Platform.POSTGRES, "postgres");
       // dbMigration.addPlatform(Platform.ORACLE, "oracle");

        // т.к. корень проекта у меня не orm (субмодулем открыт), то дефалтовая папка
        // 'src/main/resources' ресурса не находится
        // для запуска из IDEA нужно явно указать относительный путь до ресурса.
        //dbMigration.setPathToResources("java/exercises/orm/src/main/resources");

        // Генерируем миграцию.
        // Для перегенерации удалите xml-файл из src/main/resources/dbmigration/model/
        dbMigration.generateMigration();
    }
}
