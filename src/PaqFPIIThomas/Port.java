package PaqFPIIThomas;

public class Port {
    //private Hub hub;
    public Hub[] allTheHubs = new Hub[3];

    public Port(Hub firstHub, Hub secondHub, Hub thirdHub) {
        allTheHubs[0] = firstHub;
        allTheHubs[1] = secondHub;
        allTheHubs[2] = thirdHub;

        //this.hub = new Hub();
    }

    // This method will check if a hub is full
    // And more precisely, that allow me to know if a column is full, to go to the next column (or just stop and print that the port is full)
    public void checkAvailable(Container container){
        if(allTheHubs[0].stackContainer(container) == false){
            if(allTheHubs[1].stackContainer(container) == false){
                if(allTheHubs[2].stackContainer(container) == false){
                    System.out.println("Port is full");
                }
            }
        }
    }
}