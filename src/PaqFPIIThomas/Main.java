package PaqFPIIThomas;
// This class only exist to test the code, outside the GUI
// And the code is working :)

public class Main {

    public static void main(String[] args) {

        Port port = new Port();

        // Add some containers
        port.stackContainer(new Container(1, 500, "USA", true, 1, "Clothes", "Sender1", "Receiver1"));
        port.stackContainer(new Container(2, 750, "Canada", false, 2, "Electronics", "Sender2", "Receiver2"));
        port.stackContainer(new Container(3, 1000, "Japan", true, 3, "Food", "Sender3", "Receiver3"));
        port.stackContainer(new Container(4, 1250, "Mexico", false, 3, "Toys", "Sender4", "Receiver4"));
        port.stackContainer(new Container(5, 1500, "USA", true, 1, "Books", "Sender5", "Receiver5"));
        port.stackContainer(new Container(6, 1750, "Canada", false, 2, "Furniture", "Sender6", "Receiver6"));

        // Print the plan of the hub
        System.out.println(port.toString());

        // Remove a container from the hub
        port.removeContainer(3, 1); // Remove container from row 3, column 1
        System.out.println(port.toString());

        // Display all data of a container based on its identification number
        System.out.println(port.getContainerById(4)); // Should print container 4's data

        // Calculate the quantity of containers from a certain country
        System.out.println(port.getQuantityOfContainersFromCountry("USA")); // Should print 2

    }
}