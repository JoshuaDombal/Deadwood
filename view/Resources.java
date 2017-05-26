package view;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Resources{

    //array of images for all scene cards
    private ImageIcon[] sceneCards;

    //arrays for every different dice color
    private ImageIcon[] b;
    private ImageIcon[] c;
    private ImageIcon[] g;
    private ImageIcon[] o;
    private ImageIcon[] p;
    private ImageIcon[] r;
    private ImageIcon[] v;
    private ImageIcon[] y;

    private ImageIcon[][] color;
    private char[] colors;

    //image for the shot token
    private ImageIcon shot;

    //image for the face down scene card
    private ImageIcon faceDown;

    static Resources instance;

    private Resources(){

        b = new ImageIcon[6];
        c = new ImageIcon[6];
        g = new ImageIcon[6];
        o = new ImageIcon[6];
        p = new ImageIcon[6];
        r = new ImageIcon[6];
        v = new ImageIcon[6];
        y = new ImageIcon[6];

        color = new ImageIcon[][] {b, c, g, o, p, r, v, y};
        colors = new char[] {'b', 'c', 'g', 'o', 'p', 'r', 'v', 'y'};

        //shot = new ImageIcon();
        //faceDown = new ImageIcon();

        sceneCards = new ImageIcon[40];

        try{
            for(int j = 0; j < 8; j++){
                for(int i = 0; i < 6; i++){
                    color[j][i] = new ImageIcon(
                        ImageIO.read(
                            new File(String.format("../resources/%s%d.png", colors[j], (i+1))
                        ))
                    );
                }
            }

        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }

    }

    public ImageIcon getPlayerDie(char playerColor, int i){

        ImageIcon playerDie = new ImageIcon();

        for(int k = 0; k < 8; k++){
            if(colors[k] == playerColor){
                playerDie = color[k][i];
            }
        }

        return playerDie;
    }

    public static Resources getInstance(){
        if(instance == null){
            instance = new Resources();
        }

        return instance;
    }
}
