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


}
