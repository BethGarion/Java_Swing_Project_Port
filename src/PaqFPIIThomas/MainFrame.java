// THOMAS PERRAULT
package PaqFPIIThomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField enterIDOfContainerTextField;
    private JTextField columnOfContainerToRemoveTextField;
    private JRadioButton firstHubRadioButton;
    private JRadioButton secondHubRadioButton;
    private JRadioButton thirdHubRadioButton;
    private JLabel companyLogo;
    private JButton examButton;
    private JTextField tfWeightInput;
    private JTextArea weightControltextArea;
    static int selectedHub;


    public MainFrame() {

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Port Management");
        setSize(1500,800);
        setVisible(true);


        Hub firstHub = new Hub();
        Hub secondHub = new Hub();
        Hub thirdHub = new Hub();

        Port port = new Port(firstHub, secondHub, thirdHub);
        firstHubRadioButton.setSelected(true);

        // We add a few country to the country selection combo box
        cbCountry.addItem("Select a country");
        cbCountry.addItem("France");
        cbCountry.addItem("Spain");
        cbCountry.addItem("Germany");
        cbCountry.addItem("Russia");
        cbCountry.addItem("USA");
        cbCountry.addItem("Canada");
        cbCountry.addItem("Mexico");
        cbCountry.addItem("Uruguay");

        cbNumberOfContainerFromACountry.addItem("France");
        cbNumberOfContainerFromACountry.addItem("Spain");
        cbNumberOfContainerFromACountry.addItem("Germany");
        cbNumberOfContainerFromACountry.addItem("Russia");
        cbNumberOfContainerFromACountry.addItem("USA");
        cbNumberOfContainerFromACountry.addItem("Canada");
        cbNumberOfContainerFromACountry.addItem("Mexico");
        cbNumberOfContainerFromACountry.addItem("Uruguay");


        // Button to Add a new Container, management of exception done
        pileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // First I deal with the priority
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

                    //Checking which hub is selected
                    if(firstHubRadioButton.isSelected()){
                        selectedHub = 0;
                    } else if (secondHubRadioButton.isSelected()) {
                        selectedHub = 1;
                    }else if (thirdHubRadioButton.isSelected()){
                        selectedHub = 2;
                    }

                    // Then I check if these field text have been filled
                    if (tfRemittentCompany.getText().equals("") || tfReceiverCompany.getText().equals("")) {
                        throw new Exception("Please enter values for Remittent Company and Receiver Company.");
                    }

                    // Then I check if a country have been chosen
                    if (cbCountry.getSelectedItem().equals("Select a country")) {
                        throw new Exception("Please select a country.");
                    }

                    // I reuse the 'findContainerById' method to see if the user set a new ID, and do not try to use an ID already used
                    for(int i = 0; i < 3; i++) {
                        String verifyID;
                        verifyID = port.allTheHubs[i].findContainerById(Integer.parseInt(tfIDNumber.getText()), 2);

                        if (verifyID == "Wait that illegal, this ID looks like an already used ID.") {
                            throw new Exception("Please enter an ID which has not already been used.");
                        }
                    }



                    // I create a new container Object and I use the field of my GUI to fill the parameter of my constructor
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

                    // I add the container, with the checkAvailable who will check if Hub is not full
                    // Then the checkAvailable method will call stackContainer method in Hub
                    port.checkAvailable(Current);
                    // I can use the following method to reset the field, but for testing it's not practical
                    //clearContainerFields();

                    // Now I refresh the state of the Hub in the Hub plan display
                    stateInHubPlanTextArea.setText(port.allTheHubs[selectedHub].hubDisplay());

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
                try {

                    //Checking which hub is selected
                    if(firstHubRadioButton.isSelected()){
                        selectedHub = 0;
                    } else if (secondHubRadioButton.isSelected()) {
                        selectedHub = 1;
                    }else if (thirdHubRadioButton.isSelected()){
                        selectedHub = 2;
                    }

                    boolean removed = port.allTheHubs[selectedHub].removeContainer(Integer.parseInt(columnOfContainerToRemoveTextField.getText()));
                    stateInHubPlanTextArea.setText(port.allTheHubs[selectedHub].hubDisplay());
                    if (removed == true) {
                        JOptionPane.showMessageDialog(mainPanel, "Container removed from stack.");
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "There is no container in this column.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter valid numbers for column number.");
                }
            }
        });

        // Show us the description of 1 container based on the ID number of the container
        showContainerDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Checking which hub is selected
                    if(firstHubRadioButton.isSelected()){
                        selectedHub = 0;
                    } else if (secondHubRadioButton.isSelected()) {
                        selectedHub = 1;
                    }else if (thirdHubRadioButton.isSelected()){
                        selectedHub = 2;
                    }

                    String showDescription;
                    showDescription = port.allTheHubs[selectedHub].findContainerById(Integer.parseInt(enterIDOfContainerTextField.getText()), 1);
                    showContainerDescriptionHereTextArea.setText(showDescription);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter valid numbers for the ID.");
                }
            }
        });

        // Button to show how much container are in the hub from a specific country
        // Try catch need to be done here
        numberOfContainersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfContainers = 0;
                for(int c = 0; c < 3; c++) {
                    numberOfContainers = numberOfContainers + port.allTheHubs[c].countContainersByCountry((String) cbNumberOfContainerFromACountry.getSelectedItem());
                }
                tfHowMuchContainerACountryHave.setText(String.valueOf(numberOfContainers));
            }
        });

        // I check the weight of all the container with a input weight (that I get from a text field)
        // If i found container with weight less or equal to my input weight, i display their information (ID, sender company, weight, inspected) in a text area And a new windows
        examButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Checking which hub is selected
                    if(firstHubRadioButton.isSelected()){
                        selectedHub = 0;
                    } else if (secondHubRadioButton.isSelected()) {
                        selectedHub = 1;
                    }else if (thirdHubRadioButton.isSelected()){
                        selectedHub = 2;
                    }

                    // I'm creating here the string who will be show, because my method return a string.
                    String showInformation;
                    showInformation = port.allTheHubs[selectedHub].checkWeight(Integer.parseInt(tfWeightInput.getText()));
                    // Here I show my string in a text Area
                    weightControltextArea.setText(showInformation);

                    // And here I show my string in a new windows
                    // I have a problem who is if i have too much container that windows disappear at the bottom of the screen
                    // I should add a defined dimension and a scroll bar to the windows
                    JScrollPane scrollPane = new JScrollPane(weightControltextArea);  // I add my scroll pane component
                    scrollPane.setPreferredSize(new Dimension(400, 500)); // I set the dimension of my new window
                    JOptionPane.showMessageDialog(mainPanel, scrollPane); // Now i show it
                    //JOptionPane.showMessageDialog(mainPanel, showInformation); // I first use this, but I can not add a dimension or scroll bar

                    // Management of exception for verified that the user input a valid number for the weight
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter a valid weight number.");
                }
            }
        });

        // The 3 following button allow me to display the interface of the 3 different Hubs.
        firstHubRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstHubRadioButton.isSelected()){
                    stateInHubPlanTextArea.setText(port.allTheHubs[0].hubDisplay());
                }
            }
        });
        secondHubRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(secondHubRadioButton.isSelected()){
                    stateInHubPlanTextArea.setText(port.allTheHubs[1].hubDisplay());
                }
            }
        });
        thirdHubRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(thirdHubRadioButton.isSelected()){
                    stateInHubPlanTextArea.setText(port.allTheHubs[2].hubDisplay());
                }
            }
        });
    }

    // I reset the field of each Text fields / text area. Can be usefully if I don't want the user to put the same information everywhere
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
        descriptionIsShowHereTextArea.setText("");
    }

    public static void main(String[] args) {
        MainFrame PortManagement = new MainFrame();
    }
}