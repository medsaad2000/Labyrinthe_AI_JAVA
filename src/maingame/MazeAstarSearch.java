package maingame;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;


public class MazeAstarSearch extends javax.swing.JFrame {

    private  SerClass serClass =new SerClass() ;;
    private JMenuBar menuBar;
    private Label lblscore = new Label();
	private JMenu game;
	private JMenu level;
	private JMenuItem[] levels;
	private JMenu help;
	private JMenu computer;
    private JMenu save;
    private Maze maze;


    private boolean showPath  = false;
    
        
        
    JPanel jPanel1 = new JPanel();
    Agent player;
    public Maze rootMaze;
    AstarSearchEngin currentSearchEngine = null;

     public MazeAstarSearch(int height, int width, int timeInSeconds) {


        try {
            jbInit();
            rootMaze= new  Maze(height,width);
        } catch (Exception e) {
            System.out.println("GUI initilization error: " + e);
        }
         Dimension startLoc = new Dimension();
        currentSearchEngine = new AstarSearchEngin(height, width ,rootMaze);
        this.player= new Agent(currentSearchEngine.getMaze() ,startLoc ,timeInSeconds);
         // this.player = new Agent(timeInSeconds);

         Thread thread = new Thread(this.player);
         thread.start();
        repaint();
       menu();
    }
    public MazeAstarSearch(SerClass serClass, int timeInSeconds) {



        try {
            jbInit();
            rootMaze= new  Maze(serClass.getHeight(),serClass.getWidth() ,serClass.getMaze() );
            System.out.println("-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-----//");
            rootMaze.afficherConsole();
            Agent.startLoc=serClass.getStartLoc();
            Agent.setMoney(serClass.getMoney());
        } catch (Exception e) {
            System.out.println("GUI initilization error: " + e);
        }
        System.out.println("-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
      currentSearchEngine = new AstarSearchEngin(serClass.getHeight(),serClass.getWidth() ,rootMaze);
//        System.out.println("-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
//        Dimension startLoc = new Dimension();
////        startLoc.height=serClass.getHeight();
////        startLoc.width=serClass.getWidth();
//        this.player= new Agent(currentSearchEngine.getMaze() ,startLoc,timeInSeconds);
//        Thread thread = new Thread(this.player);
//        thread.start();
        repaint();
        menu();
    }
    public void afficherConsole(short[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
            
            
           
        }}
    public void paint(Graphics g_unused) {

       lblscore.setText("Score : "+Agent.getMoney());
        if (currentSearchEngine == null) return ;
        Maze maze = currentSearchEngine.getMaze();
        int width = maze.getWidth();
        int height = maze.getHeight();
        //pour voire le chement le plus rapid
        currentSearchEngine = new AstarSearchEngin(height,width, rootMaze);


        System.out.println("Size of current maze: " + width + " by " + height);
//        maze.afficherConsole();
        Graphics g = jPanel1.getGraphics();
        BufferedImage image = new BufferedImage(1020, 1020, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = image.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, 1020, 1020);
        g2.setColor(Color.black);
        maze.setValue(height,width , Maze.START_LOC_VALUE);
        int n=width+10;
        switch (width){
            case 21:
                n=width+10/1;
                break;
            case 41:
                n=width/2;
                break;
            case 61:
                n=width/5;
                break;
            case 81:
                n=width*10/72;
                break;
        }

        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                short val = maze.getValue(x,y);

                if ( val == Maze.OBSTICLE) {
                    g2.setColor(Color.black);
                    g2.fillRect(6 + x * n, 3 + y * n, n, n);
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * n, 3 + y * n, n, n);
                } else if (val == Maze.START_LOC_VALUE) {
                    g2.setColor(Color.blue);
                    g2.fillRect(6 + x * n, 3 + y * n, n, n);
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * n, 3 + y * n, n, n);
                } else if (val == Maze.GOAL_LOC_VALUE) {
                    g2.setColor(Color.red);
                    g2.fillRect(6 + x * n, 3 + y * n, n, n);
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * n, 3 + y * n, n, n);
                } else if(val == 0) {
                	g2.setColor(Color.black);
                	g2.drawRect(6 + x * n, 3 + y * n, n, n);
                }else if(val == 5) {
                    g2.setColor(Color.yellow);
                    g2.fillRect(6 + x * n, 3 + y * n, n, n);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * n, 3 + y * n, n, n);
                }
                else if(val == 6) {
                    g2.setColor(Color.green);
                    g2.fillRect(6 + x * n, 3 + y * n, n, n);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * n, 3 + y * n, n, n);
                }
            }
        }


        // redraw the path in black:

        g2.setColor(Color.black);
        Dimension [] path = currentSearchEngine.getPath();
        if(showPath)
        for (int i=1; i< (path.length-1); i++) {
          int x = path[i].width;
          int y = path[i].height;
          short val = maze.getValue(x,y);
//          g2.drawString("#" + (path.length - i), 16 + x * 29, 19 + y * 29);
            g2.setColor(Color.GRAY);
            g2.fillRect( 6 + x * n, 3 + y * n, n, n);
            g2.setColor(Color.black);
            g2.drawRect(6 + x * n, 3 + y * n, n, n);

        }
        g.drawImage(image, 30, 5, 920, 920, null);


    }
    protected void processKeyEvent(KeyEvent e){
        if( e.getID() % 2 == 1){
            int keyCode = e.getKeyCode();
            System.out.println(keyCode);
            if(e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT) player.moveLeft(this.currentSearchEngine.getMaze());
            if(e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT) player.moveRight(this.currentSearchEngine.getMaze());
            if(e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP) player.moveDown(this.currentSearchEngine.getMaze());
            if(e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN) player.moveUp(this.currentSearchEngine.getMaze());
            repaint();
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//
//        MazeAstarSearch mazeSearch1 = new MazeAstarSearch(21,21);
//
//    }

    private void jbInit() throws Exception {
        /*
        this.setContentPane(jPanel1);
        this.setCursor(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("MazeAStarSearch");
        this.getContentPane().setLayout(null);
        jPanel1.setBackground(Color.white);
        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        jPanel1.setDoubleBuffered(false);
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);
        this.setSize(1020, 970);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
         setResizable(false);
*/
        JPanel p = new JPanel(new BorderLayout());
            Label label =new Label();
            //label.setText(player.getCountDown());
            
            Timer t = new Timer(1000, new ActionListener() {
              int delais = 60 ;   
            @Override
            public void actionPerformed(ActionEvent e) {
                delais-- ;
                if(delais>=0)
              label.setText("Time : 00:"+delais);
            }
         });
            t.setRepeats(true);
         t.setCoalesce(true);
         t.setInitialDelay(0);
         t.start();
      //  BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
      p.add(jPanel1,BorderLayout.CENTER);
      p.add(label,BorderLayout.NORTH);
      
      
      //Label lblscore = new Label();
     
      
      p.add(lblscore,BorderLayout.SOUTH);
      this.add(p,BorderLayout.CENTER);
      
      
                jPanel1.setBackground(Color.white);
        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        jPanel1.setDoubleBuffered(false);
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);
        
       // this.add(jPanel1);
       // this.add(label);
        //this.add(hard);

       // this.setLayout(boxLayout);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }
    public  void menu (){
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menu
        game = new JMenu("Game");
        level = new JMenu("Level");
        computer = new JMenu("Algorithm");
        help = new JMenu("Help");
        save = new JMenu("save");
        //currentTime= new JMenu("Time");

        menuBar.add(game);
        menuBar.add(level);
        menuBar.add(computer);
        menuBar.add(help);
        menuBar.add(save);

        JMenuItem newGame = new JMenuItem(new AbstractAction("New Game") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                Agent.startLoc.width=0;
                Agent.startLoc.height=0;
                repaint();

            }
        });

        JMenuItem quitter = new JMenuItem(new AbstractAction("quitter") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }

        });
        
        JMenuItem algorithm = new JMenuItem(new AbstractAction("A Star") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                showPath=true;
                //Thread.sleep();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                showPath=false;
                            }
                        },
                        5000
                );
                Agent.moneyDown(2);

            }
        });
        JMenuItem algorithm2 = new JMenuItem(new AbstractAction("A Star") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                showPath=true;
                //Thread.sleep();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                showPath=false;
                            }
                        },
                        5000
                );
                Agent.moneyDown(2);

            }
        });
        JMenuItem saveJeux = new JMenuItem(new AbstractAction("save") {
           // private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                serClass.setHeight(rootMaze.getHeight());
                serClass.setWidth(rootMaze.getWidth());
                serClass.setStartLocH(Agent.getStartLoc().height);
                serClass.setStartLocH(Agent.getStartLoc().width);
                rootMaze.afficherConsole();
                serClass.setMaze(rootMaze.getMaze());
                serClass.setMoney(Agent.getMoney());
                serClass.setStartLoc(Agent.startLoc);
                serClass.saveAstart();
            }
        });



        levels = new JMenuItem[4];
        for(int i = 0; i < levels.length; i++) {
            levels[i] = new JMenuItem("Level " + (i + 1));
            levels[i].setActionCommand((i + 1) + "");

            level.add(levels[i]);
        }
        JMenuItem about = new JMenuItem(new AbstractAction("Instruction") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Instructions");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                JLabel textLabel = new JLabel("" +"<html>" +
                        "1. Le Joueur commence avec le charactère bleu.<br> " +
                        "2. Pour gagner le joueur doit arriver à la sortie manifestée en bleue.<br> " +
                        "3. Obtenez des pièces en vous déplaçant dans le labyrinthe.. <br>" +
                        "4. Essayer d'arriver au score maximal pour beneficier des avantages<br>" +
                        "de nos algorithme A*(les bonus sont en jaunes et vous donne 5 pts) .<br>" +
                        "5. deplacer l'agent en utilisant le curseur du clavier <br>" +
                        "6. Si vous touchez les obstacles(manifesté en vert) vous perdez 2 pts <br>" +
                        "7. Faites attention au temps car si vous n'arrivez pas a la sortie <br>" +
                        "avant 1 min vous perdez.</html>");
                frame.getContentPane().add(textLabel, BorderLayout.CENTER);

                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

            }
        });



        game.add(newGame);
        game.add(quitter);
        computer.add(algorithm);
        
        
        help.add(about);
        save.add(saveJeux);
//        save.add(save);


    }
}
