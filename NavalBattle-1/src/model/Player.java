package model;

import java.util.Random;

/**
 * Classe abstraite Player qui représente un joueur
 * @author
 *
 */
public class Player {


    protected String type;	// human ou computer
    protected Board ownGrid;	// la grille du joueur
    protected Board enemyGrid;	// la grille de l'ennemi
    protected Ship[] fleet;	// la flotte de navires du joueur
    int shipCounter;	// compteur de navires




    protected Player(String type) {
        this.type = type;

        // instancier les deux grilles
        this.ownGrid = new Board(Constantes.GRID_ROWS, Constantes.GRID_COLS, Constantes.GRID_CHARS);
        this.enemyGrid = new Board(Constantes.GRID_ROWS, Constantes.GRID_COLS, Constantes.GRID_CHARS);

        // initialiser la flotte
        this.fleet = new Ship[Constantes.FLEET_SIZE];
        shipCounter = 0;
        for (int i = 0; i<Constantes.IRONCLAD_NBR; i++) {
            this.fleet[shipCounter] = new Ironclad();
            shipCounter++;
        }
        for (int i = 0; i<Constantes.CRUISER_NBR; i++) {
            this.fleet[shipCounter] = new Cruiser();
            shipCounter++;
        }
        for (int i = 0; i<Constantes.DESTROYER_NBR; i++) {
            this.fleet[shipCounter] = new Destroyer();
            shipCounter++;
        }
        for (int i = 0; i<Constantes.SUBMARINE_NBR; i++) {
            this.fleet[shipCounter] = new Submarine();
            shipCounter++;
        }
    }


    /*Faire la méthode pour placer les bateaux*/



    /**
     * @param coor : les coordonnées de la case
     * @return vrai si l'un des navires de la flotte occupe la case coor, faux sinon
     */
    private boolean isOccuped(Coordinates coor) {
        for (int i=0; i<shipCounter; i++) {
            if (fleet[i].area.contains(coor))
                return true;
        }
        return false;
    }

    /**
     * @return la grilles du joueur
     */
    public Board getOwnGrid() {
        return ownGrid;
    }

    /**
     * @return la grilles de l'ennemi
     */
    public Board getEnemyGrid() {
        return enemyGrid;
    }

    /**
     * @return la flotte du joueur
     */
    public Ship[] getFleet() {
        return fleet;
    }

    /**
     * @return le nombre de navires dans la flotte
     */
    public int getShipCounter() {
        return shipCounter;
    }

    /**
     * @param coor : les coordonn�es de la case
     * @return : le navire dans la flotte qui occupe la case coor,
     * 			null si aucun navire ne l'occupe
     */
    public Ship getShipFromCoor(Coordinates coor) {
        for (int i=0; i<fleet.length; i++) {
            if (fleet[i].occupe(coor))
                return fleet[i];
        }
        return null;
    }

    /**
     * Modifie la valeur du champ "destroyed" dans la case de la grille
     * @param coor : la case concern�e
     */
    public void destroyAt(Coordinates coor) {
        ownGrid.destroy(coor);
    }

    /**
     * @return le nombre total des cases saines dans la flotte (la sant� de la flotte)
     */
    public int getFleetHealth() {
        int health=0;
        for (int i=0; i<fleet.length; i++) {
            health += fleet[i].getHealth();
        }
        return health;
    }

}
