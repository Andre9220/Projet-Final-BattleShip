package model;

/**
 * Classe Cruiser qui hérite de la classe Ship
 * Repr�sente un destroyer
 * @author
 *
 */
public class Destroyer extends Ship {

    // d�termine si le destroyer poss�de encore sa fus�e �clairante ou non
    private boolean hasFlare;

    /**
     * Constructeur par défaut
     */
    public Destroyer() {
        super(Constantes.DESTROYER_LENGTH, Constantes.DESTROYER_FIREPOWER);
        ch = Constantes.DESTROYER_CHARS;
        this.hasFlare = true;
    }

    /**
     * @return ship type name
     */
    @Override
    public String toString() {
        return "Destroyer";
    }

    /**
     * @return hasFlare
     */
    public boolean canFlare() {
        return hasFlare;
    }

    /**
     * Modifie la valeur de hasFlare à Faux, il n'a pas donc de fusée éclairante
     */
    public void cantFlare() {
        this.hasFlare = false;
    }



}
