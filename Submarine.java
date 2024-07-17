import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Submarine extends SuperDefence {
    private JLabel soldierCountLabel;
    private JLabel ammoAmountLabel;
    private JCheckBox positionTick;
    private JSlider positionSlider;
    private JButton shootButton;
    private SuperDefence selectedUnit;
    private JButton SonarOperationButton;
    private JButton TomohawkMissileButton;
    private JButton Trident2MissileButton;
    private JTextField privateMessageField;
    private JButton sendPrivateMessageButton;
    private JTextArea messageBox;

    public Submarine(MainController mainController) {
        super("Submarine ");
        this.soldierCount = 100;
        this.ammoAmount = 50;
        this.positionLevel = 50;

    }

    protected void setupGUI() {
        setSize(570, 700);
        setTitle("Submarine");
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(12, 1, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        shootButton = new JButton("Shoot");
        shootButton.setFont(new Font("Serif", Font.BOLD, 14));
        shootButton.setEnabled(false);
        controlPanel.add(shootButton);

        SonarOperationButton = new JButton("Sonar Operation");
        SonarOperationButton.setFont(new Font("Serif", Font.BOLD, 14));
        SonarOperationButton.setEnabled(false);
        controlPanel.add(SonarOperationButton);

        TomohawkMissileButton = new JButton("Tomahawk Missile");
        TomohawkMissileButton.setFont(new Font("Serif", Font.BOLD, 14));
        TomohawkMissileButton.setEnabled(false);
        controlPanel.add(TomohawkMissileButton);

        Trident2MissileButton = new JButton("Trident-2 Missile");
        Trident2MissileButton.setFont(new Font("Serif", Font.BOLD, 14));
        Trident2MissileButton.setEnabled(false);
        controlPanel.add(Trident2MissileButton);

        JButton areaClearButton = new JButton("Area Clear");
        areaClearButton.setFont(new Font("Serif", Font.BOLD, 14));
        controlPanel.add(areaClearButton);

        JButton areaNotClearButton = new JButton("Are Not Clear");
        areaNotClearButton.setFont(new Font("Serif", Font.BOLD, 14));
        controlPanel.add(areaNotClearButton);

        JPanel messagePanel = new JPanel(new BorderLayout());

        privateMessageField = new JTextField(20);
        messagePanel.add(privateMessageField, BorderLayout.CENTER);

        sendPrivateMessageButton = new JButton("Send Private Message");
        sendPrivateMessageButton.setFont(new Font("Serif", Font.BOLD, 14));
        messagePanel.add(sendPrivateMessageButton, BorderLayout.EAST);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(controlPanel, BorderLayout.NORTH);
        leftPanel.add(messagePanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        soldierCountLabel = new JLabel("Soldier Count: " + soldierCount);
        soldierCountLabel.setFont(new Font("Serif", Font.BOLD, 14));
        infoPanel.add(soldierCountLabel, gbc);
        gbc.gridy++;

        ammoAmountLabel = new JLabel("Ammo Amount: " + ammoAmount);
        ammoAmountLabel.setFont(new Font("Serif", Font.BOLD, 14));
        infoPanel.add(ammoAmountLabel, gbc);
        gbc.gridy++;

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setFont(new Font("Serif", Font.BOLD, 14));
        infoPanel.add(positionLabel, gbc);
        gbc.gridy++;

        positionTick = new JCheckBox("Enable Position:");
        positionTick.setFont(new Font("Serif", Font.BOLD, 14));
        infoPanel.add(positionTick, gbc);
        gbc.gridy++;

        positionSlider = new JSlider(JSlider.VERTICAL, 0, 100, positionLevel);
        positionSlider.setMajorTickSpacing(10);
        positionSlider.setPaintTicks(true);
        positionSlider.setPaintLabels(true);
        infoPanel.add(positionSlider, gbc);
        gbc.gridy++;

        add(infoPanel, BorderLayout.CENTER);

        messageBox = new JTextArea();
        messageBox.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageBox);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane, BorderLayout.SOUTH);

        shootButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (ammoAmount > 0) {
                    ammoAmount--;
                    sendMessage("Submarine shooting! Ammo left: " + ammoAmount);
                    updateGUI();
                } else {
                    sendMessage("No ammo left!");
                }
            }
        });

        SonarOperationButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sendMessage("Submarine launching missile !");
                updateGUI();
            }
        });

        TomohawkMissileButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sendMessage("Submarine launching Tomohawk missile !");
                updateGUI();
            }
        });

        Trident2MissileButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sendMessage("Submarine launching Trident-2-Missile missile !");
                updateGUI();
            }
        });

        sendPrivateMessageButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String message = privateMessageField.getText();
                if (!message.isEmpty()) {
                    sendMessage("Private Message: " + message);
                    privateMessageField.setText("");
                }
            }
        });

        areaClearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sendMessage("Area cleared!");
                updateGUI();
            }
        });

        areaNotClearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sendMessage("Area not cleared!");
                updateGUI();
            }
        });

        positionTick.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateButtonsEnabled();
            }
        });

        positionSlider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                positionLevel = positionSlider.getValue();
                updateButtonsEnabled();
            }
        });

        updateButtonsEnabled();
        setVisible(true);
    }

    private void updateButtonsEnabled() {
        boolean positionEnabled = positionTick.isSelected();
        int position = positionSlider.getValue();

        shootButton.setEnabled(positionEnabled && position >= 20 && ammoAmount > 0);
        SonarOperationButton.setEnabled(positionEnabled && position >= 40);
        TomohawkMissileButton.setEnabled(positionEnabled && position >= 60);
        Trident2MissileButton.setEnabled(positionEnabled && position >= 86);
    }

    protected void sendMessage(String message) {
        super.sendMessage(message);
        messageBox.append(message + "\n");
        updateGUI();
    }

    private void updateGUI() {
        soldierCountLabel.setText("Soldier Count: " + soldierCount);
        ammoAmountLabel.setText("Ammo Amount: " + ammoAmount);
    }

    public int getSoldierCount() {
        return soldierCount;
    }

    public int getAmmoAmount() {
        return ammoAmount;
    }

    // Method to return current position level

    public int getPositionLevel() {
        return positionLevel;
    }
}