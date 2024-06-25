public class Bacteria extends Entite {

    private EtatPathogene etat;

    public Bacteria() {

    }

    public void kill(RedBloodCell redBloodCell) {
        etat.kill(redBloodCell);
    }

}
