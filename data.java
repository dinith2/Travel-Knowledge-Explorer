import java.sql.*;

public class DeliveryService {
    private static final String JDBC_URL = "jdbc:sqlite:delivery_service.db";
    
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL);
            
            createTables(conn);
            
            insertCustomer(conn, "John Doe");
            insertItem(conn, "Electronics");
            insertDelivery(conn, 1, 1, "123 Main St", 1);
            
            queryDeliveries(conn);
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void createTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Customer (" +
                    "customer_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT)");
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Item (" +
                    "item_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "item_name TEXT)");
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Delivery (" +
                    "delivery_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "customer_id INTEGER," +
                    "item_id INTEGER," +
                    "address TEXT," +
                    "warehouse_id INTEGER," +
                    "FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)," +
                    "FOREIGN KEY (item_id) REFERENCES Item(item_id))");
        }
    }
    
    private static void insertCustomer(Connection conn, String name) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Customer (name) VALUES (?)")) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }
    
    private static void insertItem(Connection conn, String itemName) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Item (item_name) VALUES (?)")) {
            stmt.setString(1, itemName);
            stmt.executeUpdate();
        }
    }
    
    private static void insertDelivery(Connection conn, int customerId, int itemId, String address, int warehouseId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Delivery (customer_id, item_id, address, warehouse_id) VALUES (?, ?, ?, ?)")) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, itemId);
            stmt.setString(3, address);
            stmt.setInt(4, warehouseId);
            stmt.executeUpdate();
        }
    }
    
    private static void queryDeliveries(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Delivery")) {
            while (rs.next()) {
                int deliveryId = rs.getInt("delivery_id");
                int customerId = rs.getInt("customer_id");
                int itemId = rs.getInt("item_id");
                String address = rs.getString("address");
                int warehouseId = rs.getInt("warehouse_id");
                
                System.out.printf("Delivery ID: %d, Customer ID: %d, Item ID: %d, Address: %s, Warehouse ID: %d%n",
                        deliveryId, customerId, itemId, address, warehouseId);
            }
        }
    }
}
