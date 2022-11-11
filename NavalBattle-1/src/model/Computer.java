package model;

/**
 * Classe Computer qui hérite de la classe Player
 * représente le joueur Ordinateur
 * @author
 *
 */
public class Computer extends Player {

    /**
     * Constructeur
     */
    public Computer() {
        super("Computer");
    }

    @Override
    public String toString() {
        return super.type;
    }

}
/*Done*/