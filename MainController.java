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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainController extends JFrame implements Observer {
    private List<SuperDefence> units;
    private JComboBox<String> unitSelector;
    private JTextArea messageBox;
    private SuperDefence selectedUnit;
    private JSlider positionSlider;

    public MainController() {
        units = new ArrayList<>();
        setupGUI();
    }

    public void addUnit(SuperDefence unit) {
        units.add(unit);
        unit.addObserver(this);
        unitSelector.addItem(unit.getName());
    }

    public void update(String message) {
        messageBox.append(message + "\n");
    }

    private void setupGUI() {
        setTitle("Main Controller");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font customFont = new Font("Serif", Font.BOLD, 14);

        JPanel controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBackground(new Color(173, 216, 230)); // Light blue background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        unitSelector = new JComboBox<>();
        unitSelector.setPreferredSize(new Dimension(200, 30));
        unitSelector.setFont(customFont);
        unitSelector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = unitSelector.getSelectedIndex();
                if (selectedIndex >= 0) {
                    selectedUnit = units.get(selectedIndex);
                }
            }
        });

        JPanel actionButtonsPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Rows, Columns, Hgap, Vgap
        actionButtonsPanel.setBackground(new Color(173, 216, 230)); // Light blue background

        JButton collectInfoButton = new JButton("Collect Information");
        JButton areaClearButton = new JButton("Is the area Clear ?....");
        JButton areaNotClearButton = new JButton("Get position...");
        collectInfoButton.setFont(customFont);
        areaClearButton.setFont(customFont);
        areaNotClearButton.setFont(customFont);

        JPanel privateMessagePanel = new JPanel(new GridBagLayout());
        privateMessagePanel.setBackground(new Color(173, 216, 230)); // Light blue background

        JLabel privateMessageLabel = new JLabel("Private Message:");
        privateMessageLabel.setFont(customFont);
        JTextField privateMessageField = new JTextField(20);
        privateMessageField.setFont(customFont);
        JButton sendPrivateMessageButton = new JButton("Send");
        sendPrivateMessageButton.setFont(customFont);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(255, 240, 245)); // Light pink background
        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 0;
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.insets = new Insets(10, 10, 10, 10);

        JLabel soldierCountLabel = new JLabel("Soldier Count:");
        soldierCountLabel.setFont(customFont);
        JLabel fuelAmountLabel = new JLabel("Fuel Amount:");
        fuelAmountLabel.setFont(customFont);
        JLabel ammoAmountLabel = new JLabel("Ammo Amount:");
        ammoAmountLabel.setFont(customFont);
        JLabel positionLevelLabel = new JLabel("Position Level:");
        positionLevelLabel.setFont(customFont);
        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setFont(customFont);

        positionSlider = new JSlider(0, 100);
        positionSlider.setPaintTicks(true);
        positionSlider.setPaintLabels(true);
        positionSlider.setMajorTickSpacing(20);
        positionSlider.setMinorTickSpacing(5);
        positionSlider.setFont(customFont);

        infoPanel.add(soldierCountLabel, gbcInfo);
        gbcInfo.gridy++;
        infoPanel.add(fuelAmountLabel, gbcInfo);
        gbcInfo.gridy++;
        infoPanel.add(ammoAmountLabel, gbcInfo);
        gbcInfo.gridy++;
        infoPanel.add(positionLevelLabel, gbcInfo);
        gbcInfo.gridy++;

        messageBox = new JTextArea(30, 19);
        messageBox.setEditable(false);
        messageBox.setFont(customFont);
        JScrollPane messageScrollPane = new JScrollPane(messageBox);

        gbc.gridy++;
        controlPanel.add(unitSelector, gbc);
        gbc.gridy++;
        controlPanel.add(actionButtonsPanel, gbc);
        gbc.gridy++;
        controlPanel.add(privateMessagePanel, gbc);

        actionButtonsPanel.add(collectInfoButton);
        actionButtonsPanel.add(areaClearButton);
        actionButtonsPanel.add(areaNotClearButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        privateMessagePanel.add(privateMessageLabel, gbc);
        gbc.gridy++;
        privateMessagePanel.add(privateMessageField, gbc);
        gbc.gridy++;
        privateMessagePanel.add(sendPrivateMessageButton, gbc);

        add(controlPanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(messageScrollPane, BorderLayout.EAST);

        collectInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedUnit != null) {
                    soldierCountLabel.setText("Soldier Count: " + selectedUnit.getSoldierCount());
                    fuelAmountLabel.setText("Fuel Amount: " + selectedUnit.getFuelAmount());
                    ammoAmountLabel.setText("Ammo Amount: " + selectedUnit.getAmmoAmount());
                    positionLevelLabel.setText("Position Level: " + selectedUnit.getPositionLevel());
                    positionSlider.setValue(selectedUnit.getPositionLevel() * 10);
                }
            }
        });

        areaClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedUnit != null) {
                    selectedUnit.sendMessage("Is the Area Clear ?...");
                    messageBox.append("Area Clear ?...massege sent\n");
                }
            }
        });

        areaNotClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedUnit != null) {
                    selectedUnit.sendMessage("Get position...");
                    messageBox.append("Get position message sent.\n");
                }
            }
        });

        sendPrivateMessageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedUnit != null && !privateMessageField.getText().isEmpty()) {
                    selectedUnit.sendMessage("Private Message: " + privateMessageField.getText());
                    messageBox.append("Private Message sent: " + privateMessageField.getText() + "\n");
                    privateMessageField.setText("");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        MainController mainController = new MainController();

        Tank tank = new Tank(mainController);
        Helicopter helicopter = new Helicopter(mainController);
        Submarine submarine = new Submarine(mainController);

        mainController.addUnit(tank);
        mainController.addUnit(helicopter);
        mainController.addUnit(submarine);

        tank.setupGUI();
        helicopter.setupGUI();
        submarine.setupGUI();
    }
}
