import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/network"; // Reemplaza con tu URL de la BD
    private static final String USER = "tu_usuario"; // Reemplaza con tu usuario de la BD
    private static final String PASSWORD = "tu_contraseña"; // Reemplaza con tu contraseña de la BD

    static {
        try {
            // Registrar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo registrar el driver de MySQL", e);
        }
    }

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo establecer la conexión a la base de datos", e);
        }
    }
}

