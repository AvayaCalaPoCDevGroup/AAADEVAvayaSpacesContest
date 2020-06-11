package service.SpacesContest.Beans;


/**
 *
 * @author umansilla
 */
public class Control {

    private final String name;
    private final String objectType;
    private final String checked;
    private final int row;
    private final int col;

    
    public Control(String name, String objectType, String checked, int row, int col) {
        this.name = name;
        this.objectType = objectType;
        this.checked = checked;
        this.row = row;
        this.col = col;
    }

    public String getName() {
        return this.name;
    }

    public String getObjectType() {
        return this.objectType;
    }

    public String getChecked() {
        return this.checked;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public String toString() {
        return this.name + ":r/c:" + row + "/" + col + ":" + this.checked;
    }
}
