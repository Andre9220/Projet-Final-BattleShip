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
     * @param nbrRows
     * @param nbrCols
     * @param ch
     * Permet d'initialiser un plateau de jeu avec le bon nombre de lignes et de colonnes
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
     * @param coor
     * @param ch
     */
    public void setChar(Coordinates coor, char ch) {
        grid[coor.getRow()][coor.getCol()].setCh(ch);  //
    }

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
