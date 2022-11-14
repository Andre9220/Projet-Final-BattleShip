package view;

import java.util.Scanner;

import model.Constantes;
import model.Coordinates;
import model.Game;

/**
 * Classe qui représente l'interface entre l'utilisateur et le Controleur du jeu
 * elle permet d'afficher des messages et des menus vers le console
 * et de lire les entrées du clavier de l'utilisateur
 */
public class NavalBattleView {
    // créer un objet Scanner pour lire les entrée clavier
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructeur
     */
    public NavalBattleView() {

    }

    /**
     * Méthode privée qui affiche le menu principal
     * utilisée par la méthode getMenuOption()
     */
    private void showMenu() {
        System.out.println("*********************************************************************");
        System.out.println("*******************                               *******************");
        System.out.println("*******************       Naval Battle Game       *******************");
        System.out.println("*******************                               *******************");
        System.out.println("*********************************************************************\n");
        System.out.println("\t1. Play a game");
        System.out.println("\t2. Load a game");
        System.out.println("\t3. Help");
        System.out.println("\t4. Exit");
        System.out.println("Choose an option (1 to 4) : ");

    }

    /**
     * Méthode qui affiche le menu principal puis lit le choix de l'utilisateur
     * @return le choix de l'utilisateur (4 si le choix = 'q' ou 'Q')
     */
    public int getMenuOption() {
        String strChoice; int choice = 0;
        do {
            showMenu();
            strChoice = sc.nextLine();
            if (strChoice.equalsIgnoreCase("q"))
                choice = 4;
            else {
                try {
                    choice = Integer.valueOf(strChoice);
                } catch (Exception e) {
                    showMessage("invalid choice! try again");
                }

            }
        } while (choice < 1 || choice > 4);
        return choice;
    }

    /**
     * Méthode qui permet d'afficher la chaine passée en parametre
     * @param message
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Méthode qui permet d'afficher l'action réalisée par le joueur Computer lors du tir
     * (tir ou fusée éclairante, et les coordonnées de la cible)
     * @param targetCoor
     * @param isFlare
     */
    public void showCompAction(boolean isFlare, Coordinates targetCoor) {
        String s = "Computer";
        if (isFlare)
            s += " flare";
        else
            s += " shoot";
        System.out.printf("%s at target : (%d, %d)\n", s, targetCoor.getRow()+1, targetCoor.getCol()+1);
    }

    /**
     * Méthode privée utilisée pour demander une valeur dans une intervalle
     * @param message
     * @param min : valeur minimale
     * @param max : valeur maximale
     */
    private void askShipCoor(String message, int min, int max) {
        System.out.printf(message, min, max);
    }

    /**
     * Méthode pour afficher le menu d'action (tir ou déplacement)
     * et lire le choix du joueur
     * @return (1 pour d�élacement ou 2 pour tir, et -1 si le joueur tape 'q')
     */
    public int getMoveOrFire() {
        String strChoice; int choice = 0;
        do {
            System.out.println("\t1. Move ship");
            System.out.println("\t2. Fire");
            System.out.println("Choose an action (1 or 2) : ");
            strChoice = sc.nextLine();
            if (strChoice.equalsIgnoreCase("q")) {
                choice = -1;
                break;
            }
            else {
                try {
                    choice = Integer.valueOf(strChoice);
                } catch (Exception e) {
                    showMessage("invalid choice! try again");
                }
            }
        } while (choice < 1 || choice > 2);
        return choice;
    }

    /**
     * Méthode pour afficher le menu de direction (Up/Down ou Left/Right)
     * et lire le choix du joueur
     * @param isVertical
     * @return si le navire est plac� verticalement : 1 pour UP et 2 pour DOWN
     * 		   sinon : 1 pour LEFT et 2 pour RIGHT,
     *         -1 si le joueur tape 'q' ou 'Q'
     */
    public int getMoveBy(boolean isVertical) {
        String strChoice; int choice = 0;
        if (isVertical) {
            do {
                System.out.println("\t1. Move UP");
                System.out.println("\t2. Move DOWN");
                System.out.println("Choose a direction (1 or 2) : ");
                strChoice = sc.nextLine();
                if (strChoice.equalsIgnoreCase("q")) {
                    choice = -1;
                    break;
                }
                else {
                    try {
                        choice = Integer.valueOf(strChoice);
                    } catch (Exception e) {
                        showMessage("invalid choice! try again");
                    }
                }
            } while (choice < 1 || choice > 2);
        } else {
            do {
                System.out.println("\t1. Move LEFT");
                System.out.println("\t2. Move RIGHT");
                System.out.println("Choose a direction (1 or 2) : ");
                strChoice = sc.nextLine();
                if (strChoice.equalsIgnoreCase("q")) {
                    choice = -1;
                    break;
                }
                else {
                    try {
                        choice = Integer.valueOf(strChoice);
                    } catch (Exception e) {
                        showMessage("invalid choice! try again");
                    }
                }
            } while (choice < 1 || choice > 2);
        }
        if (choice == -1)
            return 0;
        else
            return choice == 1 ? -1 : 1;
    }

    /**
     * Méthode pour demander et lire le numéro de ligne d'une coordonnée
     * @param s : le message à afficher
     * @return le numéro de ligne, ou -1 si le joueur tape 'q' ou 'Q'
     */
    public int getRow(String s) {
        String strChoice; int choice = 0;
        do {
            askShipCoor("Type the "+s+" row (%d to %d) : ", 1, Constantes.GRID_ROWS);
            strChoice = sc.nextLine();
            if (strChoice.equalsIgnoreCase("q")) {
                choice = -1;
                break;
            }
            else {
                try {
                    choice = Integer.valueOf(strChoice);
                } catch (Exception e) {
                    showMessage("invalid choice! try again");
                }
            }
        } while (choice < 1 || choice > Constantes.GRID_ROWS);
        return choice;
    }

    /**
     * Méthode pour demander et lire le numéro de colonne d'une coordonnée
     * @param s : le message à afficher
     * @return le numéro de ligne, ou -1 si le joueur tape 'q' ou 'Q'
     */
    public int getCol(String s) {
        String strChoice; int choice = 0;
        do {
            askShipCoor("Type the "+s+" column (%d to %d) : ", 1, Constantes.GRID_COLS);
            strChoice = sc.nextLine();
            if (strChoice.equalsIgnoreCase("q")) {
                choice = -1;
                break;
            }
            else {
                try {
                    choice = Integer.valueOf(strChoice);
                } catch (Exception e) {
                    showMessage("invalid choice! try again");
                }
            }
        } while (choice < 1 || choice > Constantes.GRID_COLS);
        return choice;
    }

    /**
     * Méthode pour afficher les deux grilles du joueur human
     * @param game : Objet Game
     * @param playerIndex : Joueur 0 si c'est human sinon c'est computer
     * @param isFlare : determine si la grille Ennemi doit etre dévoilé (4 cases de la cible)
     * @param CoorFlare : (la cible si c'est une fusée éclairante)
     */
    public void showGrids(Game game, int playerIndex, boolean isFlare, Coordinates CoorFlare) {
        String horSepOwnGrid = (new String("----")).repeat(game.human.getOwnGrid().nbrCols+1);
        String horSepEnnGrid = (new String("----")).repeat(game.human.getEnemyGrid().nbrCols+1);
        String separator = "\n"+horSepOwnGrid+"      "+horSepEnnGrid+"\n";
        System.out.printf("%30s  %60s\n\n", "My grid", "Ennemy grid");
        System.out.print("  |");
        for (int i=0; i<game.human.getOwnGrid().nbrCols; i++) {
            System.out.printf(" %-2d|", (i+1));
        }
        System.out.print("         |");
        for (int i=0; i<game.human.getEnemyGrid().nbrCols; i++) {
            System.out.printf(" %-2d|", (i+1));
        }
        System.out.print(separator);
        for (int i=0; i<game.human.getOwnGrid().nbrRows; i++) {
            System.out.printf("%-2d|", i+1);
            for (int j=0; j<game.human.getOwnGrid().nbrCols; j++) {
                System.out.printf(" %-2c|", game.human.getOwnGrid().grid[i][j].getCh());
            }
            System.out.print("       ");
            System.out.printf("%-2d|", i+1);
            for (int j=0; j<game.human.getEnemyGrid().nbrCols; j++) {
                if (playerIndex==0 && isFlare && (i==CoorFlare.getRow() || i==CoorFlare.getRow()-1)
                        && (j==CoorFlare.getCol() || j==CoorFlare.getCol()-1))
                    System.out.printf(" %-2c|", game.computer.getOwnGrid().grid[i][j].getCh());
                else
                    System.out.printf(" %-2c|", game.human.getEnemyGrid().grid[i][j].getCh());
            }
            System.out.print(separator);
        }
    }

    /**
     * Méthode qui affiche les règles du jeu
     */
    public void showHelp() {
        System.out.println("*** Game rules **");
        System.out.println("Ce jeu représente un jeu de bataille navale où le but est de couler entièrement la flotte annemie" +
                "Vous avez exactement la même flotte que l'adversaire, mais chose qui diffère du jeu original est que vous pouvez déplacer vos navires dans le sens sur lequel il est" +
                "si ils n'ont pas été touché. Chaque navire à une puissance de tir respectif. Ironclad : 9 cases, Cruiser : 4 cases" +
                "Destroyeur et Submarine on tout les deux une puissance de 1 case. Vous pouvez sauvegardé votre partie en appuyant sur q ou Q à tout moment");

    }
}
