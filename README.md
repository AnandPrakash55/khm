# khm
# 🧾 Expense Tracker (JDBC + MySQL)

This is a simple command-line **Java Expense Tracker** application that uses **JDBC** to connect to a **MySQL database**. It allows users to add, view, and delete expense records via an interactive menu.

---

## 📁 Project Structure

ExpenseTrackerJDBC/
├── lib/
│ └── mysql-connector-java-8.0.xx.jar # MySQL JDBC driver
├── src/
│ └── First.java # Main Java file
└── .vscode/
└── launch.json # (Optional) VS Code run configuration

yaml
Copy
Edit

---

## ✅ Features

- ➕ Add Expense  
- 📋 View All Expenses  
- ❌ Delete Expense by ID  
- 💾 Data stored persistently using MySQL

---

## 🔧 Requirements

- Java JDK 11 or higher
- MySQL Server
- MySQL Connector/J (JDBC driver)
- Visual Studio Code (optional, but recommended)
- Java Extension Pack (if using VS Code)

---

## 🗃️ MySQL Database Setup

Login to your MySQL terminal or use MySQL Workbench and run:

```sql
CREATE DATABASE IF NOT EXISTS ExpenseTracker;

USE ExpenseTracker;

CREATE TABLE IF NOT EXISTS expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE,
    category VARCHAR(100),
    date DATE
);
🧪 Running the Application
Compile the Program
Open terminal inside the project root and run:

bash
Copy
Edit
javac -cp "lib/mysql-connector-java-8.0.xx.jar" -d . src/First.java
Run the Program
bash
Copy
Edit
java -cp ".;lib/mysql-connector-java-8.0.xx.jar" First
📝 On Linux/Mac, replace ; with : in classpath.

📷 Sample Run
mathematica
Copy
Edit
Expense Tracker Menu:
1. Add Expense
2. View Expenses
3. Delete Expense
4. Exit
Enter your choice: 1
Enter amount: 250
Enter category: Travel
Enter date (YYYY-MM-DD): 2025-07-17
Expense added successfully!
