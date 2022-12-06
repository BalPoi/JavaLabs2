package by.gsu.bal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetter {

    private final Connection conn;

    public DBSetter(Connection conn) {
        this.conn = conn;
    }

    public int insertFile(File file) throws SQLException {
        try(Statement statement = conn.createStatement()) {
            return statement.executeUpdate("""
                    INSERT INTO files (parent_directory, name, size)
                    VALUES (%d, '%s', %d)
                    """.formatted(file.getParentId(), file.getName(), file.getSize()));
        }
    }
    public int updateFile(long fileId, File newFile) throws SQLException {
        try(Statement statement = conn.createStatement()) {
            return statement.executeUpdate("""
                    UPDATE files
                    SET parent_directory=%d, name='%s', size=%d
                    WHERE id=%d
                    """.formatted(newFile.getParentId(), newFile.getName(), newFile.getSize(), fileId));
        }
    }
    public int deleteFile(long id) throws SQLException {
        try(Statement statement = conn.createStatement()) {
            return statement.executeUpdate("""
                    DELETE FROM files
                    WHERE id = %d
                    """.formatted(id));
        }
    }

    public int insertDirectory(Directory directory) throws SQLException {
        try(Statement statement = conn.createStatement()) {
            return statement.executeUpdate("""
                    INSERT INTO directories (parent_directory, name)
                    VALUES (%d, '%s')
                    """.formatted(directory.getParentId(), directory.getName()));
        }
    }
    public int updateDirectory(long dirId, Directory newDirectory) throws SQLException {
        try(Statement statement = conn.createStatement()) {
            return statement.executeUpdate("""
                    UPDATE directories
                    SET parent_directory=%d, name='%s'
                    WHERE id=%d
                    """.formatted(newDirectory.getParentId(), newDirectory.getName(), dirId));
        }
    }

}
