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
    
}
