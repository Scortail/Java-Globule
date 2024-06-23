import java.util.ArrayList;

public abstract class Cell {
    private ArrayList<String> parts;
    protected int position;
    boolean alive;
    protected final int MAX_POSITION = 100;

    public Cell() {
        this.parts = new ArrayList<>();
        this.position = 0;
        this.alive = true;
    }

    public void addPart(String part) {
        this.parts.add(part);
    }

    public void showParts() {
        System.out.println("Cell consists of: " + String.join(", ", parts));
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public abstract void updatePosition();
}