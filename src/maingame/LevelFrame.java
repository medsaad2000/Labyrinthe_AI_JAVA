package maingame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author AMMOR & ELABDI & MESKINI
 */

public class LevelFrame extends JFrame {

    private JPanel contentPane;


    public LevelFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 414, 239);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Niveau ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(12, 10, 394, 209);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 70, 374, 70);
        panel_1.add(panel_2);
        panel_2.setLayout(null);

        JButton btnNewButton = new JButton("NIVEAU 1");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //do level1
                dispose();
                new MazeAstarSearch(21,21, 60);
            }
        });
        btnNewButton.setBounds(0, 0, 90, 70);
        panel_2.add(btnNewButton);

        JButton btnNiveau = new JButton("NIVEAU 2");
        btnNiveau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //do level2
                dispose();
                new MazeAstarSearch(41,41, 90);
            }
        });
        btnNiveau.setBounds(94, 0, 90, 70);
        panel_2.add(btnNiveau);

        JButton btnNiveau_1 = new JButton("NIVEAU 3");
        btnNiveau_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //do level3
                dispose();
                new MazeAstarSearch(61,61, 120);

            }
        });
        btnNiveau_1.setBounds(188, 0, 90, 70);
        panel_2.add(btnNiveau_1);

        JButton btnNiveau_2 = new JButton("NIVEAU 4");
        btnNiveau_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //do level4
                dispose();
                MazeAstarSearch f = new MazeAstarSearch(81,81, 150);
            }
        });
        btnNiveau_2.setBounds(284, 0, 90, 70);
        panel_2.add(btnNiveau_2);

        JLabel lblNewLabel = new JLabel("Niveau ");
        lblNewLabel.setFont(new Font("Andalus", Font.BOLD, 16));
        lblNewLabel.setBounds(168, 23, 90, 25);
        panel_1.add(lblNewLabel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}

