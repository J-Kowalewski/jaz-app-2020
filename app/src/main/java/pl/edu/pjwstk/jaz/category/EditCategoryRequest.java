package pl.edu.pjwstk.jaz.category;

public class EditCategoryRequest {
    private String name;
    private String newName;

    public EditCategoryRequest(String name, String newName) {
        this.name = name;
        this.newName = newName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
