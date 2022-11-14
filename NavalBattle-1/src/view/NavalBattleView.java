package view;


import java.util.Scanner;

import model.Constantes;
import model.Coordinates;
import model.Game;

/**
 * Classe qui représente l'interface entre l'utilisateur et le Controleur du jeu
 * elle permet d'afficher des messages et des menus vers le console
 * et de lire les entrées du clavier de l'utilisateur
 * @author
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

        String strChoice;
        int choice = 0;

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
     * Méthode qui affiche les règles du jeu
     */

    //à completer

    public void showHelp() {
        System.out.println("*********************** Game rules ************************");

    }


    /**
     * Méthode pour afficher les deux grilles du joueur human
     * @param game
     * @param playerIndex
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


}
