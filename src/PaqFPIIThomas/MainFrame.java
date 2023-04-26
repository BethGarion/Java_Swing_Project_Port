package PaqFPIIThomas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame{
    private JTextField tfIDNumber; // Text field for input of Id
    private JComboBox cbCountry; // Combo Box to choose the country
    private JTextField tfWeight; // Text field for the weight of the container
    private JRadioButton btnPriority1; // Radio button to select priority 1
    private JRadioButton btnPriority2; // Radio button to select priority 2
    private JRadioButton btnPriority3; // Radio button to select priority 3
    private JTextArea descriptionIsShowHereTextArea; // The description of the container will be writing in this Text Area
    private JTextArea stateInHubPlanTextArea; // Text Area for showing the state of the container in the Hub plan
    private JTextField tfRemittentCompany; // In this text field we will write the sender company of the container
    private JTextField tfReceiverCompany; // In this text field we will write the receiver company of the container
    private JButton pileButton; // This button is used to stack the container to the Hub, we need to verify that every information have been filled
    private JButton unpileButton; // This button is used to remove a container from the Hub, we pass the Column number as a parameter
    private JButton showContainerDescriptionButton; // This button allow us to display in 'showContainerDescriptionHereTextArea' the description of a container
    private JButton numberOfContainersButton; // This button use 'cbNumberOfContainerFromACountry' combo box for displaying in 'tfHowMuchContainerACountryHave' the number of container by a country.
    private JCheckBox checkBoxCustomInspection; // is the container have been inspected
    private JTextArea showContainerDescriptionHereTextArea; // the description of a container will be display here
    private JComboBox cbNumberOfContainerFromACountry; // The combo box to choose the country, as a parameter for the action of button 'numberOfContainersButton'
    private JTextField tfHowMuchContainerACountryHave; // This text field will be display the number of container by a specific country.
    private JPanel mainPanel;
    private JTextField rowOfContainerToRemoveTextField;
    private JTextField enterIDOfContainerTextField;
    private JTextField columnOfContainerToRemoveTextField;


    public MainFrame(Hub hub) {

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Port Management");
        setSize(1000,500);
        setVisible(true);

        Port port = new Port();

        cbCountry.addItem("France");
        cbCountry.addItem("Spain");
        cbCountry.addItem("Germany");
        cbCountry.addItem("Russia");
        cbCountry.addItem("USA");
        cbCountry.addItem("Canada");
        cbCountry.addItem("Mexico");


        cbNumberOfContainerFromACountry.addItem("France");
        cbNumberOfContainerFromACountry.addItem("Spain");
        cbNumberOfContainerFromACountry.addItem("Germany");
        cbNumberOfContainerFromACountry.addItem("Russia");
        cbNumberOfContainerFromACountry.addItem("USA");
        cbNumberOfContainerFromACountry.addItem("Canada");
        cbNumberOfContainerFromACountry.addItem("Mexico");


        cbCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        // Button to show how much container are in the hub from a specific country
        // Try catch need to be done here
        numberOfContainersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port.getQuantityOfContainersFromCountry((String) cbNumberOfContainerFromACountry.getSelectedItem());
            }
        });

        // Button to Add a new Container, management of exception done
        pileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int priority;
                    if(btnPriority1.isSelected()) {
                        priority=1;
                    } else if (btnPriority2.isSelected()) {
                        priority=2;
                    } else if (btnPriority3.isSelected()) {
                        priority=3;
                    } else {
                        throw new Exception("Please select a priority level.");
                    }
                    Container Current = new Container(
                            Integer.valueOf(tfIDNumber.getText()),
                            Integer.valueOf(tfWeight.getText()),
                            (String) cbCountry.getSelectedItem(),
                            checkBoxCustomInspection.isSelected(),
                            priority,
                            descriptionIsShowHereTextArea.getText(),
                            tfRemittentCompany.getText(),
                            tfReceiverCompany.getText()
                    );
                    if (tfRemittentCompany.getText().equals("") || tfReceiverCompany.getText().equals("")) {
                        throw new Exception("Please enter values for Remittent Company and Receiver Company.");
                    }
                    port.stackContainer(Current);
                    clearContainerFields();
                    JOptionPane.showMessageDialog(mainPanel, "Container added to stack.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter valid numbers for ID number and weight.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, ex.getMessage());
                }
            }
        });


        // Button to remove a container from the hub, using the input row and column
        unpileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.valueOf(rowOfContainerToRemoveTextField.getText());
                int column = Integer.valueOf(columnOfContainerToRemoveTextField.getText());

                ArrayList<Container> containers = hub.getContainers();
                Container[][] storage = hub.getStorage();

                // Find the container in the stack and remove it
                for (int i = 0; i < storage[0].length; i++) {
                    for (int j = 0; j < storage[0].length; j++)
                        if (storage[i][j] != null) {
                            port.removeContainer(row, column);
                            JOptionPane.showMessageDialog(mainPanel, "Container removed from stack.");
                        } else {
                            JOptionPane.showMessageDialog(mainPanel, "There is no container in column " + column + " and row " + row + ".");
                        }
                }
            }
        });

        // Show us the description of 1 container based on the ID number of the container
        showContainerDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idNumber = tfIDNumber.getText();
                String country = (String) cbCountry.getSelectedItem();
                int weight = Integer.parseInt(tfWeight.getText());
                int priority = getSelectedPriority();
                String remittentCompany = tfRemittentCompany.getText();
                String receiverCompany = tfReceiverCompany.getText();
                boolean customInspection = checkBoxCustomInspection.isSelected();

                String description = generateDescription(idNumber, country, weight, priority, remittentCompany,
                        receiverCompany, customInspection);
                showContainerDescriptionHereTextArea.setText(description);
            }
        });
    }

    private String generateDescription(String idNumber, String country, int weight, int priority,
                                       String remittentCompany, String receiverCompany, boolean customInspection) {
        StringBuilder sb = new StringBuilder();
        sb.append("Container ID: ").append(idNumber).append("\n");
        sb.append("Country: ").append(country).append("\n");
        sb.append("Weight: ").append(weight).append("\n");
        sb.append("Priority: ").append(priority).append("\n");
        sb.append("Remittent Company: ").append(remittentCompany).append("\n");
        sb.append("Receiver Company: ").append(receiverCompany).append("\n");
        sb.append("Custom Inspection: ").append(customInspection ? "Yes" : "No");

        return sb.toString();
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
        String remittentCompany = tfRemittentCompany.getText();
        String receiverCompany = tfReceiverCompany.getText();

        if (idNumber.isEmpty() || country == null || weightText.isEmpty() || remittentCompany.isEmpty() ||
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

    private void clearContainerFields() {
        tfIDNumber.setText("");
        cbCountry.setSelectedIndex(0);
        tfWeight.setText("");
        btnPriority1.setSelected(false);
        btnPriority2.setSelected(false);
        btnPriority3.setSelected(false);
        tfRemittentCompany.setText("");
        tfReceiverCompany.setText("");
        checkBoxCustomInspection.setSelected(false);
        showContainerDescriptionHereTextArea.setText("");
    }

    public static void main(String[] args) {
        Hub hub = new Hub();
        MainFrame PortManagement = new MainFrame(hub);
    }
}