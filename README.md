# Phone Inventory Management System

A comprehensive Java-based cell phone inventory management system with user authentication, intuitive GUI, and persistent data storage.

## Features

### 🔐 User Account Management
- **Secure Account Creation**: Users can create accounts with validation
- **Email Validation**: Ensures proper email format
- **Password Security**: Minimum password requirements (6+ characters)
- **Username Validation**: Unique usernames with minimum 4 characters
- **Login Authentication**: Secure user authentication system

### 📱 Inventory Management
- **Add Phones**: Add new cell phones to inventory with complete details
- **Update Records**: Modify existing phone information
- **Delete Phones**: Remove phones from inventory with confirmation
- **Search Functionality**: Search by ID, brand, or model
- **View All**: Display complete inventory in a table format

### 💾 Data Persistence
- **Automatic Saving**: Inventory data is automatically saved
- **User Data Storage**: Secure storage of user credentials
- **File-based Storage**: Uses serialization for efficient data storage

### 🎨 User Interface
- **Modern GUI**: Built with Java Swing
- **Intuitive Design**: Easy-to-use interface with color-coded buttons
- **Table View**: Clear display of inventory data
- **Form Validation**: Real-time input validation
- **Responsive Layout**: Well-organized panels and components

## Files Description

### Core Classes

#### `CellPhone.java`
- **Purpose**: Model class representing a cell phone
- **Attributes**:
  - `id`: Unique identifier
  - `brand`: Phone manufacturer (e.g., Apple, Samsung)
  - `model`: Phone model name
  - `price`: Phone price in dollars
  - `quantity`: Stock quantity
  - `color`: Phone color
  - `storage`: Storage capacity in GB
- **Features**: Implements Serializable for data persistence

#### `CellPhoneInventory.java`
- **Purpose**: Main inventory management system
- **Features**:
  - Add, update, delete phone records
  - Search functionality
  - Table-based display
  - Menu bar with File and Help menus
  - Automatic data persistence
  - Color-coded action buttons

#### `CreateAccount.java`
- **Purpose**: User account creation interface
- **Features**:
  - Full name, email, username, password fields
  - Email format validation
  - Password strength requirements
  - Duplicate username checking
  - Secure password confirmation

#### `CreateLoginForm.java`
- **Purpose**: User authentication and login
- **Features**:
  - Username and password authentication
  - Show/hide password option
  - Create account integration
  - Session management
  - User-friendly error messages

### Resource Files

#### `user-login-305.png`
- **Purpose**: Login form logo/icon
- **Note**: Optional - system uses emoji fallback if not present
- **Recommended Size**: 305x305 pixels

## Installation & Setup

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE)

### Compilation

```bash
# Navigate to the project directory
cd Phone-Inventory_Management-System

# Compile all Java files
javac *.java
```

### Running the Application

#### Option 1: Start with Login Form (Recommended)
```bash
java CreateLoginForm
```

#### Option 2: Start with Account Creation
```bash
java CreateAccount
```

#### Option 3: Direct Inventory Access (Testing)
```bash
java CellPhoneInventory
```

## Usage Guide

### First Time Setup

1. **Run the Login Form**:
   ```bash
   java CreateLoginForm
   ```

2. **Create an Account**:
   - Click "Create New Account" button
   - Fill in all required fields:
     - Full Name
     - Email (must be valid format)
     - Username (minimum 4 characters)
     - Password (minimum 6 characters)
     - Confirm Password
   - Click "Create Account"

3. **Login**:
   - Enter your username and password
   - Click "Login"

### Managing Inventory

#### Adding a Phone
1. Fill in all phone details in the input fields
2. Click the green "Add Phone" button
3. Phone will appear in the inventory table

#### Updating a Phone
1. Click on a phone record in the table to select it
2. Modify the details in the input fields
3. Click the blue "Update Phone" button

#### Deleting a Phone
1. Click on a phone record in the table to select it
2. Click the red "Delete Phone" button
3. Confirm the deletion

#### Searching for Phones
1. Click the yellow "Search" button
2. Enter search term (ID, brand, or model)
3. Matching results will be displayed

#### Clearing Input Fields
- Click the gray "Clear Fields" button to reset all input fields

### Menu Options

#### File Menu
- **Save Inventory**: Manually save inventory data
- **Exit**: Save and close the application

#### Help Menu
- **About**: Display application information

## Data Storage

### Files Created by the System

1. **`users.dat`**
   - Stores user account information
   - Contains usernames, passwords, emails, and full names
   - Created automatically on first account creation

2. **`inventory.dat`**
   - Stores cell phone inventory data
   - Contains all phone records
   - Created automatically when first phone is added

### Data Location
- Data files are created in the same directory as the Java files
- Files use Java serialization format

## Security Features

- Password validation (minimum 6 characters)
- Username uniqueness checking
- Email format validation
- Secure password confirmation
- Data persistence with serialization

## Technical Details

### Technologies Used
- **Language**: Java
- **GUI Framework**: Java Swing
- **Data Storage**: Java Serialization
- **Design Pattern**: MVC-inspired architecture

### Key Components
- `JFrame` for window management
- `JTable` with `DefaultTableModel` for data display
- `GridLayout` and `BorderLayout` for UI organization
- `ObjectInputStream`/`ObjectOutputStream` for data persistence
- Event listeners for user interactions

## Troubleshooting

### Common Issues

#### "No accounts found" Error
- **Solution**: Create an account first using the "Create New Account" button

#### Image Not Loading
- **Solution**: The system will use an emoji fallback. To use a custom image, place `user-login-305.png` in the project directory

#### Data Not Saving
- **Solution**: Ensure you have write permissions in the project directory

#### Compilation Errors
- **Solution**: Ensure you're using JDK 8 or higher
  ```bash
  java -version
  ```

## Future Enhancements

Potential improvements for the system:
- Password encryption
- Export inventory to CSV/Excel
- Advanced search filters
- User roles (Admin, Staff)
- Inventory reports and analytics
- Low stock alerts
- Barcode scanning integration
- Database integration (MySQL/PostgreSQL)

## System Requirements

- **Operating System**: Windows, macOS, or Linux
- **Java Version**: JDK 8 or higher
- **Memory**: Minimum 256 MB RAM
- **Disk Space**: 50 MB free space

## License

This project is open-source and available for educational purposes.

## Support

For issues or questions:
1. Check the troubleshooting section
2. Review the usage guide
3. Ensure all prerequisites are met

## Version History

- **Version 1.0** (Current)
  - Initial release
  - User authentication system
  - Complete inventory management
  - Data persistence
  - Search functionality

---

**Note**: This is a desktop application built with Java Swing. For production use, consider implementing additional security measures such as password hashing and database integration.
