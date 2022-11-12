package controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import model.Board;
import model.Constantes;
import model.Coordinates;
import model.Cruiser;
import model.Destroyer;
import model.Game;
import model.Ironclad;
import model.Ship;
import model.Submarine;
import view.NavalBattleView;

/**
 *
 * Classe NavalBattleController : la classe qui control le jeu
 * et execute les actions du joueur
 * @author
 */
public class NavalBattleController {


    public NavalBattleView battleView;  // objet de la classe NavalBattleView
    public Game game;					// objet de la classe Game
    private boolean flare = false;		// drapeau pour dire que l'action est une fusée éclairante
    private Coordinates targetCoor;		// coordonnées de la case cible
    private boolean quit;

    /**
     * Constructeur
     */
    /*public NavalBattleController() {}*/


    /**
     * Méthode qui permet de Lancer une partie (nouvelle ou chargée)
     */
    /*public void runGame() {}*/


    /**
     * Méthode qui permet d'afficher le menu principal et exécuter le choix du joueur
     */
    /*public void start() {}*/



    /*public void runTurn(int player) {*/
    /**
     * Méthode qui permet de lancer un tour du jeu
     * @param player : le joueur courant (0 pour Human et 1 pour Computer)
     */

    public boolean moveShip(Board board, Ship ship, int moveBy) {
        // avoir les coordonn�es du navire (1�re case occup�e) dans les variables x et y
        int x = ship.getPos().getRow();
        int y = ship.getPos().getCol();
        // si le navire est plac� verticalement dans la grille
        if (ship.isVertical()) {
            // et si le d�placement est vers Haut
            if (moveBy == -1) {
                if (x == 0)		// si le navire est d�ja plac� dans la 1�re ligne,
                    // le d�placement est donc impossible
                    return false;

                // sinon, si la case en haut n'est pas vide, le d�placement n'est pas permis
                if (board.grid[x-1][y].getCh()!=Constantes.GRID_CHARS)
                    return false;

            } else {	// si c'est un d�placement vers le bas
                // et si le navire est plac� tout en bas, le d�placement est donc impossible
                if (x+ship.getLength()+1 > board.nbrRows)
                    return false;
                // sinon, si la case en bas n'est pas vide, le d�placement n'est pas permis
                if (board.grid[x+ship.getLength()][y].getCh()!=Constantes.GRID_CHARS)
                    return false;
            }

        } else {	// si le navire est plac� horizontalement dans la grille
            // et si le d�placement est vers Gauche
            if (moveBy == -1) {
                if (y == 0)		// si le navire est d�ja plac� dans la 1�re colonne,
                    // le d�placement est donc impossible
                    return false;

                // sinon, si la case � gauche n'est pas vide, le d�placement n'est pas permis
                if (board.grid[x][y-1].getCh()!=Constantes.GRID_CHARS)
                    return false;

            } else {	// si c'est un d�placement vers la droite
                // et si le navire est plac� � la limite de la grille, le d�placement est donc impossible
                if (y+ship.getLength()+1 > board.nbrCols)
                    return false;
                // sinon, si la case � droite n'est pas vide, le d�placement n'est pas permis
                if (board.grid[x][y+ship.getLength()].getCh()!=Constantes.GRID_CHARS)
                    return false;
            }
        }
        // si le d�placement est permis, on effectue le d�placement

        if (ship.isVertical()) {
            if (moveBy < 0) {	// si c'est un d�placement vers le haut
                // d�placer les cases occup�es par le navire une par une
                // et puisque c'est un d�placement vers le haut,
                // on commence par la 1�re case pour ne pas �craser d'autre case occup�e(par le navire)
                for (int i=0; i<ship.getArea().size(); i++) {
                    int xx = ship.getArea().get(i).getRow();
                    ship.getArea().get(i).setRow(xx + moveBy);

                    // effectuer le d�placement dans la grille
                    board.grid[xx+moveBy][y].setCh(board.grid[xx][y].getCh());
                }
                // vider la place de la derni�re case d�plac�e vers le haut
                board.grid[x+ship.getLength()-1][y].setCh(Constantes.GRID_CHARS);

            } else {	// si c'est un d�placement vers le bas
                // d�placer les cases occup�es par le navire une par une
                // et puisque c'est un d�placement vers le bas,
                // on commence par la derni�re case pour ne pas �craser d'autre case occup�e(par le navire)
                for (int i=ship.getArea().size()-1; i>=0; i--) {
                    int xx = ship.getArea().get(i).getRow();
                    ship.getArea().get(i).setRow(xx + moveBy);

                    // effectuer le d�placement dans la grille
                    board.grid[xx+moveBy][y].setCh(board.grid[xx][y].getCh());
                }
                // vider la case de la position pr�c�dente du navire
                board.grid[x][y].setCh(Constantes.GRID_CHARS);
            }

        } else {
            if (moveBy < 0) {	// si c'est un d�placement vers la gauche
                // d�placer les cases occup�es par le navire
                // et puisque c'est un d�placement vers la gauche,
                // on commence par la 1�re case pour ne pas �craser d'autre case occup�e(par le navire)
                for (int i=0; i<ship.getArea().size(); i++) {
                    int yy = ship.getArea().get(i).getCol();
                    ship.getArea().get(i).setCol(yy + moveBy);

                    // effectuer le d�placement dans la grille
                    board.grid[x][yy+moveBy].setCh(board.grid[x][yy].getCh());
                }
                // vider la place de la derni�re case d�plac�e vers la gauche
                board.grid[x][y+ship.getLength()-1].setCh(Constantes.GRID_CHARS);

            } else {	// si c'est un d�placement vers la droite
                // d�placer les cases occup�es par le navire
                // et puisque c'est un d�placement vers la droite,
                // on commence par la derni�re case pour ne pas �craser d'autre case occup�e(par le navire)
                for (int i=ship.getArea().size()-1; i>=0; i--) {
                    int yy = ship.getArea().get(i).getCol();
                    ship.getArea().get(i).setCol(yy + moveBy);

                    // effectuer le d�placement dans la grille
                    board.grid[x][yy+moveBy].setCh(board.grid[x][yy].getCh());
                }
                // vider la case de la position pr�c�dente du navire
                board.grid[x][y].setCh(Constantes.GRID_CHARS);
            }
        }
        return true;
    }
}
