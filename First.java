import java.sql.*;
import java.util.Scanner;

public class First {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ExpenseTracker";
    private static final String DB_USER = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "Anand5515@"; // Replace with your MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database!");

            while (true) {
                System.out.println("\nExpense Tracker Menu:");
                System.out.println("1. Add Expense");
                System.out.println("2. View Expenses");
                System.out.println("3. Delete Expense");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addExpense(connection, scanner);
                        break;
                    case 2:
                        viewExpenses(connection);
                        break;
                    case 3:
                        deleteExpense(connection, scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void addExpense(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            String query = "INSERT INTO expenses (amount, category, date) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, amount);
            statement.setString(2, category);
            statement.setDate(3, Date.valueOf(date));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Expense added successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error adding expense: " + e.getMessage());
        }
    }

    private static void viewExpenses(Connection connection) {
        try {
            String query = "SELECT * FROM expenses";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("\nID | Amount | Category | Date");
            System.out.println("-----------------------------");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double amount = resultSet.getDouble("amount");
                String category = resultSet.getString("category");
                Date date = resultSet.getDate("date");

                System.out.printf("%d | %.2f | %s | %s%n", id, amount, category, date);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving expenses: " + e.getMessage());
        }
    }

    private static void deleteExpense(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter the ID of the expense to delete: ");
            int idToDelete = scanner.nextInt();

            String query = "DELETE FROM expenses WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idToDelete);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Expense deleted successfully!");
            } else {
                System.out.println("Expense with the given ID does not exist.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting expense: " + e.getMessage());
        }
    }
}