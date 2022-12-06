package by.gsu.bal;

public class File {
    private long id;
    private long parentId;
    private String name;
    private long size;

    public File(long id, long parentId, String name, long size) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.size = size;
    }

    public File(long parentId, String name, long size) {
        this.parentId = parentId;
        this.name = name;
        this.size = size;
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
