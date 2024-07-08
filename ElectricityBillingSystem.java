package SYSTEM;

import java.sql.*;

public class ElectricityBillingSystem {

    // JDBC URL, username and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/customer";
    private static final String JDBC_USER = "Aditi";
    private static final String JDBC_PASSWORD = "Aditi";

    public static void main(String[] args) {
        try {
            // Register MySQL JDBC Driver
            Class.forName("com.mysql.cj.customer.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
            // Insert example data (you can modify this as per your needs)
            insertCustomer(conn, 1, "John Doe", 150); // Inserting customer with ID 1, name "John Doe", and units consumed 150
            insertCustomer(conn, 2, "Jane Smith", 200); // Inserting customer with ID 2, name "Jane Smith", and units consumed 200

            // Fetch all customers and print their details
            getAllCustomers(conn);

            // Close connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a customer record into the database
    private static void insertCustomer(Connection conn, int id, String name, int unitsConsumed) throws SQLException {
        String sql = "INSERT INTO customers (customer_id, customer_name, units_consumed, bill_amount) VALUES (?, ?, ?, ?)";

        // Calculate bill amount (example calculation)
        double billAmount = unitsConsumed * 5.75; // Replace with your own calculation logic

        // Create a PreparedStatement object
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setInt(3, unitsConsumed);
        statement.setDouble(4, billAmount);

        // Execute the query
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new customer was inserted successfully!");
        }

        // Close the PreparedStatement
        statement.close();
    }

    // Method to fetch all customers from the database and print their details
    private static void getAllCustomers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM customers";

        // Create a Statement object
        Statement statement = conn.createStatement();

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery(sql);

        // Iterate over the result set and print each customer's details
        while (resultSet.next()) {
            int id = resultSet.getInt("customer_id");
            String name = resultSet.getString("customer_name");
            int unitsConsumed = resultSet.getInt("units_consumed");
            double billAmount = resultSet.getDouble("bill_amount");

            System.out.println("Customer ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Units Consumed: " + unitsConsumed);
            System.out.println("Bill Amount: $" + billAmount);
            System.out.println("----------------------");
        }

        // Close the result set, statement, and connection
        resultSet.close();
        statement.close();
    }
}


	


