package model;

public class Board {
    public int nbrRows;			// nbr de lignes
    public int nbrCols;			// nbr de colonnes
    public Coordinates[][] grid;	// matrice de cases


    /*constructeur à ajouter/


     */
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
        grid[coor.getRow()][coor.getCol()].setCh(ch);
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
}
