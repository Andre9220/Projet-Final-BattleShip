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
    public NavalBattleController() {
        this.battleView = new NavalBattleView();	// instancier l'objet battleView
        this.game = new Game();						// instancier l'objet game
        quit = false;
    }

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



    /**
     * Méthode qui renvoie la liste des cases touchées par un tir de navire
     * @param ship : le navire qui tire
     * @param coor : les coordonnées de la case cible
     * @return une liste de coordonnées de cases qui représente la zone touchée par le tir
     */
    private ArrayList<Coordinates> getShootArea(Ship ship, Coordinates coor) {
        ArrayList<Coordinates> area = new ArrayList<Coordinates>();
        if (ship instanceof Ironclad) {	// si le navire est un Cuirassé
            // prendre les 3 lignes (haut de la cible, la cible et bas de la cible)
            for (int i=coor.getRow()-1; i<=coor.getRow()+1; i++)
                if (i>=0 && i<Constantes.GRID_ROWS)
                    // pour chaque ligne prendre les 3 colonnes (gauche de la cible, la cible et droite de la cible)
                    for (int j=coor.getCol()-1; j<=coor.getCol()+1; j++)
                        if (j>=0 && j<Constantes.GRID_COLS)
                            area.add(new Coordinates(i, j));	// ajouter à la liste area

        } else if (ship instanceof Cruiser) { // si le navire est un Croiseur
            // prendre les 2 lignes (la cible et bas de la cible)
            for (int i=coor.getRow(); i<=coor.getRow()+1; i++)
                if (i<Constantes.GRID_ROWS)
                    // pour chaque ligne prendre les 2 colonnes (la cible et droite de la cible)
                    for (int j=coor.getCol(); j<=coor.getCol()+1; j++)
                        if (j<Constantes.GRID_COLS)
                            area.add(new Coordinates(i, j));	// ajouter à la liste area

        } else {		// sinon (le navire est un Destroyer ou un Sous-marin)
            // dans ce cas, seule la case ciblée sera touché
            area.add(coor);
        }
        return area;
    }


    /**
     * Méthode qui determine si le joueur a un navire non touch�
     * @param player : le joueur courant
     * @return vrai si au moins un navire non touché trouvé, faux sinon
     */
    public boolean hasShipNotHit(int player) {
        if (player == 0) {
            // parcourir la liste des navires
            for (int i=0; i<game.human.getFleet().length; i++) {
                // wasHit c.à.d health<length (navire touché), !(wasHit) veut dire non touché
                if (!game.human.getFleet()[i].wasHit())
                    return true;	// il suffit de trouvé un pour arrêter la boucle
            }
        } else {
            for (int i=0; i<game.computer.getFleet().length; i++) {
                if (!game.computer.getFleet()[i].wasHit())
                    return true;
            }
        }
        return false;
    }

    /**
     * Méthode qui permet de déplacer le navire "ship" selon la valeur de "moveBy"
     * @param board : la grille concernée par le déplacement
     * @param ship : le navire qu'on veut déplacer
     * @param moveBy : -1/1 correspond à Gauche/Droite ou Haut/Bas
     * @return vrai si le déplacement est effectué, faux sinon
     */

    public boolean moveShip(Board board, Ship ship, int moveBy) {
        // avoir les coordonnées du navire (1ère case occupée) dans les variables x et y
        int x = ship.getPos().getRow();
        int y = ship.getPos().getCol();
        // si le navire est placé verticalement dans la grille
        if (ship.isVertical()) {
            // et si le déplacement est vers Haut
            if (moveBy == -1) {
                if (x == 0)		// si le navire est deja placé dans la 1ère ligne,
                    // le déplacement est donc impossible
                    return false;

                // sinon, si la case en haut n'est pas vide, le d�placement n'est pas permis
                if (board.grid[x-1][y].getCh()!=Constantes.GRID_CHARS)
                    return false;

            } else {	// si c'est un déplacement vers le bas
                // et si le navire est placé tout en bas, le déplacement est donc impossible
                if (x+ship.getLength()+1 > board.nbrRows)
                    return false;
                // sinon, si la case en bas n'est pas vide, le déplacement n'est pas permis
                if (board.grid[x+ship.getLength()][y].getCh()!=Constantes.GRID_CHARS)
                    return false;
            }

        } else {	// si le navire est placé horizontalement dans la grille
            // et si le déplacement est vers Gauche
            if (moveBy == -1) {
                if (y == 0)		// si le navire est déjà placé dans la 1 ère colonne,
                    // le déplacement est donc impossible
                    return false;

                // sinon, si la case à gauche n'est pas vide, le déplacement n'est pas permis
                if (board.grid[x][y-1].getCh()!=Constantes.GRID_CHARS)
                    return false;

            } else {	// si c'est un déplacement vers la droite
                // et si le navire est placé à la limite de la grille, le déplacement est donc impossible
                if (y+ship.getLength()+1 > board.nbrCols)
                    return false;
                // sinon, si la case à droite n'est pas vide, le déplacement n'est pas permis
                if (board.grid[x][y+ship.getLength()].getCh()!=Constantes.GRID_CHARS)
                    return false;
            }
        }
        // si le déplacement est permis, on effectue le déplacement

        if (ship.isVertical()) {
            if (moveBy < 0) {	// si c'est un déplacement vers le haut
                // d�placer les cases occupées par le navire une par une
                // et puisque c'est un déplacement vers le haut,
                // on commence par la 1ère case pour ne pas écraser d'autre case occupée(par le navire)
                for (int i=0; i<ship.getArea().size(); i++) {
                    int xx = ship.getArea().get(i).getRow();
                    ship.getArea().get(i).setRow(xx + moveBy);

                    // effectuer le déplacement dans la grille
                    board.grid[xx+moveBy][y].setCh(board.grid[xx][y].getCh());
                }
                // vider la place de la dernière case déplacée vers le haut
                board.grid[x+ship.getLength()-1][y].setCh(Constantes.GRID_CHARS);

            } else {	// si c'est un déplacement vers le bas
                // d�placer les cases occupées par le navire une par une
                // et puisque c'est un déplacement vers le bas,
                // on commence par la dernière case pour ne pas écraser d'autre case occupée(par le navire)
                for (int i=ship.getArea().size()-1; i>=0; i--) {
                    int xx = ship.getArea().get(i).getRow();
                    ship.getArea().get(i).setRow(xx + moveBy);

                    // effectuer le déplacement dans la grille
                    board.grid[xx+moveBy][y].setCh(board.grid[xx][y].getCh());
                }
                // vider la case de la position précédente du navire
                board.grid[x][y].setCh(Constantes.GRID_CHARS);
            }

        } else {
            if (moveBy < 0) {	// si c'est un déplacement vers la gauche
                // déplacer les cases occupées par le navire
                // et puisque c'est un déplacement vers la gauche,
                // on commence par la 1ère case pour ne pas écraser d'autre case occupée(par le navire)
                for (int i=0; i<ship.getArea().size(); i++) {
                    int yy = ship.getArea().get(i).getCol();
                    ship.getArea().get(i).setCol(yy + moveBy);

                    // effectuer le déplacement dans la grille
                    board.grid[x][yy+moveBy].setCh(board.grid[x][yy].getCh());
                }
                // vider la place de la dernière case déplacée vers la gauche
                board.grid[x][y+ship.getLength()-1].setCh(Constantes.GRID_CHARS);

            } else {	// si c'est un déplacement vers la droite
                // d�placer les cases occupées par le navire
                // et puisque c'est un déplacement vers la droite,
                // on commence par la dernière case pour ne pas écraser d'autre case occupée(par le navire)
                for (int i=ship.getArea().size()-1; i>=0; i--) {
                    int yy = ship.getArea().get(i).getCol();
                    ship.getArea().get(i).setCol(yy + moveBy);

                    // effectuer le déplacement dans la grille
                    board.grid[x][yy+moveBy].setCh(board.grid[x][yy].getCh());
                }
                // vider la case de la position précédente du navire
                board.grid[x][y].setCh(Constantes.GRID_CHARS);
            }
        }
        return true;
    }

    /**
     * M�thode qui permet d'ex�cuter l'action de d�placement
     * la m�thode permet d'avoir les coordonn�es du navire concern� et la direction souhait�e
     * (demander au joueur si c'est un humain, ou g�n�rer al�atoirement si le joueur = Computer)
     * @param player : joueur qui veut d�placer un navire
     * @return vrai si le d�placement est effectu�, faux sinon
     */
    public boolean move(int player) {
        Coordinates currCoor;
        Ship ship = null;
        boolean moved = false;
        if (player == 0) {		// si le joueur = human
            do {
                int x = battleView.getRow("ship"); // avoir la ligne du navire
                if (x == -1) {		// quitter si le joueur tape "q"
                    quit = true;
                    break;
                }
                int y = battleView.getCol("ship");	// avoir la colonne du navire
                if (y == -1) {
                    quit = true;
                    break;
                }
                // l'utilisateur voit 1 .. 15 mais le programme traite 0 .. 14, c'est pour �a
                // qu'on doit soustraire 1 pour les valeurs de coordonn�es saisies par l'utilisateur
                currCoor = new Coordinates(x-1, y-1);

                // avoir le navire � laquelle appartient la case choisie
                ship = game.human.getShipFromCoor(currCoor);

                // si pas de navire dans la case choisie, afficher un message
                if (ship == null)
                    battleView.showMessage("Human : There is no ship at this position! Try again");

                    // si le navire choisi est touch� (ne doit pas etre d�plac�)
                else if (ship.wasHit())
                    battleView.showMessage("Human : Ship is hit, cant be moved! Try again");

                // r�p�ter tant que la case choisie ne correspond pas � un navire qui peut etre d�plac�
            } while (ship == null || ship.wasHit());

        } else {	// si c'est l'ordinateur
            // r�p�ter de g�n�rer des coordonn�es tant que ces derni�res
            // ne correspondent pas � un navire qui peut etre d�plac�
            do {
                currCoor = new Coordinates((new Random()).nextInt(Constantes.GRID_ROWS), (new Random()).nextInt(Constantes.GRID_COLS));
                ship = game.computer.getShipFromCoor(currCoor);

            } while (ship == null || ship.wasHit());
        }
        // si le jouer n'as pas interrompu le jeu
        if (!quit) {
            int moveBy;
            if (player == 0) {
                // avoir la direction de d�placement correspond au choix du joueur humain
                moveBy = battleView.getMoveBy(ship.isVertical());
                if (moveBy == 0) 	// si le joueur tape "q"
                    quit = true;
                else {  // sinon tenter de d�placer le navire
                    moved = moveShip(game.human.getOwnGrid(), ship, moveBy);
                    if (moved)
                        battleView.showMessage("Human : Ship moved.");
                }
            } else {
                // g�n�rer une direction al�atoirement pour le joueur Computer
                moveBy = (new Random()).nextInt(2) == 0 ? -1 : 1;
                moved = moveShip(game.computer.getOwnGrid(), ship, moveBy);	// tenter le d�placement
                if (moved)
                    battleView.showMessage("Computer : Ship moved.");
            }
            if (!quit && moved)		// afficher les grilles apr�s le d�placement
                battleView.showGrids(game, player, false, null);
        }
        return moved;
    }


    /**
     * M�thode qui permet d'ex�cuter l'action de tir
     * la m�thode permet d'avoir les coordonn�es du navire tireur et les coordonn�es de la cible
     * (demander au joueur si c'est un humain, ou g�n�rer al�atoirement si le joueur = Computer)
     * @param player : joueur qui veut d�placer un navire
     */
    public void fire(int player) {
        flare = false;
        Ship shooter = null;
        Coordinates coor = null;
        if (player == 0) {	// si le joueur = human
            do {
                int x = battleView.getRow("shooter ship");	// avoir la ligne du navire tireur
                if (x == -1) {
                    quit = true;
                    break;
                }
                int y = battleView.getCol("shooter ship");	// avoir la colonne du navire tireur
                if (y == -1) {
                    quit = true;
                    break;
                }
                coor = new Coordinates(x-1, y-1);
                // avoir le navire concern� par les coordonn�es donn�es
                shooter = game.human.getShipFromCoor(coor);

                // si les coordonn�es donn�es ne correspondent � aucun navire, afficher un message
                if (shooter == null)
                    battleView.showMessage("Human : There is no ship at this position! Try again");

                    // si les coordonn�es donn�es correspondent � un navire d�truit(ne peut pas tirer)
                else if (shooter.getHealth()==0)
                    battleView.showMessage("Human : This ship was totally destroyed! Try again");

                // r�p�ter tant que les coordonn�es fournies ne correspondent pas
                // � un navire qui peut tirer
            } while (shooter == null || shooter.getHealth()==0);

        } else {	// si c'est l'ordinateur
            // r�p�ter de g�n�rer des coordonn�es tant que ces derni�res
            // ne correspondent pas � un navire qui peut tirer
            do {
                coor = new Coordinates((new Random()).nextInt(Constantes.GRID_ROWS), (new Random()).nextInt(Constantes.GRID_COLS));
                shooter = game.computer.getShipFromCoor(coor);

            } while (shooter == null || shooter.getHealth()==0);
        }
        // si le jouer n'as pas interrompu le jeu
        if (!quit) {
            // voir si le navire tireur est un destroyer et si c'est son premir tir
            flare = shooter instanceof Destroyer && ((Destroyer) shooter).canFlare();

            if (player == 0) {
                int x = battleView.getRow("target");	// avoir la ligne de la case cible
                if (x == -1)
                    quit = true;
                else {
                    int y = battleView.getCol("target");	// avoir la colonne de la case cible
                    if (y == -1)
                        quit = true;
                    else
                        targetCoor = new Coordinates(x-1, y-1);
                }
            } else {
                // si c'est l'ordinateur, alors g�n�rer al�atoirement des coordonn�es de cible
                targetCoor = new Coordinates((new Random()).nextInt(Constantes.GRID_ROWS), (new Random()).nextInt(Constantes.GRID_COLS));
                // afficher l'action r�alis�e par le joueur computer
                battleView.showCompAction(flare, targetCoor);
            }
            if (!quit) {
                if (!flare) {	// si c'est pas une fus�e �clairante
                    ArrayList<Coordinates> shootArea;
                    // avoir la zone touch�e par le tir
                    shootArea = getShootArea(shooter, targetCoor);
                    Ship targetShip;
                    if (player == 0) {
                        // parcourir les cases touch�es par le tir
                        for (int i=0; i<shootArea.size(); i++) {
                            // avoir le navire concern� par chaque case touch�e
                            targetShip = game.computer.getShipFromCoor(shootArea.get(i));
                            // si la case touch�e en cours correspond � un navire
                            if (targetShip != null) {
                                // si la cible correspond � un sous-marin
                                // et le tireur n'est pas un sous-marin, alors passer � la case suivante
                                if (targetShip instanceof Submarine && !(shooter instanceof Submarine))
                                    continue;
                                // sinon
                                // effectuer les modifications n�c�ssaires sur le navire touch�
                                targetShip.destroyAt(shootArea.get(i));
                                // effectuer les modifications sur la grille du joueur computer
                                game.computer.destroyAt(shootArea.get(i));
                                // effectuer les modifications sur la grille Ennemi du joueur human
                                game.human.getEnemyGrid().destroy(shootArea.get(i));
                                // si le tir a caus� la perte du joueur computer
                                if (game.isOver()) {
                                    game.winner = game.human;
                                    battleView.showMessage("Human win!");
                                    break;
                                }

                            }
                        }

                    } else {	// si c'est le joueur Computer
                        // parcourir les cases touch�es par le tir
                        for (int i=0; i<shootArea.size(); i++) {
                            // avoir le navire concern� par chaque case touch�e
                            targetShip = game.human.getShipFromCoor(shootArea.get(i));
                            // si la case touch�e en cours correspond � un navire
                            if (targetShip != null) {
                                // si la cible correspond � un sous-marin
                                // et le tireur n'est pas un sous-marin, alors passer � la case suivante
                                if (targetShip instanceof Submarine && !(shooter instanceof Submarine))
                                    continue;
                                // sinon
                                // effectuer les modifications n�c�ssaires sur le navire touch�
                                targetShip.destroyAt(shootArea.get(i));
                                // effectuer les modifications sur la grille du joueur human
                                game.human.destroyAt(shootArea.get(i));
                                // effectuer les modifications sur la grille Ennemi du joueur computer
                                game.computer.getEnemyGrid().destroy(shootArea.get(i));
                                // si le tir a caus� la perte du joueur human
                                if (game.isOver()) {
                                    game.winner = game.computer;
                                    battleView.showMessage("Computer win!");
                                    break;
                                }
                            }
                        }
                    }
                } else {	// si c'est une fus�e �clairante
                    // le Destroyer ne pourra plus tirer par la fus�e �clairante
                    ((Destroyer) shooter).cantFlare();
                }
                // afficher les grilles en fonction des valeurs donn�es en param�tres
                battleView.showGrids(game, player, flare, targetCoor);
            }
        }
    }





}
