package de.laurens.tree;

import java.util.ArrayList;

public class DrawAssistant {

    private ArrayList<String> drawInstructions;

    private int level;
    private int depth;
    public double x;
    public double y;

    //level starts with 1
    // 2^-1 = 0.5


    public DrawAssistant(int level, int depth) {
        this.level = level;
        this.depth = depth;
        System.out.println(depth + "d");
        this.x = 0.5;
        this.y = 1.0 / depth * level;

        drawInstructions = new ArrayList<>();
    }

    public void moveDownLeft() {
        this.level ++;
        double fromX = x;
        double fromY = y;
        this.x = this.x - Math.pow(2, -level);
        this.y = (1.0 / depth) * level;
        addToInstructions(fromX, fromY, x, y);
        System.out.println("Down left");
    }

    public void moveDownRight() {
        this.level ++;
        double fromX = x;
        double fromY = y;
        this.x = this.x + Math.pow(2, -level);
        this.y = (1.0 / depth) * level;
        addToInstructions(fromX, fromY, x, y);
        System.out.println("Down right");
    }

    public void moveUpLeft() {
        double fromX = x;
        double fromY = y;
        this.x = this.x - Math.pow(2, -level);
        this.level --;

        this.y =( 1.0 / depth) * level;
        addToInstructions(fromX, fromY, x, y);


        System.out.println("Up left");
    }

    public void moveUpRight() {
        double fromX = x;
        double fromY = y;
        this.x = this.x + Math.pow(2, -level);
        this.level--;

        this.y = (1.0 / depth) * level;
        addToInstructions(fromX, fromY, x, y);


        System.out.println("Up right");

    }



    public void addToInstructions(String value) {
            //draw character
            drawInstructions.add(0 + ";" + value + ";" + x + ";" + y);
    }

    public void addToInstructions(double fromX, double fromY, double toX, double toY) {
        //draw line
        drawInstructions.add(1 + ";" + fromX + ";" + fromY + ";" +  toX + ";" + toY);
    }

    public ArrayList<String> getDrawInstructions() {
        return drawInstructions;
    }




}
