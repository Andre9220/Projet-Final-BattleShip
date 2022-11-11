package model;

/**
 * Classe Cruiser qui hérite de la classe Ship
 * Représente un cuirassé
 * @author
 *
 */
public class Ironclad extends Ship {

    /**
     * Constructeur par défaut
     */
    public Ironclad() {
        super(Constantes.IRONCLAD_LENGTH, Constantes.IRONCLAD_FIREPOWER);
        ch = Constantes.IRONCLAD_CHARS;
    }

    /**
     * @return ship type name
     */
    @Override
    public String toString() {
        return "Ironclad";
    }



    /*DONE*/
}
