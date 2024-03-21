import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private Connection conexion;

    public PostDAO() {
        this.conexion = ConexionDB.getConexion();
    }

    // Create
    public boolean crearPost(Post post) {
        String sql = "INSERT INTO posts (usuario_id, titulo, contenido) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, post.getUsuarioId());
            statement.setString(2, post.getTitulo());
            statement.setString(3, post.getContenido());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read (Single post)
    public Post obtenerPostPorId(int id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Post(
                    resultSet.getInt("id"),
                    resultSet.getInt("usuario_id"),
                    resultSet.getString("titulo"),
                    resultSet.getString("contenido")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read (All posts)
    public List<Post> obtenerTodosLosPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(
                    resultSet.getInt("id"),
                    resultSet.getInt("usuario_id"),
                    resultSet.getString("titulo"),
                    resultSet.getString("contenido")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    // Update
    public boolean actualizarPost(Post post) {
        String sql = "UPDATE posts SET usuario_id = ?, titulo = ?, contenido = ? WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, post.getUsuarioId());
            statement.setString(2, post.getTitulo());
            statement.setString(3, post.getContenido());
            statement.setInt(4, post.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean eliminarPost(int id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

