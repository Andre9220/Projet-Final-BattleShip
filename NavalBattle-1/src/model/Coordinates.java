package model;

/**
 * Classe Coordinates qui représente une case dans la grille
 * @author 
 *
 */
public class Coordinates {
	private int row;	// la ligne de la case
	private int col;	// la colonne de la case
	private boolean destroyed;	// détruite ou non
	private Character ch;	// le caractère de la case
	
	/**
	 * Constructeur à seulement deux paramètres (la ligne et la colonne)
	 * @param x
	 * @param y
	 */
	public Coordinates(int x, int y) {
		this.row = x;
		this.col = y;
		this.ch = null;
		this.destroyed = false;
	}
	
	/**
	 * Constructeur à trois paramètres (la ligne, la colonne et le caractère)
	 * @param x
	 * @param y
	 * @param ch
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
	 */
	@Override
	public boolean equals(Object secondCoor) {
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
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Modifie la colonne
	 * @param col
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Modifie le caractère
	 * @param ch
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
