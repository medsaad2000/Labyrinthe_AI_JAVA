package maingame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author AMMOR & ELABDI & MESKINI
 */

public class MainGame extends JFrame {


    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        MainGame frame = new MainGame();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public MainGame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(29, 11, 364, 214);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JButton btnNewButton = new JButton("Nouvelle Partie");
        btnNewButton.setBounds(124, 59, 118, 23);
        panel_1.add(btnNewButton);

        JButton btnOption = new JButton("Instructions");
        btnOption.setBounds(124, 97, 118, 23);
        panel_1.add(btnOption);
        JButton restBtn = new JButton("Restaurer");
        restBtn.setBounds(124, 135, 118, 23);
        panel_1.add(restBtn);
        
        

        

        JLabel lblNewLabel = new JLabel(" MazeGame");
        lblNewLabel.setFont(new Font("Andalus", Font.BOLD, 18));
        lblNewLabel.setBounds(119, 11, 123, 37);
        panel_1.add(lblNewLabel);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               dispose();
               new LevelFrame();
            }
        });
        restBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    FileInputStream fis = new FileInputStream("./test.ser");

                    ObjectInputStream ois = new ObjectInputStream(fis);



                    SerClass ser = (SerClass)ois.readObject();

                    System.out.println( "height: "+ser.getHeight());
                    System.out.println( "start H: "+ser.getStartLocH());
                    System.out.println( "money "+ser.getMoney());
                    System.out.println( "start H: "+ser.getWidth());
                    System.out.println( "start H: "+ser.getWidth());

                    System.out.println( "start H: "+ser.getStartLocH());
                    ser.afficherConsole();
                    MazeAstarSearch mazeSearch1 = new MazeAstarSearch(ser ,41);

                    //System.out.println(mazeSearch1.rootMaze.getHeight());

                    fis.close();
                    ois.close();
                    System.out.println("Deserialized data has been created in the memory");

                } catch (IOException | ClassNotFoundException ex) {

                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
