public class RedBloodCell extends Cell {

    private boolean oxygen;

    public RedBloodCell(int position) {
        this.position = position;
    }

    public void takeOxygen() {
        if (position == 0) {
            oxygen = true;
        }
    }

    // Ajouter l'oxygen au system
    public void giveOxygen() {
        if (position == MAX_POSITION) {
            oxygen = false;
        }
    }

    @Override
    public void updatePosition() {
        if (position >= MAX_POSITION) {
            position = 0;
        } else {
            position += 1;
        }
    }

}
