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

public class Helicopter extends SuperDefence {
    private JLabel soldierCountLabel;
    private JLabel ammoAmountLabel;
    private JCheckBox positionTick;
    private JSlider positionSlider;
    private JButton shootButton;
    private JButton MissileOperationButton;
    private JButton LaserOperationButton;
    private JTextField privateMessageField;
    private JButton sendPrivateMessageButton;
    private JTextArea messageBox;

    public Helicopter(MainController mainController) {
        super("Helicopter ");
        this.soldierCount = 100;
        this.ammoAmount = 50;
        this.positionLevel = 50;

    }

    protected void setupGUI() {
        setSize(570, 700);
        setTitle("Helicopter");
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(12, 1, 5, 5)); // Adjusted layout to accommodate additional buttons
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        shootButton = new JButton("Shoot");
        shootButton.setFont(new Font("Serif", Font.BOLD, 14));
        shootButton.setEnabled(false);
        controlPanel.add(shootButton);

        MissileOperationButton = new JButton("Missile Operation");
        MissileOperationButton.setFont(new Font("Serif", Font.BOLD, 14));
        MissileOperationButton.setEnabled(false);
        controlPanel.add(MissileOperationButton);

        LaserOperationButton = new JButton("Laser Operation");
        LaserOperationButton.setFont(new Font("Serif", Font.BOLD, 14));
        LaserOperationButton.setEnabled(false);
        controlPanel.add(LaserOperationButton);

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
                    sendMessage("Helicopter shooting! Ammo left: " + ammoAmount);
                    updateGUI();
                } else {
                    sendMessage("No ammo left!");
                }
            }
        });

        MissileOperationButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sendMessage("Helicopter launching missile !");
                updateGUI();
            }
        });

        LaserOperationButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sendMessage("Helicopter launching Laser !");
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
        MissileOperationButton.setEnabled(positionEnabled && position >= 40);
        LaserOperationButton.setEnabled(positionEnabled && position >= 60);
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

    public int getPositionLevel() {
        return positionLevel;
    }
}
