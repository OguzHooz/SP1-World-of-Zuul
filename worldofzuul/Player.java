package worldofzuul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;

public class Player {

    JFrame window;
    JPanel healthBarPanel;
    Container con;
    JProgressBar healthBar;
    int hp;


    //skal indsamles i starten på et tidspunkt
    String name;
    int Hunger;
    public static int xkoordinat;
    public static int ykoordinat;
    static int[] position = {xkoordinat, ykoordinat};

    public static void moveXakse(int x) {

        position[0] = position[0] + x;
        System.out.println("\nYou are now standing on: " + Arrays.toString(position));

    }

    public static void moveYakse(int y) {

        position[1] = position[1] + y;
        System.out.println("\nYou are now standing on: " + Arrays.toString(position));

    }


    public static boolean onBorder(){
        boolean canMove = false;
        if (position[1] == 0){
            canMove = true;
        } else if (position[1] == 5){
            canMove = true;
        } else if (position[0] == 0){
            canMove = true;
        } else if (position[0] == 5){
            canMove = true;
        }
        return canMove;
    }


    public static void setXkoordinat(int x) {
        xkoordinat = x;
    }
    public static void setYkoordinat(int y) {
        ykoordinat = y;
    }

    {
        healthBarPanel = new JPanel();
        healthBarPanel.setBounds(250, 250, 300, 30);
        healthBarPanel.setBackground(Color.orange);
        con.add(healthBarPanel);

        healthBar = new JProgressBar( 0,100);
        healthBar.setPreferredSize(new Dimension(300, 30));
        healthBar.setValue(100);
        healthBarPanel.add(healthBar);

        hp = 100;

        window.setVisible(true);
    }
    public void damageReceived(){
         hp = hp -5;
         healthBar.setValue(hp);
    }

    public class DamageHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            damageReceived();
        }
    }
    public void cureReceived(){
        boolean touchingfood = false; //Determines if we've
        hp = hp++;
        healthBar.setValue(hp);
    }
    //??????????

    public class CureHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            cureReceived();
        }
    }
}
