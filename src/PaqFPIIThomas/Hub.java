package PaqFPIIThomas;

import java.util.ArrayList;

public class Hub {
    private Container[][] storage;
    private ArrayList<Container> containers;

    public Hub() {
        storage = new Container[10][12];
        containers = new ArrayList<>();
    }

    public Container[][] getStorage() {
        return storage;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public boolean stackContainer(Container container) {
        int priority = container.getPriority();
        for (int i = 0; i < storage.length; i++) {
            if (priority == 1) {
                if (storage[i][0] == null) {
                    storage[i][0] = container;
                    containers.add(container);
                    return true;
                }
            } else if (priority == 2) {
                if (storage[i][1] == null) {
                    storage[i][1] = container;
                    containers.add(container);
                    return true;
                }
            } else {
                for (int j = 2; j < storage[i].length; j++) {
                    if (storage[i][j] == null) {
                        storage[i][j] = container;
                        containers.add(container);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeContainer(int column, int row) {
        if (storage[row][column] == null) {
            return false;
        } else {
            containers.remove(storage[row][column]);
            storage[row][column] = null;
            return true;
        }
    }

    public Container findContainerById(int id) {
        for (Container container : containers) {
            if (container.getId() == id) {
                return container;
            }
        }
        return null;
    }

    public int countContainersByCountry(String country) {
        int count = 0;
        for (Container container : containers) {
            if (container.getCountryOfOrigin().equals(country)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[i].length; j++) {
                if (storage[i][j] == null) {
                    sb.append("[     ]");
                } else {
                    sb.append(storage[i][j]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}