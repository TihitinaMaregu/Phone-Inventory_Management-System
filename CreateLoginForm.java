import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Map;

/**
 * CreateLoginForm class manages user authentication and login process.
 * Provides a GUI for users to login or create new accounts.
 */
public class CreateLoginForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String USERS_FILE = "users.dat";
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;
    private JLabel statusLabel;
    private JCheckBox showPasswordCheckBox;
    
    public CreateLoginForm() {
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Phone Inventory Management System - Login");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(44, 62, 80));
        
        // Top panel with logo/image
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(52, 73, 94));
        topPanel.setPreferredSize(new Dimension(500, 150));
        topPanel.setLayout(new BorderLayout());
        
        // Try to load the image, if not available, use text
        JLabel logoLabel;
        try {
            ImageIcon icon = new ImageIcon("user-login-305.png");
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                logoLabel = new JLabel(new ImageIcon(scaledImage));
            } else {
                logoLabel = createTextLogo();
            }
        } catch (Exception e) {
            logoLabel = createTextLogo();
        }
        
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(logoLabel, BorderLayout.CENTER);
        
        JLabel titleLabel = new JLabel("Phone Inventory System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titleLabel, BorderLayout.SOUTH);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Center panel with login form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(236, 240, 241));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        centerPanel.add(usernameLabel, gbc);
        
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(usernameField, gbc);
        
        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        centerPanel.add(passwordLabel, gbc);
        
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        centerPanel.add(passwordField, gbc);
        
        // Show password checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        showPasswordCheckBox.setBackground(new Color(236, 240, 241));
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerPanel.add(showPasswordCheckBox, gbc);
        
        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 35));
        loginButton.addActionListener(e -> login());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 10, 10, 10);
        centerPanel.add(loginButton, gbc);
        
        // Create account button
        createAccountButton = new JButton("Create New Account");
        createAccountButton.setFont(new Font("Arial", Font.BOLD, 14));
        createAccountButton.setBackground(new Color(46, 204, 113));
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setFocusPainted(false);
        createAccountButton.setPreferredSize(new Dimension(150, 35));
        createAccountButton.addActionListener(e -> openCreateAccount());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        centerPanel.add(createAccountButton, gbc);
        
        // Status label
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        centerPanel.add(statusLabel, gbc);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        // Add Enter key listener for login
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        };
        
        usernameField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);
        
        setVisible(true);
    }
    
    private JLabel createTextLogo() {
        JLabel textLogo = new JLabel("📱");
        textLogo.setFont(new Font("Arial", Font.PLAIN, 80));
        textLogo.setHorizontalAlignment(SwingConstants.CENTER);
        return textLogo;
    }
    
    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter username and password!");
            return;
        }
        
        Map<String, CreateAccount.UserAccount> users = loadUsers();
        
        if (users.isEmpty()) {
            showError("No accounts found. Please create an account first.");
            return;
        }
        
        CreateAccount.UserAccount user = users.get(username);
        
        if (user == null) {
            showError("Username not found!");
            return;
        }
        
        if (!user.getPassword().equals(password)) {
            showError("Incorrect password!");
            return;
        }
        
        // Successful login
        statusLabel.setText("Login successful!");
        statusLabel.setForeground(new Color(46, 204, 113));
        
        // Open inventory management system
        SwingUtilities.invokeLater(() -> {
            new CellPhoneInventory(username);
            dispose();
        });
    }
    
    private void openCreateAccount() {
        new CreateAccount();
    }
    
    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(Color.RED);
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, CreateAccount.UserAccount> loadUsers() {
        File file = new File(USERS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (Map<String, CreateAccount.UserAccount>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading users: " + e.getMessage());
            }
        }
        return new java.util.HashMap<>();
    }
    
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new CreateLoginForm());
    }
}
