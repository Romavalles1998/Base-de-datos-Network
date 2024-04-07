package network;

import java.sql.*;
import java.util.Scanner;

public class PostController {
    private Connection connection;

    public PostController() throws SQLException {
        this.connection = DatabaseManager.getConnection();
    }

    public void createPost(int userId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your post text: ");
        String text = scanner.nextLine();

        String sql = "INSERT INTO posts (text, userId, likes) VALUES (?, ?, 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, text);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            System.out.println("Post creado.");
        }
    }

    public void viewAllPosts() throws SQLException {
        String sql = "SELECT * FROM posts";
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ": " + resultSet.getString("text") +
                        " (Likes: " + resultSet.getInt("likes") + ")");
            }
        }
    }
 
    // Método para añadir un 'like' a una publicación
    public void likePost(int postId) throws SQLException {
        String sql = "UPDATE posts SET likes = likes + 1 WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, postId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Post liked.");
            } else {
                System.out.println("Post not found.");
            }
        }
    }

    // Método para añadir un nuevo comentario a una publicación
    public void commentPost(int postId, int userId, String comment) throws SQLException {
        String sql = "INSERT INTO comments (postId, userId, text) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, postId);
            stmt.setInt(2, userId);
            stmt.setString(3, comment);
            stmt.executeUpdate();
            System.out.println("Comentario añadido.");
        }
    }

    // Método para ver las publicaciones de otros usuarios
    public void viewOthersPosts(int currentUserId) throws SQLException {
        String sql = "SELECT * FROM posts WHERE userId != ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, currentUserId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + ": " + resultSet.getString("text") +
                            " (Likes: " + resultSet.getInt("likes") + ")");
                }
            }
        }
    }
 // Método para añadir un nuevo comentario a una publicación
    public void addComment(int userId, int postId, String commentText) throws SQLException {
        String sql = "INSERT INTO comments (userId, postId, text) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, postId);
            stmt.setString(3, commentText);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Comentario añadido.");
            } else {
                System.out.println("Error al añadir un comentario.");
            }
        }
    }
    // Método para ver los post del Usuario
    public void viewMyPosts(int userId) throws SQLException {
        String sql = "SELECT * FROM posts WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet resultSet = stmt.executeQuery();
            boolean hasPosts = false;
            while (resultSet.next()) {
                hasPosts = true;
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                int likes = resultSet.getInt("likes");

                System.out.println("Post ID: " + id + ", Text: " + text + ", Likes: " + likes);
            }

            if (!hasPosts) {
                System.out.println("No tienes ningun post creado.");
            }
        }
    }

}
