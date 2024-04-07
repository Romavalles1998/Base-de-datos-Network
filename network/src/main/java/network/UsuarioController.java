package network;
import java.sql.*;
import java.util.Scanner;

public class UsuarioController {
    private Connection connection;
    private Usuario currentUser;

    public UsuarioController() throws SQLException {
        this.connection = DatabaseManager.getConnection();
    }

    public void login() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escribe tu nombre: ");
        String name = scanner.nextLine();

        String sql = "SELECT * FROM users WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                this.currentUser = new Usuario(resultSet.getInt("id"), resultSet.getString("name"));
                System.out.println("Logueado como: " + currentUser.getName());
            } else {
                System.out.println("User not found.");
            }
        }
    }

    public void register() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escribe tu nombre: ");
        String name = scanner.nextLine();

        System.out.print("Escribe tu apellido: ");
        String lastname = scanner.nextLine();

        String sql = "INSERT INTO users (name, lastname) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, lastname);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Usuario creado.");
            } else {
                System.out.println("Error al crear usuario.");
            }
        }
    }


    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }
}
