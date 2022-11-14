package model;

import java.io.IOException;
import java.util.Random;

/**
 * Classe abstraite Player qui représente un joueur
 *
 */
public class Player {


    protected String type;	// human ou computer
    protected Board ownGrid;	// la grille du joueur
    protected Board enemyGrid;	// la grille de l'ennemi
    protected Ship[] fleet;	// la flotte de navires du joueur
    int shipCounter;	// compteur de navires



    /**
     * Méthode qui permet de sauvegarder la partie dans le fichier "filename"
     * @param type : type du joueur (human ou computer)
     */
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


    /**
     * Méthode qui permet de placer aléatoirement
     * les navires du joueur sur sa propre grille
     */

    public void placeShips() {
        // parcourir la liste des navires
        for (int i=0; i<shipCounter; i++) {
            boolean canPlace = false;	// determine si le navire peut etre placé ou non
            int x, y;
            int l = fleet[i].getLength();
            // répéter tant que le navire ne peut pas etre placé
            do {
                fleet[i].area.clear();	// vider la zone occupé
                Random rd = new Random();

                // générer des coordonnées de position selon l'orientation du navire
                if (fleet[i].isVertical()) {
                    x = rd.nextInt(Constantes.GRID_ROWS-l+1);
                    y = rd.nextInt(Constantes.GRID_COLS);
                } else {
                    x = rd.nextInt(Constantes.GRID_ROWS);
                    y = rd.nextInt(Constantes.GRID_COLS-l+1);
                }
                Coordinates c = new Coordinates(x, y, fleet[i].getChar());
                fleet[i].setPos(c);
                int counter = 0;
                // parcourir les cases qui vont etre occupées par le navire
                // et voir si elles sont vides, sinon on refait le travail avec
                // une autre position
                while (x<Constantes.GRID_ROWS && y<Constantes.GRID_COLS && counter<l && !isOccuped(c)) {
                    fleet[i].area.add(c);	// ajouter la case à la zone occupée tant qu'elle est vide
                    if (fleet[i].isVertical()) {
                        x++;
                    } else {
                        y++;
                    }
                    c = new Coordinates(x, y, fleet[i].getChar());
                    counter++;
                }
                canPlace = !isOccuped(c);
            } while (!canPlace);	// tant qu'on a rencontré un obstacle (case occupée), on repète

            // mettre à jour les cases de la grille du joueur
            for (int j=0; j<fleet[i].area.size(); j++) {
                ownGrid.setChar(fleet[i].area.get(j), fleet[i].getChar());
            }
        }
    }



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
     * @param coor : les coordonnées de la case
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
     * @param coor : la case concernée
     */

    public void destroyAt(Coordinates coor) {
        ownGrid.destroy(coor);
    }

    /**
     * @return le nombre total des cases saines dans la flotte (la santé de la flotte)
     */
    public int getFleetHealth() {
        int health=0;
        for (int i=0; i<fleet.length; i++) {
            health += fleet[i].getHealth();
        }
        return health;
    }

    /*done*/

}
