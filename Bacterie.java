public class Bacterie {

    private EtatPathogene etat;

    public Bacterie() {

    }

    public void kill(RedBloodCell redBloodCell) {
        etat.kill(redBloodCell);
    }

}
