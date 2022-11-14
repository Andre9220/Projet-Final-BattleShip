package main;

import controller.NavalBattleController;

public class Driver {

    public static void main(String[] args) {

        // créer un objet NavalBattleController
        NavalBattleController battle = new NavalBattleController();
        // lancer le jeu
        battle.start();

    }

}
