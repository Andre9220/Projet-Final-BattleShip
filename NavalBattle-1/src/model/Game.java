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

public class Game {
    public Player human, computer, winner;

    /**
     * Constructeur par défaut
     */
    public Game() {
        this.human = new Human();
        this.computer = new Computer();
    }

    /**
     * détermine si l'une des flottes des deux joueurs est coulée
     * @return vrai si l'une des flottes des deux joueurs est coulée, faux sinon
     */
    public boolean isOver() {
        return (human.getFleetHealth()==0 || computer.getFleetHealth()==0);
    }

    //à faire le plus rapidement possible !!!
    public void saveToFile(String filename) throws IOException {

    }

    public void loadFromFile(String filename) throws IOException {

    }

}