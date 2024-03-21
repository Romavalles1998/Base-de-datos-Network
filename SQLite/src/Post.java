
public class Post {
    private int id;
    private int usuarioId;
    private String titulo;
    private String contenido;

    // Constructor con todos los parámetros
    public Post(int id, int usuarioId, String titulo, String contenido) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    // Constructor sin el parámetro id, útil para la creación de posts
    public Post(int usuarioId, String titulo, String contenido) {
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}

