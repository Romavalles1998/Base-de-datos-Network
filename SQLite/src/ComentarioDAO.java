
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {
    private Connection conexion;

    public ComentarioDAO() {
        // Establecer la conexión con la base de datos
        this.conexion = ConexionDB.getConexion();
    }

    // Método para crear un comentario
    public boolean crearComentario(Comentario comentario) {
        String sql = "INSERT INTO comentarios (post_id, autor, contenido) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, comentario.getPostId());
            statement.setString(2, comentario.getAutor());
            statement.setString(3, comentario.getContenido());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener un comentario por ID
    public Comentario obtenerComentarioPorId(int id) {
        String sql = "SELECT * FROM comentarios WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Comentario(
                    resultSet.getInt("id"),
                    resultSet.getInt("post_id"),
                    resultSet.getString("autor"),
                    resultSet.getString("contenido"),
                    resultSet.getTimestamp("fecha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para obtener todos los comentarios
    public List<Comentario> obtenerTodosLosComentarios() {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM comentarios";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comentarios.add(new Comentario(
                    resultSet.getInt("id"),
                    resultSet.getInt("post_id"),
                    resultSet.getString("autor"),
                    resultSet.getString("contenido"),
                    resultSet.getTimestamp("fecha")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }

    // Método para actualizar un comentario
    public boolean actualizarComentario(Comentario comentario) {
        String sql = "UPDATE comentarios SET post_id = ?, autor = ?, contenido = ? WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, comentario.getPostId());
            statement.setString(2, comentario.getAutor());
            statement.setString(3, comentario.getContenido());
            statement.setInt(4, comentario.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un comentario
    public boolean eliminarComentario(int id) {
        String sql = "DELETE FROM comentarios WHERE id = ?";
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
