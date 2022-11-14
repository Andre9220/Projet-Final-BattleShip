package model;

/**
 * Classe Coordinates qui représente une case dans la grille
 *
 */
public class Coordinates {
	private int row;	// la ligne de la case
	private int col;	// la colonne de la case
	private boolean destroyed;	// détruite ou non
	private Character ch;	// le caractère de la case
	
	/**
	 * Constructeur à seulement deux paramètres (la ligne et la colonne)
	 * @param x : Ligne de la case
	 * @param y : Colonne de la case
	 */
	public Coordinates(int x, int y) {
		this.row = x;
		this.col = y;
		this.ch = null;
		this.destroyed = false;
	}
	
	/**
	 * Constructeur à trois paramètres (la ligne, la colonne et le caractère)
	 * @param x : Ligne de la case
	 * @param y : Colonne de la case
	 * @param ch : caractère de la case
	 */
	public Coordinates(int x, int y, char ch) {
		this.row = x;
		this.col = y;
		this.ch = ch;
		this.destroyed = false;
	}
	
	/**
	 * redéfinition de la méthode equals pour l'exploiter dans la méthode
	 * "contains(Coordinates)" du champ "area"
	 * @param secondCoor : Coordonnées à testé avec celle passé en paramètre
	 */
	@Override
	public boolean equals(Coordinates secondCoor) {
		return (this.row == ((Coordinates)secondCoor).row && this.col == ((Coordinates)secondCoor).col);
	}

	/**
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return col
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Modifie la valeur destroyed vers True 
	 * et la valeur ch vers le caractère FIRE_CHAR
	 */
	public void destroy() {
		destroyed = true;
		ch = Constantes.FIRE_CHAR;
	}
	
	/**
	 * @return destroyed
	 */
	public boolean isDestroyed() {
		return destroyed;
	}

	/**
	 * @return ch
	 */
	public Character getCh() {
		return ch;
	}

	/**
	 * Modifie la ligne
	 * @param row : paramètre set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Modifie la colonne
	 * @param col : paramètre set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Modifie le caractère
	 * @param ch : paramètre set
	 */
	public void setCh(Character ch) {
		this.ch = ch;
	}
	
	/**
	 * toString méthode : représentation textuelle d'une case
	 */
	@Override
	public String toString() {
		return row+","+col;
	}
}
