package by.gsu.bal;

public class Constants {

    public static final String CREATE_DIRECTORIES_TABLE = """
            CREATE TABLE IF NOT EXISTS directories (
              id bigint NOT NULL AUTO_INCREMENT,
              parent_directory bigint,
              name varchar(255) NOT NULL,
              PRIMARY KEY (id),
              FOREIGN KEY (parent_directory) REFERENCES directories (id)
              ON DELETE CASCADE
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
              ON DELETE CASCADE
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
     * ?????????? ?? ???????? ?????????? ???? ???????????????? ?????????? ?? ?????????????? ?????????????? ????????.
     */
    public static final String FIND_FILES_REGEX = """
            """;

}
