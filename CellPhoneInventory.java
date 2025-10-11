import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CellPhoneInventory class manages the inventory of cell phones.
 * Provides GUI for adding, updating, removing, and viewing phone records.
 */
public class CellPhoneInventory extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String INVENTORY_FILE = "inventory.dat";
    
    private List<CellPhone> inventory;
    private DefaultTableModel tableModel;
    private JTable inventoryTable;
    private String currentUser;
    
    // GUI Components
    private JTextField idField, brandField, modelField, priceField, quantityField, colorField, storageField;
    private JButton addButton, updateButton, deleteButton, clearButton, searchButton;
    
    public CellPhoneInventory(String username) {
        this.currentUser = username;
        this.inventory = new ArrayList<>();
        loadInventory();
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Cell Phone Inventory Management System - User: " + currentUser);
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel for input fields
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Center panel for table
        JPanel tablePanel = createTablePanel();
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Bottom panel for buttons
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Menu bar
        createMenuBar();
        
        setVisible(true);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Phone Details"));
        
        // Initialize text fields
        idField = new JTextField();
        brandField = new JTextField();
        modelField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();
        colorField = new JTextField();
        storageField = new JTextField();
        
        // Add components
        panel.add(new JLabel("Phone ID:"));
        panel.add(idField);
        panel.add(new JLabel("Brand:"));
        panel.add(brandField);
        
        panel.add(new JLabel("Model:"));
        panel.add(modelField);
        panel.add(new JLabel("Price ($):"));
        panel.add(priceField);
        
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Color:"));
        panel.add(colorField);
        
        panel.add(new JLabel("Storage (GB):"));
        panel.add(storageField);
        panel.add(new JLabel("")); // Empty label for spacing
        panel.add(new JLabel(""));
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Inventory"));
        
        // Create table model
        String[] columnNames = {"ID", "Brand", "Model", "Price", "Quantity", "Color", "Storage (GB)"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        inventoryTable = new JTable(tableModel);
        inventoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inventoryTable.getTableHeader().setReorderingAllowed(false);
        
        // Add selection listener
        inventoryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = inventoryTable.getSelectedRow();
                if (selectedRow != -1) {
                    populateFieldsFromTable(selectedRow);
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        refreshTable();
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        addButton = new JButton("Add Phone");
        updateButton = new JButton("Update Phone");
        deleteButton = new JButton("Delete Phone");
        clearButton = new JButton("Clear Fields");
        searchButton = new JButton("Search");
        
        // Add action listeners
        addButton.addActionListener(e -> addPhone());
        updateButton.addActionListener(e -> updatePhone());
        deleteButton.addActionListener(e -> deletePhone());
        clearButton.addActionListener(e -> clearFields());
        searchButton.addActionListener(e -> searchPhone());
        
        // Style buttons
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(52, 152, 219));
        updateButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        clearButton.setBackground(new Color(149, 165, 166));
        clearButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(241, 196, 15));
        searchButton.setForeground(Color.WHITE);
        
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(clearButton);
        panel.add(searchButton);
        
        return panel;
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save Inventory");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        saveItem.addActionListener(e -> {
            saveInventory();
            JOptionPane.showMessageDialog(this, "Inventory saved successfully!");
        });
        
        exitItem.addActionListener(e -> {
            saveInventory();
            System.exit(0);
        });
        
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, 
                "Cell Phone Inventory Management System\nVersion 1.0\n\nManage your phone inventory efficiently.",
                "About", JOptionPane.INFORMATION_MESSAGE));
        
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void addPhone() {
        try {
            String id = idField.getText().trim();
            String brand = brandField.getText().trim();
            String model = modelField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            String color = colorField.getText().trim();
            int storage = Integer.parseInt(storageField.getText().trim());
            
            if (id.isEmpty() || brand.isEmpty() || model.isEmpty() || color.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check if ID already exists
            for (CellPhone phone : inventory) {
                if (phone.getId().equals(id)) {
                    JOptionPane.showMessageDialog(this, "Phone ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            CellPhone newPhone = new CellPhone(id, brand, model, price, quantity, color, storage);
            inventory.add(newPhone);
            refreshTable();
            clearFields();
            saveInventory();
            
            JOptionPane.showMessageDialog(this, "Phone added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Price, Quantity, and Storage!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updatePhone() {
        try {
            String id = idField.getText().trim();
            
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a phone to update!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            CellPhone phoneToUpdate = null;
            for (CellPhone phone : inventory) {
                if (phone.getId().equals(id)) {
                    phoneToUpdate = phone;
                    break;
                }
            }
            
            if (phoneToUpdate == null) {
                JOptionPane.showMessageDialog(this, "Phone not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String brand = brandField.getText().trim();
            String model = modelField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            String color = colorField.getText().trim();
            int storage = Integer.parseInt(storageField.getText().trim());
            
            phoneToUpdate.setBrand(brand);
            phoneToUpdate.setModel(model);
            phoneToUpdate.setPrice(price);
            phoneToUpdate.setQuantity(quantity);
            phoneToUpdate.setColor(color);
            phoneToUpdate.setStorage(storage);
            
            refreshTable();
            clearFields();
            saveInventory();
            
            JOptionPane.showMessageDialog(this, "Phone updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deletePhone() {
        String id = idField.getText().trim();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a phone to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this phone?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            inventory.removeIf(phone -> phone.getId().equals(id));
            refreshTable();
            clearFields();
            saveInventory();
            
            JOptionPane.showMessageDialog(this, "Phone deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void searchPhone() {
        String searchTerm = JOptionPane.showInputDialog(this, "Enter Phone ID, Brand, or Model to search:");
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return;
        }
        
        searchTerm = searchTerm.toLowerCase();
        tableModel.setRowCount(0);
        
        boolean found = false;
        for (CellPhone phone : inventory) {
            if (phone.getId().toLowerCase().contains(searchTerm) ||
                phone.getBrand().toLowerCase().contains(searchTerm) ||
                phone.getModel().toLowerCase().contains(searchTerm)) {
                
                Object[] row = {
                    phone.getId(),
                    phone.getBrand(),
                    phone.getModel(),
                    String.format("$%.2f", phone.getPrice()),
                    phone.getQuantity(),
                    phone.getColor(),
                    phone.getStorage()
                };
                tableModel.addRow(row);
                found = true;
            }
        }
        
        if (!found) {
            JOptionPane.showMessageDialog(this, "No matching phones found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        }
    }
    
    private void clearFields() {
        idField.setText("");
        brandField.setText("");
        modelField.setText("");
        priceField.setText("");
        quantityField.setText("");
        colorField.setText("");
        storageField.setText("");
    }
    
    private void populateFieldsFromTable(int row) {
        idField.setText(tableModel.getValueAt(row, 0).toString());
        brandField.setText(tableModel.getValueAt(row, 1).toString());
        modelField.setText(tableModel.getValueAt(row, 2).toString());
        String price = tableModel.getValueAt(row, 3).toString().replace("$", "");
        priceField.setText(price);
        quantityField.setText(tableModel.getValueAt(row, 4).toString());
        colorField.setText(tableModel.getValueAt(row, 5).toString());
        storageField.setText(tableModel.getValueAt(row, 6).toString());
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (CellPhone phone : inventory) {
            Object[] row = {
                phone.getId(),
                phone.getBrand(),
                phone.getModel(),
                String.format("$%.2f", phone.getPrice()),
                phone.getQuantity(),
                phone.getColor(),
                phone.getStorage()
            };
            tableModel.addRow(row);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadInventory() {
        File file = new File(INVENTORY_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                inventory = (List<CellPhone>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                inventory = new ArrayList<>();
                System.err.println("Error loading inventory: " + e.getMessage());
            }
        }
    }
    
    private void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INVENTORY_FILE))) {
            oos.writeObject(inventory);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving inventory: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CellPhoneInventory("Admin"));
    }
}
