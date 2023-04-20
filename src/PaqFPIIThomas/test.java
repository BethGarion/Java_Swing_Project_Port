package PaqFPIIThomas;
// This is a test class, this class should not be count as part of the project
// If this class is still there, please delete it.
public class test {
    /*package PaqGroupName;

package PaqGroupName;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrame {
    private JPanel mainPanel;
    private JTextField tfIDNumber;
    private JComboBox<String> cbCountry;
    private JTextField tfWeight;
    private JRadioButton btnPriority1;
    private JRadioButton btnPriority2;
    private JRadioButton btnPriority3;
    private JTextArea descriptionIsShowHereTextArea;
    private JTextArea stateInHubPlanTextArea;
    private JTextField tfRemitentCompany;
    private JTextField tfReceiverCompany;
    private JButton pileButton;
    private JButton unpileButton;
    private JButton showContainerDescriptionButton;
    private JButton numberOfContainersButton;
    private JCheckBox checkBoxCustomInspection;
    private JTextField columnNumberTextField;
    private JTextArea showContainerDescriptionHereTextArea;
    private JComboBox<String> cbNumberOfContainerFromACountry;
    private JTextField tfHowMuchContainerACountryHave;

    private final Map<String, Integer> containerCountByCountry;

    public MainFrame() {
        containerCountByCountry = new HashMap<>();

        cbCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNumberOfContainers();
            }
        });

        numberOfContainersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNumberOfContainers();
            }
        });

        pileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateContainer()) {
                    stackContainer();
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Please fill all fields and select a priority.");
                }
            }
        });

        unpileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int column = Integer.parseInt(columnNumberTextField.getText());
                    removeContainer(column);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter a valid column number.");
                }
            }
        });

        showContainerDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idNumber = tfIDNumber.getText();
                String country = (String) cbCountry.getSelectedItem();
                int weight = Integer.parseInt(tfWeight.getText());
                int priority = getSelectedPriority();
                String remitentCompany = tfRemitentCompany.getText();
                String receiverCompany = tfReceiverCompany.getText();
                boolean customInspection = checkBoxCustomInspection.isSelected();

                String description = generateDescription(idNumber, country, weight, priority, remitentCompany,
                        receiverCompany, customInspection);
                showContainerDescriptionHereTextArea.setText(description);
            }
        });
    }

    private int getSelectedPriority() {
        if (btnPriority1.isSelected()) {
            return 1;
        } else if (btnPriority2.isSelected()) {
            return 2;
        } else if (btnPriority3.isSelected()) {
            return 3;
        } else {
            return 0;
        }
    }

    private boolean validateContainer() {
        String idNumber = tfIDNumber.getText();
        String country = (String) cbCountry.getSelectedItem();
        String weightText = tfWeight.getText();
        String remitentCompany = tfRemitentCompany.getText();
        String receiverCompany = tfReceiverCompany.getText();

        if (idNumber.isEmpty() || country == null || weightText.isEmpty() || remitentCompany.isEmpty() ||
                receiverCompany.isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(weightText);
        } catch (NumberFormatException e) {
            return false;
        }

        return getSelectedPriority() != 0;
    }

    private void stackContainer() {
    String idNumber = tfIDNumber.getText();
    String country = (String) cbCountry.getSelectedItem();
    int weight = Integer.parseInt(tfWeight.getText());
    int priority = getSelectedPriority();
    String remitentCompany = tfRemitentCompany.getText();
    String receiverCompany = tfReceiverCompany.getText();
    boolean customInspection = checkBoxCustomInspection.isSelected();

    ontainer container = new Container(idNumber, country, weight, priority, remitentCompany,
            receiverCompany, customInspection);

    int count = containerCountByCountry.getOrDefault(country, 0);
    containerCountByCountry.put(country, count + 1);

    String columnText = JOptionPane.showInputDialog(mainPanel, "Enter the column number to stack the container in.");
    int column = Integer.parseInt(columnText);

    if (column >= 1 && column <= ContainerShip.NUM_COLUMNS) {
        ContainerShip.getInstance().stackContainer(container, column);
        stateInHubPlanTextArea.setText(ContainerShip.getInstance().toString());
        updateNumberOfContainers();
        clearContainerFields();
    } else {
        JOptionPane.showMessageDialog(mainPanel, "Please enter a valid column number.");
    }
}

private void removeContainer(int column) {
    try {
        ContainerShip.getInstance().removeContainer(column);
        stateInHubPlanTextArea.setText(ContainerShip.getInstance().toString());
        updateNumberOfContainers();
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(mainPanel, e.getMessage());
    }
}

private String generateDescription(String idNumber, String country, int weight, int priority,
                                    String remitentCompany, String receiverCompany, boolean customInspection) {
    StringBuilder sb = new StringBuilder();
    sb.append("Container ID: ").append(idNumber).append("\n");
    sb.append("Country: ").append(country).append("\n");
    sb.append("Weight: ").append(weight).append("\n");
    sb.append("Priority: ").append(priority).append("\n");
    sb.append("Remitent Company: ").append(remitentCompany).append("\n");
    sb.append("Receiver Company: ").append(receiverCompany).append("\n");
    sb.append("Custom Inspection: ").append(customInspection ? "Yes" : "No");

    return sb.toString();
}

private void updateNumberOfContainers() {
    String country = (String) cbCountry.getSelectedItem();
    int count = containerCountByCountry.getOrDefault(country, 0);
    tfHowMuchContainerACountryHave.setText(String.valueOf(count));
    cbNumberOfContainerFromACountry.removeAllItems();
    for (int i = 1; i <= count; i++) {
        cbNumberOfContainerFromACountry.addItem(String.valueOf(i));
    }
}

private void clearContainerFields() {
    tfIDNumber.setText("");
    cbCountry.setSelectedIndex(0);
    tfWeight.setText("");
    btnPriority1.setSelected(false);
    btnPriority2.setSelected(false);
    btnPriority3.setSelected(false);
    tfRemitentCompany.setText("");
    tfReceiverCompany.setText("");
    checkBoxCustomInspection.setSelected(false);
    showContainerDescriptionHereTextArea.setText("");
}

public static void main(String[] args) {
    JFrame frame = new JFrame("Container Ship");
    frame.setContentPane(new MainFrame().mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
}
}


package PaqGroupName;

import javax.swing.*;

public class MainFrame {
    private JPanel mainPanel;
    private JTextField tfIDNumber; // Text field for input of Id
    private JComboBox cbCountry; // Combo Box to choose the country
    private JTextField tfWeight; // Text field for the weight of the container
    private JRadioButton btnPriority1; // Radio button to select priority 1
    private JRadioButton btnPriority2; // Radio button to select priority 2
    private JRadioButton btnPriority3; // Radio button to select priority 3
    private JTextArea descriptionIsShowHereTextArea; // The description of the container will be writing in this Text Area
    private JTextArea stateInHubPlanTextArea; // Text Area for showing the state of the container in the Hub plan
    private JTextField tfRemitentCompany; // In this text field we will write the sender company of the container
    private JTextField tfReceiverCompany; // In this text field we will write the receiver company of the container
    private JButton pileButton; // This button is used to stack the container to the Hub, we need to verify that every information have been filled
    private JButton unpileButton; // This button is used to remove a container from the Hub, we pass the Column number as a parameter
    private JButton showContainerDescriptionButton; // This button allow us to display in 'showContainerDescriptionHereTextArea' the description of a container
    private JButton numberOfContainersButton; // This button use 'cbNumberOfContainerFromACountry' combo box for displaying in 'tfHowMuchContainerACountryHave' the number of container by a country.
    private JCheckBox checkBoxCustomInspection; // is the container have been inspected
    private JTextField columnNumberTextField; // In this text Field we write the number of the column where we want to remove a container
    private JTextArea showContainerDescriptionHereTextArea; // the description of a container will be display here
    private JComboBox cbNumberOfContainerFromACountry; // The combo box to choose the country, as a parameter for the action of button 'numberOfContainersButton'
    private JTextField tfHowMuchContainerACountryHave; // This text field will be display the number of container by a specific country.
}


*/
}
