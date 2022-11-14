/**
 * Classe Board qui représente une grille du jeu
 * @author
 *
 */

package model;

public class Board {
    public int nbrRows;			// nbr de lignes
    public int nbrCols;			// nbr de colonnes
    public Coordinates[][] grid;	// matrice de cases

    /**
     * Constructeur de la classe Board
     * Permet d'initialiser un plateau de jeu avec le bon nombre de lignes et de colonnes
     * @param nbrRows : Nombre de lignes
     * @param nbrCols : Nombre de colonnes
     * @param ch : caractère de la case
     */
    public Board(int nbrRows, int nbrCols, char ch) {
        this.nbrRows = nbrRows;
        this.nbrCols = nbrCols;
        // initialise les valeurs de la matrice
        this.grid = new Coordinates[nbrRows][nbrCols];
        for(int i=0; i<nbrRows; i++) {
            for (int j=0; j<nbrCols; j++) {
                grid[i][j] = new Coordinates(i, j, ch);
            }
        }
    }


    /**
     * Modifie la valeur "destroyed" de la case ver "vrai"
     * @param coor : la case dans la grille (matrice)
     */
    public void destroy(Coordinates coor) {
        grid[coor.getRow()][coor.getCol()].destroy();
    }

    /**
     * Modifie le caractère dans une case donnée en paramètre
     * @param coor : Case ciblé
     * @param ch : Caractère souhaité dans la case
     */
    public void setChar(Coordinates coor, char ch) {
        grid[coor.getRow()][coor.getCol()].setCh(ch);  //
    }

    /**
     * @param coor : les coordonnées de la case
     * @return : la case dans la grille qui correspond à "coor"
     */

    public Coordinates getCoor(Coordinates coor) {
        for(int i=0; i<nbrRows; i++) {
            for (int j=0; j<nbrCols; j++) {
                if (grid[i][j].equals(coor))
                    return grid[i][j];
            }
        }
        return null;
    }

    /* done */

}
