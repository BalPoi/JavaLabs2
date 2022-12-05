package by.gsu.bal;

public class Directory {
    private long id;
    private long parentId;
    private String name;

    public Directory(long id, long parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
    public Directory(long id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId == null ? 0 : Long.parseLong(parentId);
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
