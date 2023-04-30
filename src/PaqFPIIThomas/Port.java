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

    /*public boolean stackContainer(Container container,  int selectedHub) {
        boolean returnValue = false;

        if(selectedHub == 1) {
            allTheHubs[0].stackContainer(container);
            returnValue = true;
        } else if (selectedHub == 2) {
            allTheHubs[1].stackContainer(container);
            returnValue = true;
        }  else if (selectedHub == 3) {
            allTheHubs[2].stackContainer(container);
            returnValue = true;
        }

        return returnValue;
    }*/

    public void checkAvailable(Container container){
        // Depending on the result of the addContainer function of each hub, it goes to the next
        if(allTheHubs[0].stackContainer(container) == false){
            if(allTheHubs[1].stackContainer(container) == false){
                if(allTheHubs[2].stackContainer(container) == false){
                    System.out.println("Port is full");
                }
            }
        }
    }
    /*
    public Container removeContainer(int col) {
        Container container = hub.getStorage()[row][col];
        hub.getStorage()[row][col] = null;
        hub.getContainers().remove(container);
        return container;
    }*/

    /*public Container getContainerById(int id, int selectedHub) {
        if(selectedHub == 1) {
            for (Container container : allTheHubs[0].getContainers()) {
                if (container.getId() == id) {
                    return container;
                }
            }
        } else if (selectedHub == 2) {
            for (Container container : allTheHubs[1].getContainers()) {
                if (container.getId() == id) {
                    return container;
                }
            }
        }  else if (selectedHub == 3) {
            for (Container container : allTheHubs[2].getContainers()) {
                if (container.getId() == id) {
                    return container;
                }
            }
        }

        System.out.println("Container with Id " + id + " is not in the hub.");
        return null;
    }*/

    /*public int getQuantityOfContainersFromCountry(String country) {
        int count = 0;

        for (Container container : allTheHubs[0].getContainers()) {
            if (container.getCountryOfOrigin().equals(country)) {
                count++;
            }
        }

        for (Container container : allTheHubs[1].getContainers()) {
            if (container.getCountryOfOrigin().equals(country)) {
                count++;
            }
        }

        for (Container container : allTheHubs[2].getContainers()) {
            if (container.getCountryOfOrigin().equals(country)) {
                count++;
            }
        }

        return count;
    }*/

    /*
    @Override
    public String toString() {
        Container[][] storage = hub.getStorage();
        StringBuilder sb = new StringBuilder();
        sb.append("Hub Plan:\n");
        for (int i = 0; i < storage.length; i++) {
            sb.append(String.format("Row %d: ", i + 1));
            for (int j = 0; j < storage[i].length; j++) {
                Container container = storage[i][j];
                if (container == null) {
                    sb.append("X ");
                } else {
                    sb.append(container.getPriority()).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }*/
}