package maingame;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerClass  implements Serializable {
    private int height;
    private  int width;
    private  float startLocH;
    private  float startLocW;
    private  short[][] maze ;
    private  int money;
    private  Dimension  startLoc;

    public Dimension getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(Dimension startLoc) {
        this.startLoc = startLoc;
    }

    public float getStartLocH() {
        return startLocH;
    }
    public void afficherConsole(){
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[0].length; j++) {
                System.out.print((this.maze[j][i]==0||this.maze[j][i]==5?" " + this.maze[j][i]: this.maze[j][i]) + " ");
            }
            System.out.println();
        }
    }

    public void setStartLocH(float startLocH) {
        this.startLocH = startLocH;
    }

    public float getStartLocW() {
        return startLocW;
    }

    public void setStartLocW(float startLocW) {
        this.startLocW = startLocW;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public short[][] getMaze() {
        return maze;
    }



    public void setMaze(short[][] maze) {
        this.maze = maze;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;

    }

    public void saveAstart(){

        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("./test.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            System.out.println("------------------------");
            // Method for serialization of object
            out.writeObject(this);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }catch(IOException ex)
        {
            System.out.println("IOException is caught" +ex);
        }

    }
}

