package by.gsu.bal;

public class Constants {

    public static final String CREATE_DIRECTORIES_TABLE = """
            CREATE TABLE IF NOT EXISTS directories (
              id bigint NOT NULL AUTO_INCREMENT,
              parent_directory bigint,
              name varchar(255) NOT NULL,
              PRIMARY KEY (id),
              FOREIGN KEY (parent_directory) REFERENCES directories (id)
              ON DELETE restrict
            );
            """;
    public static final String CREATE_FILES_TABLE = """
            CREATE TABLE IF NOT EXISTS files (
              id bigint NOT NULL AUTO_INCREMENT,
              parent_directory bigint,
              name varchar(255) NOT NULL,
              size bigint NOT NULL,
              PRIMARY KEY (id),
              FOREIGN KEY (parent_directory) REFERENCES directories (id)
              ON DELETE restrict
            );
            """;

    public static final String INSERT_DEFAULT_ROWS_INTO_DIRECTORIES = """
            INSERT INTO directories (parent_directory, name) VALUES (null, '/');
            INSERT INTO directories (parent_directory, name) VALUES (1, 'bin');
            INSERT INTO directories (parent_directory, name) VALUES (1, 'home');
            INSERT INTO directories (parent_directory, name) VALUES (3, 'download');
            INSERT INTO directories (parent_directory, name) VALUES (3, 'games');
            INSERT INTO directories (parent_directory, name) VALUES (3, 'documents');
            """;
    public static final String INSERT_DEFAULT_ROWS_INTO_FILES = """
            INSERT INTO files (parent_directory, name, size) VALUES (2, 'ls', 16000);
            INSERT INTO files (parent_directory, name, size) VALUES (2, 'cd', 8000);
            INSERT INTO files (parent_directory, name, size) VALUES (2, 'mv', 16000);
            INSERT INTO files (parent_directory, name, size) VALUES (2, 'cp', 16000);
            INSERT INTO files (parent_directory, name, size) VALUES (2, 'mkdir', 16000);
            INSERT INTO files (parent_directory, name, size) VALUES (5, 'worms', 64000);
            INSERT INTO files (parent_directory, name, size) VALUES (5, 'dune', 64000);
            INSERT INTO files (parent_directory, name, size) VALUES (5, 'C&C', 128000);
            INSERT INTO files (parent_directory, name, size) VALUES (6, 'BalJavaLab1', 64000);
            INSERT INTO files (parent_directory, name, size) VALUES (6, 'BalJavaLab2', 64000);
            INSERT INTO files (parent_directory, name, size) VALUES (6, 'BalJavaLab3', 64000);
            INSERT INTO files (parent_directory, name, size) VALUES (6, 'BalJavaLab4', 64000);
            """;

    /**
     *Определить полный путь заданного файла (каталога).
     */
    public static final String FIND_ABSOLUTE_PATH = """
            """;
    /**
     * Подсчитать количество файлов в заданном каталоге, включая вложенные файлы и каталоги.
     */
    public static final String COUNT_FILES_AND_CATALOGS = """
            """;
    /**
     * Подсчитать место, занимаемое на диске содержимым заданного каталога.
     */
    public static final String COUNT_SIZE_OF_CATALOG = """
            """;
    /**
     * Найти в базе файлы по заданной маске с выдачей полного пути.
     */
    public static final String FIND_FILES_REGEX = """
            """;
    /**
     * Переместить файлы и подкаталоги из одного каталога в другой.
     */
    public static final String MOVE_CATALOG = """
            """;
    /**
     * Удалить файлы и каталоги заданного каталога.
     */
    public static final String REMOVE_CATALOG = """
            """;

}
