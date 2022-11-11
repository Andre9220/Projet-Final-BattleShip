package model;

/**
 * Classe Cruiser qui hérite de la classe Ship
 * Représente un croiseur
 * @author
 *
 */
public class Cruiser extends Ship {

    /**
     * Constructeur par défaut
     */
    public Cruiser() {
        super(Constantes.CRUISER_LENGTH, Constantes.CRUISER_FIREPOWER);
        ch = Constantes.CRUISER_CHARS;
    }

    /**
     * @return ship type name
     */
    @Override
    public String toString() {
        return "Cruiser";
    }

    /*DONE*/

}
