package by.gsu.bal;

import java.sql.*;
import java.util.ArrayList;

public class DBGetter {
    private final Connection conn;

    public DBGetter(Connection connection) {
        this.conn = connection;
    }

    public File getFile(long id) throws SQLException {
        try (PreparedStatement pr = conn.prepareStatement("SELECT * FROM files WHERE id = ?")) {
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            rs.next();
            long fileId = rs.getLong("id");
            long parentId = rs.getLong("parent_directory");
            String name = rs.getString("name");
            long size = rs.getLong("size");
            return new File(fileId, parentId, name, size);
        }
    }

    public Directory getDirectory(long id) throws SQLException {
        try (PreparedStatement pr = conn.prepareStatement("SELECT * FROM directories WHERE id = ?")) {
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            rs.next();
            long directoryId = rs.getLong("id");
            String parentId = rs.getString("parent_directory");
            String name = rs.getString("name");
            return new Directory(directoryId, parentId, name);
        }
    }

    //    public ResultSet getAllFiles() throws SQLException {
//        try(Statement statement = conn.createStatement()) {
//            return statement.executeQuery("SELECT * FROM files;");
//        }
//    }
//    public ResultSet getAllDirectories() throws SQLException {
//        try(Statement statement = conn.createStatement()) {
//            return statement.executeQuery("SELECT * FROM directories;");
//        }
//    }
    public boolean areThereFiles() throws SQLException {
        try (Statement statement = conn.createStatement()) {
            return statement.executeQuery("SELECT * FROM files;").next();
        }
    }

    public boolean areThereDirectories() throws SQLException {
        try (Statement statement = conn.createStatement()) {
            return statement.executeQuery("SELECT * FROM directories;").next();
        }
    }

    public String getAbsolutePathDirectory(long id) throws SQLException {
        Directory dir = getDirectory(id);
        var sb = new StringBuilder();

        if (dir.getParentId() == 0)
            return sb.insert(0, dir.getName()).toString();

        sb.insert(0, dir.getName() + '/');
        return sb.insert(0, getAbsolutePathDirectory(dir.getParentId())).toString();
    }

    public String getAbsolutePathFile(long id) throws SQLException {
        File file = getFile(id);
        var sb = new StringBuilder();

        sb.insert(0, file.getName());

        long parentId = file.getParentId();
        return sb.insert(0, getAbsolutePathDirectory(parentId)).toString();
    }

    public ArrayList<Directory> getChildrenDirectories(long id) throws SQLException {
        var children = new ArrayList<Directory>();
        try (PreparedStatement pr = conn.prepareStatement("SELECT * FROM directories WHERE parent_directory = ?")) {
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                children.add(new Directory(
                        rs.getLong("id"),
                        rs.getString("parent_directory"),
                        rs.getString("name")));
            }
            return children;
        }
    }
    public ArrayList<Directory> getChildrenDirectoriesRecursive(long id) throws SQLException {
        var childrenAll = new ArrayList<Directory>();
        var children = getChildrenDirectories(id);
        if (children.size() == 0) return new ArrayList<>();
        childrenAll.addAll(children);
        for (Directory dir : children) {
            childrenAll.addAll(getChildrenDirectoriesRecursive(dir.getId()));
        }
        return childrenAll;
    }
    public ArrayList<File> getChildrenFiles(long id) throws SQLException {
        var children = new ArrayList<File>();
        try (PreparedStatement pr = conn.prepareStatement("SELECT * FROM files WHERE parent_directory = ?")) {
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                children.add(new File(
                        rs.getLong("id"),
                        rs.getLong("parent_directory"),
                        rs.getString("name"),
                        rs.getLong("size")));
            }
            return children;
        }
    }
    public int countObjects(long id) throws SQLException {
        int counter = 0;
        counter += getChildrenFiles(id).size();
        var childDirs = getChildrenDirectoriesRecursive(id);
        counter += childDirs.size();
        for (Directory dir : childDirs) {
            counter += getChildrenFiles(dir.getId()).size();
        }
        return counter;
    }

    public long countDirectorySize(long id) throws SQLException {
        long counter = 0;
        ArrayList<File> allFiles = new ArrayList<File>();
        allFiles.addAll(getChildrenFiles(id));
        var childDirs = getChildrenDirectoriesRecursive(id);
        for (Directory dir : childDirs) {
            allFiles.addAll(getChildrenFiles(dir.getId()));
        }

        for (File file : allFiles) {
            counter += file.getSize();
        }
        return counter;
    }

    public int deleteDirectory(long id) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            return statement.executeUpdate("DELETE FROM directories WHERE id = %d".formatted(id));
        }
    }

    public int moveDirectory(long dirId, long targetDirId) throws SQLException {
        if (dirId == 1) throw new RuntimeException("Attempting to move the root.");
        Directory target = getDirectory(targetDirId);
        try (Statement statement = conn.createStatement()) {
            return statement.executeUpdate("UPDATE directories SET parent_directory=%d WHERE id=%d".formatted(target.getId(), dirId));
        }
    }

    public ArrayList<String> getFilesAbsolutePath(String regex) throws SQLException {
        var paths = new ArrayList<String>();
        try (PreparedStatement pr = conn.prepareStatement("SELECT * FROM files WHERE name ~ ?")) {
            pr.setString(1, regex);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                paths.add(getAbsolutePathFile(rs.getLong("id")));
            }
            return paths;
        }
    }

}
