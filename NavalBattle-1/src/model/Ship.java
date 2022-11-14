package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe abstraite Ship qui représente un navire
 *
 */
public abstract class Ship {
    protected int length;	// nombre de cases occupées par le navire
    protected int health;	// nombre de cases saines
    protected int firePower;	// puissance de tir
    protected Coordinates position;		// coordonnées de la 1ère case occupée par le navire
    protected boolean vertical;		// vrai si le navire est placé verticalement, faux sinon
    protected ArrayList<Coordinates> area;	// la liste des coordonnées des cases occupées par le navire
    protected char ch;	// le caractère représentant le navire

    /**
     * Constructeur
     * @param length : longueur de navire (nbr de cases)
     * @param firePower : puissance de tir
     */
    protected Ship(int length, int firePower) {
        this.length = length;
        this.health = length;
        this.firePower = firePower;
        // créer un objet Random pour générer aléatoirement l'orientation de navire
        Random rd = new Random();
        this.vertical = rd.nextBoolean();

        this.area = new ArrayList<Coordinates>();
    }

    /**
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return fire Power
     */
    public int getFirePower() {
        return firePower;
    }

    /**
     * @return position (1ère case du navire)
     */
    public Coordinates getPos() {
        return position;
    }

    /**
     * modifier la position
     * @param pos : la nouvelle position
     */
    public void setPos(Coordinates pos) {
        this.position = pos;
    }

    /**
     * @return l'orientation : true si vertical, false si horizental
     */
    public boolean isVertical() {
        return vertical;
    }

    /**
     * Méthode qui determine si le navire occupe la case passée en paramètre
     * @param coor : la case (coordonnées)
     * @return vrai si le zone occupée par le navire comprend la case donnée, faux sinon
     */
    public boolean occupe(Coordinates coor) {
        return area.contains(coor);
    }

    /**
     * @return ch : le caractère qui représente le navire
     */
    public char getChar() {
        return ch;
    }

    /**
     * Méthode qui modifie (dans la zone occupée) la valeur de la case vers "détruite"
     * et décrémente la valeur de health (la santé du navire)
     * @param coor : Coordonnée sur laquelle on va tiré
     */
    public void destroyAt(Coordinates coor) {
        if (!area.get(area.indexOf(coor)).isDestroyed()) {
            if (health>0)
                health--;
            area.get(area.indexOf(coor)).destroy();
        }
    }

    /**
     * Méthode qui determine si le navire est touché
     * @return vrai si le navire est touché, faux sinon
     */
    public boolean wasHit() {
        return health < length;
    }

    /**
     * @return area : la zone occupée par le navire
     */
    public ArrayList<Coordinates> getArea() {
        return area;
    }

    /**
     * Modifie la valeur de health
     * @param health : Nouvel attribut à set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Modifie la position du navire
     * @param position : (la 1ère case occupée)
     */
    public void setPosition(Coordinates position) {
        this.position = position;
    }

    /**
     * Modifie l'orientation du navire
     * @param vertical : Nouvel attribut à set
     */
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    /*DONE*/


}
