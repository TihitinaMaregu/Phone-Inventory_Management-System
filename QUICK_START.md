# Quick Start Guide

## 🚀 Get Started in 3 Steps

### Step 1: Install Java
```bash
# macOS (using Homebrew)
brew install openjdk@17

# Verify installation
java -version
```

### Step 2: Compile & Run
```bash
# Navigate to project directory
cd Phone-Inventory_Management-System

# Compile
javac *.java

# Run
java CreateLoginForm
```

### Step 3: Create Account & Login
1. Click "Create New Account"
2. Fill in your details
3. Login with your credentials
4. Start managing inventory!

---

## 📱 Adding Your First Phone

1. **Fill in the form**:
   - Phone ID: `IP001`
   - Brand: `Apple`
   - Model: `iPhone 15 Pro`
   - Price: `999.99`
   - Quantity: `10`
   - Color: `Titanium Blue`
   - Storage: `256`

2. **Click "Add Phone"** (green button)

3. **See it in the table!**

---

## 🔧 Common Operations

### Update a Phone
1. Click on phone in table
2. Modify fields
3. Click "Update Phone" (blue)

### Delete a Phone
1. Click on phone in table
2. Click "Delete Phone" (red)
3. Confirm deletion

### Search
1. Click "Search" (yellow)
2. Enter search term
3. View filtered results

### Clear Form
- Click "Clear Fields" (gray)

---

## 📁 Project Files

```
✅ CellPhone.java           - Phone data model
✅ CellPhoneInventory.java  - Main inventory app
✅ CreateAccount.java       - Account creation
✅ CreateLoginForm.java     - Login screen (START HERE)
📖 README.md               - Full documentation
📖 SETUP.md                - Detailed setup guide
📖 PROJECT_OVERVIEW.md     - Technical details
🚀 run.sh / run.bat        - Run scripts
```

---

## 💡 Pro Tips

- **Enter Key**: Press Enter in login/account forms to submit
- **Auto-Save**: Changes are saved automatically
- **Table Click**: Click any row to load data into form
- **Search Reset**: Search again with empty term to show all
- **Menu Bar**: Use File → Save to manually save

---

## ❓ Need Help?

- **Can't compile?** → See [SETUP.md](SETUP.md)
- **How does it work?** → See [PROJECT_OVERVIEW.md](PROJECT_OVERVIEW.md)
- **Full features?** → See [README.md](README.md)

---

## 🎯 Quick Commands

```bash
# Compile all files
javac *.java

# Run application
java CreateLoginForm

# Clean compiled files
rm *.class

# Reset all data
rm *.dat

# Use run script (macOS/Linux)
./run.sh

# Use run script (Windows)
run.bat
```

---

**Ready?** Run: `java CreateLoginForm` 🚀
