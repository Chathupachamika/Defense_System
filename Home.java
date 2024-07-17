import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JFrame {
    private JLabel imageLabel;

    public Home() {
        setTitle("Welcome");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(1, 1, 1));

        imageLabel = new JLabel(new ImageIcon("images/images.jpg"));
        imageLabel.setBounds(175, 50, 350, 250);
        panel1.add(imageLabel);

        JButton welcomeButton = new JButton("Welcome To The SL Dispose System");
        welcomeButton.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeButton.setBackground(new Color(71, 39, 71));
        welcomeButton.setForeground(Color.WHITE);
        welcomeButton.setBounds(150, 320, 400, 50);
        welcomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openMainController();
            }
        });
        panel1.add(welcomeButton);

        add(panel1, BorderLayout.CENTER);

        setVisible(true);
    }

    private void openMainController() {

        dispose();

        MainController mainController = new MainController();
        Helicopter helicopter = new Helicopter(mainController);
        Tank tank = new Tank(mainController);
        Submarine submarine = new Submarine(mainController);

        mainController.addUnit(helicopter);
        mainController.addUnit(tank);
        mainController.addUnit(submarine);
    }

    public static void main(String[] args) {
        new Home();
    }
}
