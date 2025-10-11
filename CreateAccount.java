import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * CreateAccount class handles user account creation with validation.
 * Stores user credentials securely and validates input data.
 */
public class CreateAccount extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String USERS_FILE = "users.dat";
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JTextField fullNameField;
    private JButton createButton;
    private JButton cancelButton;
    private JLabel statusLabel;
    
    public CreateAccount() {
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Create New Account");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(236, 240, 241));
        
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(52, 73, 94));
        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        formPanel.setBackground(new Color(236, 240, 241));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        
        // Full Name
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fullNameField = new JTextField();
        fullNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        formPanel.add(fullNameLabel);
        formPanel.add(fullNameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(confirmPasswordLabel);
        formPanel.add(confirmPasswordField);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(236, 240, 241));
        
        createButton = new JButton("Create Account");
        createButton.setFont(new Font("Arial", Font.BOLD, 14));
        createButton.setBackground(new Color(46, 204, 113));
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);
        createButton.setPreferredSize(new Dimension(150, 35));
        createButton.addActionListener(e -> createAccount());
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(231, 76, 60));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setPreferredSize(new Dimension(150, 35));
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);
        
        // Status label
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(236, 240, 241));
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Add Enter key listener
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createAccount();
                }
            }
        };
        
        fullNameField.addKeyListener(enterKeyListener);
        emailField.addKeyListener(enterKeyListener);
        usernameField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);
        confirmPasswordField.addKeyListener(enterKeyListener);
        
        setVisible(true);
    }
    
    private void createAccount() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        // Validation
        if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || 
            password.isEmpty() || confirmPassword.isEmpty()) {
            showError("All fields are required!");
            return;
        }
        
        if (!isValidEmail(email)) {
            showError("Invalid email format!");
            return;
        }
        
        if (username.length() < 4) {
            showError("Username must be at least 4 characters!");
            return;
        }
        
        if (password.length() < 6) {
            showError("Password must be at least 6 characters!");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match!");
            return;
        }
        
        // Check if username already exists
        Map<String, UserAccount> users = loadUsers();
        if (users.containsKey(username)) {
            showError("Username already exists!");
            return;
        }
        
        // Create new account
        UserAccount newUser = new UserAccount(username, password, email, fullName);
        users.put(username, newUser);
        
        if (saveUsers(users)) {
            JOptionPane.showMessageDialog(this, 
                "Account created successfully!\nYou can now login with your credentials.",
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            showError("Error saving account. Please try again.");
        }
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }
    
    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(Color.RED);
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, UserAccount> loadUsers() {
        File file = new File(USERS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (Map<String, UserAccount>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading users: " + e.getMessage());
            }
        }
        return new HashMap<>();
    }
    
    private boolean saveUsers(Map<String, UserAccount> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
            return false;
        }
    }
    
    // Inner class for user account data
    static class UserAccount implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String username;
        private String password;
        private String email;
        private String fullName;
        
        public UserAccount(String username, String password, String email, String fullName) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.fullName = fullName;
        }
        
        public String getUsername() {
            return username;
        }
        
        public String getPassword() {
            return password;
        }
        
        public String getEmail() {
            return email;
        }
        
        public String getFullName() {
            return fullName;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateAccount());
    }
}
