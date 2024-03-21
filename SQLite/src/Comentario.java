import java.sql.Timestamp;

public class Comentario {
    private int id;
    private int postId;
    private String autor;
    private String contenido;
    private Timestamp fecha;

    // Constructor con todos los parámetros
    public Comentario(int id, int postId, String autor, String contenido, Timestamp fecha) {
        this.id = id;
        this.postId = postId;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    // Constructor sin el parámetro id y fecha, útil para la creación de comentarios
    public Comentario(int postId, String autor, String contenido) {
        this.postId = postId;
        this.autor = autor;
        this.contenido = contenido;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public String getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}

