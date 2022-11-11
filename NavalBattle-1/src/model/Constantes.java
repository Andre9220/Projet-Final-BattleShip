package model;

/**
 * Classe Constantes qui définit toutes les constantes du programme
 * @author
 *
 */
public class Constantes {
    public static final int IRONCLAD_LENGTH = 7;
    public static final int CRUISER_LENGTH = 5;
    public static final int DESTROYER_LENGTH = 3;
    public static final int SUBMARINE_LENGTH = 1;

    public static final int IRONCLAD_FIREPOWER = 9;
    public static final int CRUISER_FIREPOWER = 4;
    public static final int DESTROYER_FIREPOWER = 1;
    public static final int SUBMARINE_FIREPOWER = 1;

    public static final int IRONCLAD_NBR = 1;
    public static final int CRUISER_NBR = 2;
    public static final int DESTROYER_NBR = 3;
    public static final int SUBMARINE_NBR = 4;

    public static final int FLEET_SIZE = IRONCLAD_NBR+CRUISER_NBR+DESTROYER_NBR+SUBMARINE_NBR;

    public static final int GRID_ROWS = 15;
    public static final int GRID_COLS = 15;

    public static final char GRID_CHARS = ' ';
    public static final char IRONCLAD_CHARS = 'B';
    public static final char CRUISER_CHARS = 'C';
    public static final char DESTROYER_CHARS = 'D';
    public static final char SUBMARINE_CHARS = 'S';

    public static final char FIRE_CHAR = '#';

    public static final String GAME_FILE = "game.txt";

    /*DONE*/


}
