package model;

/**
 * Classe Human qui hérite de la classe Player
 * représente le joueur humain
 * @author
 *
 */
public class Human  extends Player {

    /**
     * Constructeur
     */
    public Human() {
        super("Human");
    }

    @Override
    public String toString() {
        return super.type;
    }

}
/*Done*/