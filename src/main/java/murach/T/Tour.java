package murach.T;

public class Tour {
    private String id;
    private String name;
    private String country;
    private double price;
    private String description;
    private String fullDescription;
    private String image;
    private int quantity; 

    public Tour(String id, String name, String country, double price, String description, String fullDescription, String image) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.price = price;
        this.description = description;
        this.fullDescription = fullDescription;
        this.image = image;
        this.quantity = 1; 
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCountry() { return country; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getFullDescription() { return fullDescription; } 
    public String getImage() { return image; }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }
}