import java.io.Serializable;

/**
 * CellPhone class represents a cell phone in the inventory system.
 * It contains attributes such as model, brand, price, quantity, and unique ID.
 */
public class CellPhone implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String brand;
    private String model;
    private double price;
    private int quantity;
    private String color;
    private int storage; // Storage in GB
    
    /**
     * Constructor for CellPhone
     */
    public CellPhone(String id, String brand, String model, double price, int quantity, String color, int storage) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.storage = storage;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public String getColor() {
        return color;
    }
    
    public int getStorage() {
        return storage;
    }
    
    // Setters
    public void setId(String id) {
        this.id = id;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public void setStorage(int storage) {
        this.storage = storage;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | Brand: %s | Model: %s | Price: $%.2f | Quantity: %d | Color: %s | Storage: %dGB",
                id, brand, model, price, quantity, color, storage);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CellPhone cellPhone = (CellPhone) obj;
        return id.equals(cellPhone.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
