package model;

/**
 * Classe Game qui représente le jeu
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Classe qui gère la partie
 */
public class Game {
    public Player human, computer, winner;
    public int currentPlayer;

    /**
     * Constructeur par défaut
     */
    public Game() {
        this.human = new Human();
        this.computer = new Computer();
    }

    /**
     * Méthode qui initialise
     */
    public void initialize() {
        // placer les navires dans les grilles de chaque joueur
        human.placeShips();
        computer.placeShips();
    }

    /**
     * détermine si l'une des flottes des deux joueurs est coulée
     * @return vrai si l'une des flottes des deux joueurs est coulée, faux sinon
     */
    public boolean isOver() {
        return (human.getFleetHealth()==0 || computer.getFleetHealth()==0);
    }

    /**
     * Méthode qui permet de sauvegarder la partie dans le fichier "filename"
     * @param filename : nom du fichier à ouvrir pour y écrire : game.txt
     * @throws IOException
     */
    public void saveToFile(String filename) throws IOException {

        File fout = new File(filename);
        FileOutputStream fos = new FileOutputStream(fout);
        OutputStreamWriter writer = new OutputStreamWriter(fos);
        // écrire la grille du joueur human
        for (int i=0; i<human.ownGrid.nbrRows; i++) {
            for (int j=0; j<human.ownGrid.nbrCols-1; j++) {
                writer.write(human.ownGrid.grid[i][j].getCh()+",");
            }
            writer.write(human.ownGrid.grid[i][human.ownGrid.nbrCols-1].getCh()+"\n");
        }
        // écrire la grille ennemi du joueur human
        writer.write(" Grille ennemi du joueur Human : \n");
        for (int i=0; i<human.enemyGrid.nbrRows; i++) {
            for (int j=0; j<human.enemyGrid.nbrCols-1; j++) {
                writer.write(human.enemyGrid.grid[i][j].getCh()+",");
            }
            writer.write(human.enemyGrid.grid[i][human.enemyGrid.nbrCols-1].getCh()+"\n");
        }
        writer.write(" Grille ennemi du joueur Computer : \n");
        // �crire la grille du joueur computer
        for (int i=0; i<computer.ownGrid.nbrRows; i++) {
            for (int j=0; j<computer.ownGrid.nbrCols-1; j++) {
                writer.write(computer.ownGrid.grid[i][j].getCh()+",");
            }
            writer.write(computer.ownGrid.grid[i][computer.ownGrid.nbrCols-1].getCh()+"\n");
        }
        writer.write(" Grille ennemi du joueur Computer : \n");
        // écrire la grille ennemi du joueur computer
        for (int i=0; i<computer.enemyGrid.nbrRows; i++) {
            for (int j=0; j<computer.enemyGrid.nbrCols-1; j++) {
                writer.write(computer.enemyGrid.grid[i][j].getCh()+",");
            }
            writer.write(computer.enemyGrid.grid[i][computer.enemyGrid.nbrCols-1].getCh()+"\n");
        }
        // écrire les données des navires du joueur human
        for (int i=0; i<human.shipCounter; i++) {
            Ship ship = human.fleet[i];
            writer.write(String.valueOf(ship.getHealth())+"\n");
            writer.write(ship.getPos().toString()+"\n");
            writer.write(String.valueOf(ship.isVertical())+"\n");
            for (int j=0; j<ship.length; j++) {
                Coordinates coor = ship.getArea().get(j);
                writer.write(coor+","+coor.getCh()+","+coor.isDestroyed()+"\n");
            }
            if (ship instanceof Destroyer)
                writer.write(String.valueOf(((Destroyer)ship).canFlare())+"\n");
        }
        // écrire les données des navires du joueur computer
        for (int i=0; i<computer.shipCounter; i++) {
            Ship ship = computer.fleet[i];
            writer.write(String.valueOf(ship.getHealth())+"\n");
            writer.write(ship.getPos().toString()+"\n");
            writer.write(String.valueOf(ship.isVertical())+"\n");
            for (int j=0; j<ship.length; j++) {
                Coordinates coor = ship.getArea().get(j);
                writer.write(coor+","+coor.getCh()+","+coor.isDestroyed()+"\n");
            }
            if (ship instanceof Destroyer)
                writer.write(String.valueOf(((Destroyer)ship).canFlare())+"\n");
        }
        writer.close();
    }

    /**
     * Méthode qui permet de charger la partie sauvegardée dans le fichier "filename"
     * @param filename : nom du fichier à ouvrir pour lire : game.txt
     * @throws IOException
     */
    public void loadFromFile(String filename) throws IOException {

        FileInputStream fis = new FileInputStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String line;	// pour lire une seule valeur par ligne
        String[] values;	// pour lire plusieurs valeurs par ligne

        // lire les données de la grille du joueur human
        for (int i=0; i<human.ownGrid.nbrRows; i++) {
            values = reader.readLine().split(",") ;
            for (int j=0; j<human.ownGrid.nbrCols-1; j++) {
                human.ownGrid.grid[i][j].setCh(values[j].charAt(0));
            }
        }
        // lire les données de la grille ennemi du joueur human
        reader.readLine();
        for (int i=0; i<human.enemyGrid.nbrRows; i++) {
            values = reader.readLine().split(",") ;
            for (int j=0; j<human.enemyGrid.nbrCols-1; j++) {
                human.enemyGrid.grid[i][j].setCh(values[j].charAt(0));
            }
        }
        // lire les données de la grille du joueur computer
        reader.readLine();
        for (int i=0; i<computer.ownGrid.nbrRows; i++) {
            values = reader.readLine().split(",") ;
            for (int j=0; j<computer.ownGrid.nbrCols-1; j++) {
                computer.ownGrid.grid[i][j].setCh(values[j].charAt(0));
            }
        }
        // lire les données de la grille ennemi du joueur computer
        reader.readLine();
        for (int i=0; i<computer.enemyGrid.nbrRows; i++) {
            values = reader.readLine().split(",") ;
            for (int j=0; j<computer.enemyGrid.nbrCols-1; j++) {
                computer.enemyGrid.grid[i][j].setCh(values[j].charAt(0));
            }
        }
        // lire les données de la flotte du joueur human
        for (int i=0; i<human.shipCounter; i++) {
            Ship ship = human.fleet[i];
            line = reader.readLine();
            ship.setHealth(Integer.valueOf(line));
            values = reader.readLine().split(",");
            ship.setPos(new Coordinates(Integer.valueOf(values[0]), Integer.valueOf(values[1])));
            line = reader.readLine();
            ship.setVertical(Boolean.valueOf(line));
            for (int j=0; j<ship.length; j++) {
                values = reader.readLine().split(",");
                Coordinates coor = new Coordinates(Integer.valueOf(values[0]), Integer.valueOf(values[1]), values[2].charAt(0));
                if (Boolean.valueOf(values[3]))
                    coor.destroy();
                ship.getArea().add(coor);
            }
            if (ship instanceof Destroyer) {
                line = reader.readLine();
                if (!Boolean.valueOf(line))
                    ((Destroyer) ship).cantFlare();
            }
        }
        // lire les données de la flotte du joueur computer
        for (int i=0; i<computer.shipCounter; i++) {
            Ship ship = computer.fleet[i];
            line = reader.readLine();
            ship.setHealth(Integer.valueOf(line));
            values = reader.readLine().split(",");
            ship.setPos(new Coordinates(Integer.valueOf(values[0]), Integer.valueOf(values[1])));
            line = reader.readLine();
            ship.setVertical(Boolean.valueOf(line));
            for (int j=0; j<ship.length; j++) {
                values = reader.readLine().split(",");
                Coordinates coor = new Coordinates(Integer.valueOf(values[0]), Integer.valueOf(values[1]), values[2].charAt(0));
                if (Boolean.valueOf(values[3]))
                    coor.destroy();
                ship.getArea().add(coor);
            }
            if (ship instanceof Destroyer) {
                line = reader.readLine();
                if (!Boolean.valueOf(line))
                    ((Destroyer) ship).cantFlare();
            }
        }
        reader.close();
    }

    /*done*/

}