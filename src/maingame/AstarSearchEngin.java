package maingame;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author AMMOR & ELABDI & MESKINI
 */
public class AstarSearchEngin extends AbstractSearchEngine {
    ArrayList<Dimension> L= new ArrayList<Dimension>(), T=new ArrayList<Dimension>();
    PriorityQueue<Dimension> file = new PriorityQueue<Dimension>();
    Dimension predecessor[][];  
        
    public AstarSearchEngin(int width, int height, Maze rootMaze) {
        super(rootMaze);

        predecessor = new Dimension[width][height];  
        for (int i=0; i<width; i++) 
            for (int j=0; j<height; j++) 
                predecessor[i][j] = null;
        
        file.add(new Case(startLoc,0,-1.0));
        // On suppose que T contient un seul But 
        T.add(goalLoc);
        
        doAStar();
        
        // now calculate the shortest path from the predecessor array:
        maxDepth = 0;
        if (!isSearching) {
            searchPath[maxDepth++] = goalLoc;
            for (int i=0; i<1000; i++) {
                searchPath[maxDepth] = predecessor[searchPath[maxDepth - 1].width][searchPath[maxDepth - 1].height];
                maxDepth++;
                if (equals(searchPath[maxDepth - 1], startLoc))  break;  // back to starting node
            }
        }
    }
    
    // On suppose que T contient un seul But (gloalLoc)
    public double h(Dimension d) {
          return Math.sqrt((goalLoc.height-d.height)*(goalLoc.height-d.height)+(goalLoc.width-d.width)*(goalLoc.width-d.width));
    }
   
    private void doAStar() {
              
        while (!file.isEmpty()) {
            
            currentLoc = file.remove(); // éléments triés dans L1 par ordre croissant de f
            
            L.add(currentLoc);           
            if(T.contains(currentLoc)){
                 System.out.println("But trouvé : (" + currentLoc.width +", " + currentLoc.height+")");
                 isSearching = false;
                 break;
             }
             else{
                
                Dimension [] connected = getPossibleMoves(currentLoc);
                for (int i=0; i<4; i++) {

                    if ( connected[i]!=null && !file.contains(connected[i]) && !L.contains(connected[i])) {
                        Case casei= new Case(connected[i],((Case)currentLoc).getG()+1,((Case)currentLoc).getG()+1+h(connected[i]));
                        predecessor[connected[i].width][connected[i].height] = currentLoc;
                        file.add(casei);
                    }
                }
            }
        }
        
    }
}



class Case extends Dimension implements Comparable{

    private double g;    // Estimation du coût du chemin parcouru
    private double f;    // Estimation du coût du chemin qui reste

    public Case(int width, int height) {
        super(width, height);
        f=0;
        g=0;
    }

    public Case(Dimension d, double g,double f) {
        super(d);
        this.g = g;
        this.f =f;
    }
    
    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    @Override
    public int compareTo(Object o) {
       if(f<((Case)o).f)
           return -1;
       else if(f>((Case)o).f)
           return 1;
       else 
           return 0;
    }
    
    
}