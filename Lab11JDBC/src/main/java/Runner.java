import by.gsu.bal.Constants;
import by.gsu.bal.DBGetter;
import by.gsu.bal.PoolConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class Runner {
    public static void main(String[] args) throws SQLException {

        ResourceBundle rb;
        try {
            rb = ResourceBundle.getBundle("config");
        } catch (MissingResourceException e) {
            throw new RuntimeException("The config.properties file not found");
        }

        var url = rb.getString("db.jdbcUrl");
        var user = rb.getString("db.user");
        var password = rb.getString("db.password");

        System.out.println("=========================================================================================");
        System.out.printf("Trying to connect to the database(%s) as %s@%s...\t", url, user, password);
        PoolConnection.init(url, user, password);

        try(Connection conn = PoolConnection.getConnection()) {
            boolean isValid = conn.isValid(0);
            System.out.printf("%s\n", isValid ? "Success\n" : "Fail\n");
            if (!isValid) throw new RuntimeException("Failed to connect to the database.");

            try (Statement statement = conn.createStatement()) {
                statement.execute(Constants.CREATE_DIRECTORIES_TABLE);
                statement.execute(Constants.CREATE_FILES_TABLE);
            }

            DBGetter dbg = new DBGetter(conn);

            if (!dbg.areThereDirectories()) {
                try (Statement statement = conn.createStatement()) {
                    statement.execute(Constants.INSERT_DEFAULT_ROWS_INTO_DIRECTORIES);
                }
                System.out.println("Default directories have been added to the DB.");
            }
            if (!dbg.areThereFiles()) {
                try (Statement statement = conn.createStatement()) {
                    statement.execute(Constants.INSERT_DEFAULT_ROWS_INTO_FILES);
                }
                System.out.println("Default files have been added to the DB.");
            }

            System.out.println("[1] Find absolute path:");
            System.out.println(dbg.getAbsolutePathFile(12));
            System.out.println(dbg.getAbsolutePathDirectory(6));

            System.out.println("[2] Count files and directories:");
            System.out.println(dbg.countObjects(1));

            System.out.println("[3] Count size of directory:");
            long dirSize = dbg.countDirectorySize(1);
            System.out.printf("%d bits or %d Bytes or %d KB\n", dirSize, dirSize / 8, dirSize / 8 / 1024);

            System.out.println("=========================================================================================");
        }
    }
}