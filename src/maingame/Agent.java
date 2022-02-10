package maingame;

import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * A class that creates all the elements necessary for a player
 * to traverse the maze.
 */
public class Agent implements Runnable {

	private LocalDateTime startTime;
	private int timeReserved;
	private static int money = 0;
	public static Dimension startLoc = new Dimension();
	private Maze maze;
        /////////

        //////////

	public Agent(Maze maze , Dimension startLoc ,int timeReserved){
		this.timeReserved = timeReserved;
		this.startTime = LocalDateTime.now();
		Agent.startLoc=startLoc;
		this.maze=maze;
	}

	public static void setMoney(int money) {
		Agent.money = money;
	}

	public static Dimension getStartLoc() {
		return startLoc;
	}

	public static void setStartLoc(Dimension startLoc) {
		Agent.startLoc = startLoc;
	}

	public static void moveLeft(Maze maze){
		if((maze.getValue(startLoc.width-1, startLoc.height) != -1)){
			maze.setValue(startLoc.width, startLoc.height, (short) 0);
			if(maze.getValue(startLoc.width-=1, startLoc.height) == (short)-3 ) endGame(1);
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 5){
                            
                            money+=5;
                        }
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 6)
                            if(money>0)
                            money--;
                            else money=0;
			else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		}
		else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		System.out.println("You got " + money);
	}

	public static void moveRight(Maze maze){
		if((maze.getValue(startLoc.width+1, startLoc.height) != -1) ){
			maze.setValue(startLoc.width, startLoc.height, (short) 0);
			if(maze.getValue(startLoc.width+=1, startLoc.height) == (short)-3 ) endGame(1);
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 5){
                            
                            money+=5;
                        }
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 6)
                            if(money>0)
                            money--;
                            else money=0;
			else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		}
		else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		System.out.println("You got " + money);
	}

	public static void moveUp(Maze maze){
		if((maze.getValue(startLoc.width, startLoc.height+1) != -1) ){
			maze.setValue(startLoc.width, startLoc.height, (short) 0);
			if(maze.getValue(startLoc.width, startLoc.height+=1) == (short)-3 ) endGame(1);
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 5){
                            
                            money+=5;
                        }
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 6)
                            if(money>0)
                            money--;
                            else money=0;
			else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		}
		else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		System.out.println("You got " + money);
	}

	public static void moveDown(Maze maze){
		if((maze.getValue(startLoc.width, startLoc.height-1) != -1) ){
			maze.setValue(startLoc.width, startLoc.height, (short) 0);
			if(maze.getValue(startLoc.width, startLoc.height-=1) == (short)-3 ) endGame(1);
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 5){
                            
                            money+=5;
                        }
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 6)
                            if(money>0)
                            money--;
                            else money=0;
			else maze.setValue(startLoc.width, startLoc.height, (short)-2);
			maze.setValue(1,1,(short)0);
		}
		else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		maze.setValue(1,1,(short)0);
		System.out.println("You got " + money);
	}

	public static void endGame( int win){
            if(win==1)
		 MazeFrame(20, money , 1 );
            else
                 MazeFrame(20, money , 0 );
                
	}

	public static void MazeFrame(int level, int money ,  int win ){
		JPanel p3 = new JPanel(new BorderLayout());
		JFrame frame2 = new JFrame();
                JLabel textLabel;
                   
                if( win==1)
		 textLabel = new JLabel("<html>Félicitations !<br>Vous avez obtenue " + money + " pts!</html>", JLabel.CENTER);
                else
                 textLabel = new JLabel("<html>Time out)!</html>", JLabel.CENTER);
		textLabel.setFont(new Font("Verdana", Font.BOLD, 32));

		frame2.add(p3, BorderLayout.SOUTH);
		frame2.setBackground(Color.green);
		frame2.add(textLabel, BorderLayout.CENTER);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(900, 850);
		frame2.setLocationRelativeTo(null);
		frame2.setVisible(true);
	}

	@Override
	public void run() {
		while (true){

			this.getCountDown();
			if (LocalDateTime.now().isAfter(this.startTime.plusSeconds(this.timeReserved))){
				endGame(0);
				break;
			}
		}
	}

	public String getCountDown(){
		Long uptime  = SECONDS.between(LocalDateTime.now(), this.startTime.plusSeconds(this.timeReserved));

		Long hours = TimeUnit.MILLISECONDS.toHours(uptime);
		uptime -= TimeUnit.HOURS.toMillis(hours);

		Long minutes = TimeUnit.SECONDS.toMinutes(uptime);
		uptime -= TimeUnit.MINUTES.toMillis(minutes);

		Long seconds = TimeUnit.SECONDS.toSeconds(uptime);
		return (hours < 9 ? "0" : "") + hours + ":" + (minutes < 9 ? "0" : "") + minutes + ":" + (seconds < 9 ? "0" : "") + seconds;
	}
        
       public static int getMoney() {
		 return money ;
	}
       public static void moneyDown( int value) {
		  money -=value  ;
	}
       public static void moneyUp( int value ) {
		  money += value ;
	}
}
