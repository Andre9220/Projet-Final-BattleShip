package model;

/**
 * Classe Cruiser qui hérite de la classe Ship
 * Représente un sous-marin
 *
 */
public class Submarine extends Ship {

    /**
     * Constructeur par défaut
     */
    public Submarine() {
        super(Constantes.SUBMARINE_LENGTH, Constantes.SUBMARINE_FIREPOWER);
        ch = Constantes.SUBMARINE_CHARS;
    }

    /**
     * @return ship type name
     */
    @Override
    public String toString() {
        return "Submarine";
    }

    /*DONE*/

}
