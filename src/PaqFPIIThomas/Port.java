package PaqFPIIThomas;

public class Port {
    private Hub hub;

    public Port() {
        this.hub = new Hub();
    }

    public boolean stackContainer(Container container) {
        return hub.stackContainer(container);
    }

    public Container removeContainer(int row, int col) {
        Container container = hub.getStorage()[row][col];
        hub.getStorage()[row][col] = null;
        hub.getContainers().remove(container);
        return container;
    }

    public Container getContainerById(int id) {
        for (Container container : hub.getContainers()) {
            if (container.getId() == id) {
                return container;
            }
        }
        System.out.println("Container with Id " + id + " is not in the hub.");
        return null;
    }

    public int getQuantityOfContainersFromCountry(String country) {
        int count = 0;
        for (Container container : hub.getContainers()) {
            if (container.getCountryOfOrigin().equals(country)) {
                count++;
            }
        }
        return count;
    }

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
    }
}