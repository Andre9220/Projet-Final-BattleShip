package main;

import controller.NavalBattleController;

/**
 * Classe main qui lance le jeu
 *
 */

public class Driver {

    public static void main(String[] args) {

        // créer un objet NavalBattleController
        NavalBattleController battle = new NavalBattleController();
        // lancer le jeu
        battle.start();

    }

}
