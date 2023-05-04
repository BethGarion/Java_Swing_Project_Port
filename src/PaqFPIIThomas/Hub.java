// THOMAS PERRAULT
package PaqFPIIThomas;

import java.util.ArrayList;

public class Hub {
    private Container[][] storage;

    static int ROWS = 10;
    static int COLUMNS = 12;

    public Hub() {
        storage = new Container[10][12];
        cleanPort(this.storage);
    }


    public Container[][] getStorage() {
        return storage;
    }

    public static void cleanPort(Container[][] port){
        for(int i = 0; i < COLUMNS; i++){
            for(int j = 0; j < ROWS; j++){
                port[j][i] = null;
            }
        }
    }

    // I'm adding new container to the Hub, from bottom to the top.
    public boolean stackContainer(Container container) {
        int priority = container.getPriority();
        for(int i = 0; i < COLUMNS; i++){
            for(int j = ROWS - 1; j >= 0; j--){
                if(storage[j][0] == null && priority == 1){
                    storage[j][0] = container;
                    return true;
                }
                if(storage[j][1] == null && priority == 2){
                    storage[j][1] = container;
                    return true;
                }
                if(storage[j][i] == null && i != 0 && i != 1 && priority == 3){
                    storage[j][i] = container;
                    return true;
                }
            }
        }
        return false;
    }


    // I should remove container from the top to the bottom
    public boolean removeContainer(int column) {
        boolean removed = false;
        for(int i = 0; i < ROWS; i++) {
            if (storage[i][column - 1] == null) {
                removed = false;
            } else {
                //containers.remove(storage[i][column -1]);
                storage[i][column -1] = null;
                removed = true;
                break;
            }
        }
        return removed;
    }


    // This method allow me to get the content description AND to check if an ID have been already used
    // If whatIWantToDo = 1 > we want the description of a container, if equal to 2 we want to compare 2 ID (with a specific return sentence that we use in the MainFrame.)
    public String findContainerById(int id, int whatIWantToDo) {
        String returnValue = "There is no container with this ID.";
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (storage[j][i] != null) {
                    if (storage[j][i].getId() == id) {
                        if (whatIWantToDo == 1) {
                            if(storage[j][i].getContentDescription() != null) {
                                returnValue = storage[j][i].getContentDescription();
                                break;
                            } else {
                                returnValue = "Unfortunately, there is no description for this container.";
                                break;
                            }
                        } else if (whatIWantToDo == 2) {
                            returnValue = "Wait that illegal, this ID looks like an already used ID.";
                        }
                    }
                }
            }
        }
        return returnValue;
    }

    // Exams exercise 1)
    public String checkWeight(int weight) {
        String returnValue = ""; // = "There is no container with weight less or equal to " + weight + ".";
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (storage[j][i] != null) {
                    if (storage[j][i].getWeight() <= weight) {
                        if(storage[j][i].getContentDescription() != null) {
                            storage[j][i].setInspected(true);
                            returnValue = returnValue + " This container have been inspected : \n - ID: " + String.valueOf(storage[j][i].getId() + " \n - Sender company : " + storage[j][i].getSenderCompany() + " \n - Weight : " + storage[j][i].getWeight() + " \n - Custom check status : " + storage[j][i].isInspected() + ". \n");
                        }
                    }
                }
            }
        }
        return returnValue;

    }

    // This method use 'StringBuilder'
    public String hubDisplay(){
        // Allow me to display a plan of the Hub
        StringBuilder displayedHub = new StringBuilder();
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                if(storage[i][j] != null){
                    displayedHub.append(" ■ ");
                }else{
                    displayedHub.append(" □ ");
                }
            }
            displayedHub.append("\n");
        }
        return displayedHub.toString();
    }


    public int countContainersByCountry(String country) {
        int count = 0;
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (storage[j][i] != null) {
                    if (storage[j][i].getCountryOfOrigin() == country) {
                            count++;
                        }
                    }
                }
            }
        return count;
    }
}