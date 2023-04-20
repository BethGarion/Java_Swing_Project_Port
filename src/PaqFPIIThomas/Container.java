package PaqFPIIThomas;

public class Container {

    // ATTRIBUTES

    private int Id;
    private int weight;
    private String countryOfOrigin;
    private boolean inspected;
    private int priority;
    private String contentDescription;
    private String senderCompany;
    private String receiverCompany;

    // CONSTRUCTORS

    public Container(int Id, int weight, String countryOfOrigin, boolean inspected, int priority, String contentDescription, String senderCompany, String receiverCompany) {
        this.Id = Id;
        this.weight = weight;
        this.countryOfOrigin = countryOfOrigin;
        this.inspected = inspected;
        this.priority = priority;
        this.contentDescription = contentDescription;
        this.senderCompany = senderCompany;
        this.receiverCompany = receiverCompany;
    }

    // SETTERS
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public void setInspected(boolean inspected) {
        this.inspected = inspected;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany;
    }

    public void setReceiverCompany(String receiverCompany) {
        this.receiverCompany = receiverCompany;
    }

    // GETTERS

    public int getId() {
        return Id;
    }

    public int getWeight() {
        return weight;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public boolean isInspected() {
        return inspected;
    }

    public int getPriority() {
        return priority;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public String getReceiverCompany() {
        return receiverCompany;
    }

    // METHODS

    @Override
    public String toString() {
        return "Container{" +
                "Id=" + Id +
                ", Weight=" + weight +
                ", Country Of Origin='" + countryOfOrigin + '\'' +
                ", Inspected=" + inspected +
                ", Priority=" + priority +
                ", Content Description='" + contentDescription + '\'' +
                ", Sender Company='" + senderCompany + '\'' +
                ", Receiver Company='" + receiverCompany + '\'' +
                '}';
    }
}