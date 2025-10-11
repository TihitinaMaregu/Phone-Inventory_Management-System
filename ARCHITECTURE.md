# System Architecture & Diagrams

## 🏗️ System Architecture

### High-Level Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │CreateLogin   │  │CreateAccount │  │CellPhoneInventory│  │
│  │Form          │  │              │  │                  │  │
│  │(Entry Point) │  │(Registration)│  │(Main App)        │  │
│  └──────┬───────┘  └──────┬───────┘  └────────┬─────────┘  │
└─────────┼──────────────────┼───────────────────┼────────────┘
          │                  │                   │
          │                  │                   │
┌─────────┼──────────────────┼───────────────────┼────────────┐
│         │      BUSINESS LOGIC LAYER            │            │
│         │                  │                   │            │
│         ▼                  ▼                   ▼            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │Authentication│  │User          │  │Inventory         │  │
│  │Logic         │  │Validation    │  │Management        │  │
│  │              │  │              │  │(CRUD Operations) │  │
│  └──────┬───────┘  └──────┬───────┘  └────────┬─────────┘  │
└─────────┼──────────────────┼───────────────────┼────────────┘
          │                  │                   │
          │                  │                   │
┌─────────┼──────────────────┼───────────────────┼────────────┐
│         │       DATA MODEL LAYER               │            │
│         │                  │                   │            │
│         ▼                  ▼                   ▼            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │UserAccount   │  │UserAccount   │  │CellPhone         │  │
│  │(Inner Class) │  │HashMap       │  │List              │  │
│  └──────┬───────┘  └──────┬───────┘  └────────┬─────────┘  │
└─────────┼──────────────────┼───────────────────┼────────────┘
          │                  │                   │
          │                  │                   │
┌─────────┼──────────────────┼───────────────────┼────────────┐
│         │      PERSISTENCE LAYER               │            │
│         ▼                  ▼                   ▼            │
│  ┌──────────────────────────────┐  ┌──────────────────┐    │
│  │      users.dat               │  │  inventory.dat   │    │
│  │  (Serialized HashMap)        │  │ (Serialized List)│    │
│  └──────────────────────────────┘  └──────────────────┘    │
└─────────────────────────────────────────────────────────────┘
```

---

## 🔄 Application Flow Diagram

### Complete User Journey

```
START
  │
  ▼
┌─────────────────────┐
│ CreateLoginForm     │
│ (Main Entry Point)  │
└──────┬──────────────┘
       │
       ├─── New User? ───┐
       │                 │
       │                 ▼
       │         ┌─────────────────┐
       │         │ CreateAccount   │
       │         │ - Enter Details │
       │         │ - Validate      │
       │         │ - Save to DB    │
       │         └────────┬────────┘
       │                  │
       │                  │ Account Created
       │                  │
       │                  ▼
       │         ┌─────────────────┐
       │         │ Return to Login │
       │         └────────┬────────┘
       │                  │
       │◄─────────────────┘
       │
       │ Existing User
       │
       ▼
┌─────────────────────┐
│ Enter Credentials   │
│ - Username          │
│ - Password          │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│ Authenticate        │
│ - Load users.dat    │
│ - Verify Password   │
└──────┬──────────────┘
       │
       ├─── Invalid ───┐
       │               │
       │               ▼
       │        ┌─────────────┐
       │        │ Show Error  │
       │        └──────┬──────┘
       │               │
       │◄──────────────┘
       │
       │ Valid
       │
       ▼
┌─────────────────────────┐
│ CellPhoneInventory      │
│ (Main Application)      │
└──────┬──────────────────┘
       │
       ▼
┌─────────────────────────┐
│ Load inventory.dat      │
│ Display in Table        │
└──────┬──────────────────┘
       │
       ▼
┌─────────────────────────┐
│ User Operations:        │
│ ┌─────────────────────┐ │
│ │ 1. Add Phone        │ │
│ │ 2. Update Phone     │ │
│ │ 3. Delete Phone     │ │
│ │ 4. Search Phone     │ │
│ │ 5. View All         │ │
│ └─────────────────────┘ │
└──────┬──────────────────┘
       │
       │ Each Operation
       │
       ▼
┌─────────────────────────┐
│ Auto-Save to            │
│ inventory.dat           │
└──────┬──────────────────┘
       │
       ▼
┌─────────────────────────┐
│ Refresh Table Display   │
└──────┬──────────────────┘
       │
       │ Continue or Exit
       │
       ▼
     END
```

---

## 📊 Class Relationship Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                      CreateLoginForm                         │
│  - usernameField: JTextField                                │
│  - passwordField: JPasswordField                            │
│  + main(String[]): void                                     │
│  + login(): void                                            │
│  + openCreateAccount(): void                                │
└────────┬──────────────────────────────┬─────────────────────┘
         │                              │
         │ creates                      │ launches
         │                              │
         ▼                              ▼
┌─────────────────────┐      ┌─────────────────────────────┐
│  CreateAccount      │      │  CellPhoneInventory         │
│  - usernameField    │      │  - inventory: List<Phone>   │
│  - passwordField    │      │  - tableModel: TableModel   │
│  - emailField       │      │  + addPhone(): void         │
│  + createAccount()  │      │  + updatePhone(): void      │
│  + saveUsers()      │      │  + deletePhone(): void      │
└──────┬──────────────┘      │  + searchPhone(): void      │
       │                     └────────┬────────────────────┘
       │ contains                     │
       │                              │ manages
       ▼                              │
┌─────────────────────┐               │
│  UserAccount        │               │
│  (Inner Class)      │               │
│  - username: String │               │
│  - password: String │               │
│  - email: String    │               │
│  - fullName: String │               │
└─────────────────────┘               │
                                      │
                                      ▼
                            ┌─────────────────────┐
                            │  CellPhone          │
                            │  - id: String       │
                            │  - brand: String    │
                            │  - model: String    │
                            │  - price: double    │
                            │  - quantity: int    │
                            │  - color: String    │
                            │  - storage: int     │
                            │  + getters/setters  │
                            └─────────────────────┘
```

---

## 💾 Data Storage Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    APPLICATION MEMORY                        │
│                                                              │
│  ┌──────────────────────┐    ┌──────────────────────────┐  │
│  │ HashMap<String,      │    │ List<CellPhone>          │  │
│  │   UserAccount>       │    │                          │  │
│  │                      │    │ ┌──────────────────────┐ │  │
│  │ ┌──────────────────┐ │    │ │ CellPhone 1          │ │  │
│  │ │ "user1" → User1  │ │    │ ├──────────────────────┤ │  │
│  │ ├──────────────────┤ │    │ │ CellPhone 2          │ │  │
│  │ │ "user2" → User2  │ │    │ ├──────────────────────┤ │  │
│  │ ├──────────────────┤ │    │ │ CellPhone 3          │ │  │
│  │ │ "user3" → User3  │ │    │ └──────────────────────┘ │  │
│  │ └──────────────────┘ │    └──────────────────────────┘  │
│  └──────────┬───────────┘    └──────────┬───────────────┘  │
└─────────────┼──────────────────────────┼───────────────────┘
              │                          │
              │ Serialization            │ Serialization
              │ (ObjectOutputStream)     │ (ObjectOutputStream)
              │                          │
              ▼                          ▼
┌─────────────────────────────────────────────────────────────┐
│                      FILE SYSTEM                             │
│                                                              │
│  ┌──────────────────────┐    ┌──────────────────────────┐  │
│  │   users.dat          │    │   inventory.dat          │  │
│  │                      │    │                          │  │
│  │ [Binary Data]        │    │ [Binary Data]            │  │
│  │ Serialized HashMap   │    │ Serialized List          │  │
│  └──────────────────────┘    └──────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
              ▲                          ▲
              │ Deserialization          │ Deserialization
              │ (ObjectInputStream)      │ (ObjectInputStream)
              │                          │
              └──────────────────────────┘
                   On Application Start
```

---

## 🔐 Authentication Flow

```
┌─────────────────┐
│ User Enters     │
│ Credentials     │
└────────┬────────┘
         │
         ▼
┌─────────────────────────────┐
│ Load users.dat              │
│ Deserialize to HashMap      │
└────────┬────────────────────┘
         │
         ▼
┌─────────────────────────────┐
│ Username exists?            │
└────┬────────────────┬───────┘
     │ NO             │ YES
     │                │
     ▼                ▼
┌─────────────┐  ┌──────────────────┐
│ Show Error  │  │ Get UserAccount  │
│ "Not Found" │  │ from HashMap     │
└─────────────┘  └────────┬─────────┘
                          │
                          ▼
                 ┌──────────────────┐
                 │ Password Match?  │
                 └────┬────────┬────┘
                      │ NO     │ YES
                      │        │
                      ▼        ▼
                 ┌─────────┐  ┌──────────────┐
                 │ Error   │  │ Success!     │
                 │ Invalid │  │ Launch App   │
                 └─────────┘  └──────────────┘
```

---

## 📱 Inventory CRUD Operations

### Add Operation Flow
```
User Fills Form
      ↓
Validate Input
      ↓
Check Duplicate ID ──→ Exists? ──→ Show Error
      ↓ Not Exists
Create CellPhone Object
      ↓
Add to List<CellPhone>
      ↓
Refresh JTable
      ↓
Serialize to inventory.dat
      ↓
Show Success Message
```

### Update Operation Flow
```
User Clicks Table Row
      ↓
Populate Form Fields
      ↓
User Modifies Data
      ↓
Click Update Button
      ↓
Find Phone by ID ──→ Not Found? ──→ Show Error
      ↓ Found
Update Phone Object
      ↓
Refresh JTable
      ↓
Serialize to inventory.dat
      ↓
Show Success Message
```

### Delete Operation Flow
```
User Clicks Table Row
      ↓
Click Delete Button
      ↓
Show Confirmation Dialog
      ↓
User Confirms? ──→ No ──→ Cancel
      ↓ Yes
Remove from List<CellPhone>
      ↓
Refresh JTable
      ↓
Serialize to inventory.dat
      ↓
Show Success Message
```

### Search Operation Flow
```
User Clicks Search
      ↓
Enter Search Term
      ↓
Filter List<CellPhone>
  (by ID, Brand, Model)
      ↓
Clear Table
      ↓
Add Matching Rows
      ↓
Display Results ──→ No Matches? ──→ Show Message
```

---

## 🎨 GUI Component Hierarchy

```
CreateLoginForm (JFrame)
├── mainPanel (JPanel - BorderLayout)
    ├── topPanel (JPanel - BorderLayout)
    │   ├── logoLabel (JLabel with Image/Emoji)
    │   └── titleLabel (JLabel)
    │
    └── centerPanel (JPanel - GridBagLayout)
        ├── usernameLabel (JLabel)
        ├── usernameField (JTextField)
        ├── passwordLabel (JLabel)
        ├── passwordField (JPasswordField)
        ├── showPasswordCheckBox (JCheckBox)
        ├── loginButton (JButton)
        ├── createAccountButton (JButton)
        └── statusLabel (JLabel)

CellPhoneInventory (JFrame)
├── mainPanel (JPanel - BorderLayout)
│   ├── inputPanel (JPanel - GridLayout)
│   │   ├── idField (JTextField)
│   │   ├── brandField (JTextField)
│   │   ├── modelField (JTextField)
│   │   ├── priceField (JTextField)
│   │   ├── quantityField (JTextField)
│   │   ├── colorField (JTextField)
│   │   └── storageField (JTextField)
│   │
│   ├── tablePanel (JPanel - BorderLayout)
│   │   └── inventoryTable (JTable in JScrollPane)
│   │
│   └── buttonPanel (JPanel - FlowLayout)
│       ├── addButton (JButton - Green)
│       ├── updateButton (JButton - Blue)
│       ├── deleteButton (JButton - Red)
│       ├── clearButton (JButton - Gray)
│       └── searchButton (JButton - Yellow)
│
└── menuBar (JMenuBar)
    ├── fileMenu (JMenu)
    │   ├── saveItem (JMenuItem)
    │   └── exitItem (JMenuItem)
    └── helpMenu (JMenu)
        └── aboutItem (JMenuItem)
```

---

## 🔄 Event Flow Diagram

```
User Action
    │
    ▼
┌─────────────────┐
│ GUI Event       │
│ (Button Click,  │
│  Key Press,     │
│  Table Click)   │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Event Listener  │
│ (ActionListener,│
│  KeyListener,   │
│  MouseListener) │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Validation      │
│ (Input Check,   │
│  Business Rules)│
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Business Logic  │
│ (CRUD Operation,│
│  Authentication)│
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Data Update     │
│ (Modify List/   │
│  HashMap)       │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Persistence     │
│ (Save to .dat)  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ GUI Update      │
│ (Refresh Table, │
│  Show Message)  │
└─────────────────┘
```

---

## 📦 Deployment Architecture

```
┌─────────────────────────────────────────────────────────┐
│                  Development Environment                 │
│                                                          │
│  ┌──────────────────────────────────────────────────┐  │
│  │ Source Files (.java)                             │  │
│  │ - CellPhone.java                                 │  │
│  │ - CellPhoneInventory.java                        │  │
│  │ - CreateAccount.java                             │  │
│  │ - CreateLoginForm.java                           │  │
│  └────────────────────┬─────────────────────────────┘  │
│                       │                                 │
│                       │ javac *.java                    │
│                       │                                 │
│                       ▼                                 │
│  ┌──────────────────────────────────────────────────┐  │
│  │ Compiled Files (.class)                          │  │
│  │ - CellPhone.class                                │  │
│  │ - CellPhoneInventory.class                       │  │
│  │ - CreateAccount.class                            │  │
│  │ - CreateAccount$UserAccount.class                │  │
│  │ - CreateLoginForm.class                          │  │
│  └────────────────────┬─────────────────────────────┘  │
└───────────────────────┼──────────────────────────────────┘
                        │
                        │ java CreateLoginForm
                        │
                        ▼
┌─────────────────────────────────────────────────────────┐
│                  Runtime Environment                     │
│                                                          │
│  ┌──────────────────────────────────────────────────┐  │
│  │ JVM (Java Virtual Machine)                       │  │
│  │                                                  │  │
│  │  ┌────────────────────────────────────────────┐ │  │
│  │  │ Application Running                        │ │  │
│  │  │ - GUI Windows                              │ │  │
│  │  │ - Event Handlers                           │ │  │
│  │  │ - Data Structures (List, HashMap)          │ │  │
│  │  └────────────────────────────────────────────┘ │  │
│  └──────────────────────┬───────────────────────────┘  │
│                         │                               │
│                         │ Read/Write                    │
│                         │                               │
│                         ▼                               │
│  ┌──────────────────────────────────────────────────┐  │
│  │ Data Files                                       │  │
│  │ - users.dat (User Accounts)                      │  │
│  │ - inventory.dat (Phone Records)                  │  │
│  └──────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
```

---

## 🧩 Component Integration

```
┌─────────────────────────────────────────────────────────┐
│                    Integration Points                    │
└─────────────────────────────────────────────────────────┘

CreateLoginForm ←→ CreateAccount
    │ Integration: Button click opens CreateAccount window
    │ Data Flow: CreateAccount saves to users.dat
    └→ CreateLoginForm reads same users.dat for authentication

CreateLoginForm ←→ CellPhoneInventory
    │ Integration: Successful login launches CellPhoneInventory
    │ Data Flow: Username passed as constructor parameter
    └→ CellPhoneInventory displays username in title

CellPhoneInventory ←→ CellPhone
    │ Integration: CellPhoneInventory manages List<CellPhone>
    │ Data Flow: Creates, reads, updates, deletes CellPhone objects
    └→ CellPhone provides data structure and serialization

CreateAccount ←→ UserAccount (Inner Class)
    │ Integration: CreateAccount creates UserAccount instances
    │ Data Flow: UserAccount stored in HashMap
    └→ Serialized together to users.dat
```

---

**Architecture Version**: 1.0  
**Last Updated**: October 2025  
**Complexity**: Medium  
**Scalability**: Suitable for small to medium datasets
