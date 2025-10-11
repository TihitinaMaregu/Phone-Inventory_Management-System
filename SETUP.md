# Setup Guide for Phone Inventory Management System

## Quick Start

### Step 1: Install Java Development Kit (JDK)

#### macOS Installation Options:

**Option A: Using Homebrew (Recommended)**
```bash
# Install Homebrew if not already installed
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install OpenJDK
brew install openjdk@17

# Add to PATH (add this to your ~/.zshrc or ~/.bash_profile)
echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

**Option B: Download from Oracle**
1. Visit https://www.oracle.com/java/technologies/downloads/
2. Download JDK for macOS
3. Run the installer
4. Follow the installation wizard

**Option C: Using SDKMAN**
```bash
# Install SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install Java
sdk install java 17.0.9-tem
```

### Step 2: Verify Java Installation

```bash
# Check Java version
java -version

# Check Java compiler
javac -version
```

Expected output should show Java version 8 or higher.

### Step 3: Compile the Application

```bash
# Navigate to project directory
cd Phone-Inventory_Management-System

# Compile all Java files
javac *.java
```

If successful, you'll see `.class` files created for each Java file.

### Step 4: Run the Application

```bash
# Start the login form
java CreateLoginForm
```

## Detailed Setup Instructions

### For First-Time Users

1. **Compile the code** (one-time setup):
   ```bash
   javac CellPhone.java
   javac CreateAccount.java
   javac CellPhoneInventory.java
   javac CreateLoginForm.java
   ```

2. **Run the application**:
   ```bash
   java CreateLoginForm
   ```

3. **Create your first account**:
   - Click "Create New Account"
   - Fill in all fields
   - Click "Create Account"

4. **Login and start managing inventory**:
   - Enter your credentials
   - Click "Login"
   - Start adding phones!

## Running Without Compilation (Alternative)

If you have issues with compilation, you can use:

```bash
# Run directly with source files (Java 11+)
java CreateLoginForm.java
```

## Troubleshooting

### Issue: "Unable to locate a Java Runtime"

**Solution**: Install JDK using one of the methods in Step 1.

### Issue: "javac: command not found"

**Solution**: 
1. Ensure JDK (not just JRE) is installed
2. Add Java to your PATH:
   ```bash
   export JAVA_HOME=$(/usr/libexec/java_home)
   export PATH=$JAVA_HOME/bin:$PATH
   ```

### Issue: Compilation errors

**Solution**:
1. Ensure all 4 Java files are in the same directory
2. Check Java version is 8 or higher
3. Try compiling files individually in this order:
   ```bash
   javac CellPhone.java
   javac CreateAccount.java
   javac CellPhoneInventory.java
   javac CreateLoginForm.java
   ```

### Issue: "Class not found" when running

**Solution**:
1. Ensure you're in the correct directory
2. Make sure compilation was successful (`.class` files exist)
3. Run from the directory containing the `.class` files

## Project Structure

```
Phone-Inventory_Management-System/
├── CellPhone.java              # Phone model class
├── CellPhoneInventory.java     # Main inventory GUI
├── CreateAccount.java          # Account creation GUI
├── CreateLoginForm.java        # Login GUI (START HERE)
├── README.md                   # Main documentation
├── SETUP.md                    # This file
├── IMAGE_NOTE.txt             # Image instructions
├── .gitignore                 # Git ignore rules
├── users.dat                  # Created at runtime (user data)
└── inventory.dat              # Created at runtime (inventory data)
```

## Running Different Components

### Start with Login (Recommended)
```bash
java CreateLoginForm
```

### Test Account Creation Only
```bash
java CreateAccount
```

### Test Inventory Management Only
```bash
java CellPhoneInventory
```

## Development Tips

### Recompiling After Changes

If you modify any Java file:
```bash
# Recompile all files
javac *.java

# Or recompile specific file
javac YourModifiedFile.java
```

### Cleaning Compiled Files

```bash
# Remove all compiled class files
rm *.class

# Remove data files (reset application)
rm *.dat
```

### Creating a JAR File (Optional)

To create a distributable JAR:

```bash
# Compile all files
javac *.java

# Create manifest file
echo "Main-Class: CreateLoginForm" > manifest.txt

# Create JAR
jar cvfm PhoneInventory.jar manifest.txt *.class

# Run the JAR
java -jar PhoneInventory.jar
```

## System Requirements

- **Java**: JDK 8 or higher
- **OS**: macOS, Windows, or Linux
- **RAM**: 256 MB minimum
- **Disk**: 50 MB free space

## Next Steps

After successful setup:

1. ✅ Read the [README.md](README.md) for feature details
2. ✅ Create your first user account
3. ✅ Add some phones to inventory
4. ✅ Test search and update features
5. ✅ Explore the menu options

## Support

If you encounter issues:
1. Check Java installation: `java -version`
2. Verify you're in the correct directory
3. Ensure all Java files are present
4. Check file permissions

## Quick Reference Commands

```bash
# Check Java
java -version
javac -version

# Compile
javac *.java

# Run
java CreateLoginForm

# Clean
rm *.class *.dat

# Reset everything
rm *.class *.dat
javac *.java
java CreateLoginForm
```

---

**Ready to start?** Run: `java CreateLoginForm`
