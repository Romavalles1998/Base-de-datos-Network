import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Network {
    private String userName;
    private Scanner scanner;
    private Map<Integer, String> mainMenuOptions;
    private Map<Integer, String> userMenuOptions;

    // Constructor
    public Network() {
        this.scanner = new Scanner(System.in);
        this.mainMenuOptions = new HashMap<>();
        this.userMenuOptions = new HashMap<>();
        // Inicializar opciones del menú
        initializeOptions();
    }

    // Inicializar opciones de menú
    private void initializeOptions() {
        // Opciones del menú principal
        mainMenuOptions.put(0, "Exit");
        mainMenuOptions.put(1, "Login");

        // Opciones del menú del usuario
        userMenuOptions.put(0, "Exit");
        userMenuOptions.put(1, "My Posts");
        userMenuOptions.put(2, "New Post");
        userMenuOptions.put(3, "New Comment");
        userMenuOptions.put(4, "Logout " + userName);
    }

    // Método para imprimir el menú principal
    public void printMainMenu() {
        mainMenuOptions.forEach((k, v) -> System.out.println(k + " - " + v));
        System.out.print("Elige una opción: ");
    }

    // Método para manejar el inicio de sesión
    public boolean login() {
        System.out.print("Ingresa tu nombre: ");
        this.userName = scanner.next();
        System.out.println("Bienvenido " + userName);
        initializeOptions(); // Actualizar opciones del menú del usuario con el nombre
        return true;
    }

    // Método para imprimir el menú del usuario
    public void printUserMenu() {
        userMenuOptions.forEach((k, v) -> System.out.println(k + " - " + v.replace(userName, this.userName)));
        System.out.print("Elige una opción: ");
    }

    // Método para ejecutar la lógica de la aplicación
    public void run() {
        int option;
        boolean isUserLoggedIn = false;
        do {
            printMainMenu();
            option = scanner.nextInt();
            switch (option) {
                case 0: // Exit
                    System.out.println("Saliendo de la aplicación...");
                    break;
                case 1: // Login
                    isUserLoggedIn = login();
                    while (isUserLoggedIn) {
                        printUserMenu();
                        int userOption = scanner.nextInt();
                        switch (userOption) {
                            case 0: // Exit
                                System.out.println("Saliendo de la aplicación...");
                                isUserLoggedIn = false;
                                break;
                            case 1: // My Posts
                                myPosts();
                                break;
                            case 2: // New Post
                                createPost();
                                break;
                            case 3: // New Comment
                                createComment();
                                break;
                            case 4: // Logout
                                System.out.println("Cerrando sesión...");
                                isUserLoggedIn = false;
                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
                    break;
            }
        } while (option != 0);
        scanner.close();
    }

    // Métodos CRUD placeholder
    private void myPosts() {
        System.out.println("Aquí se mostrarían los posts del usuario: " + userName);
        // Implementar lógica para recuperar y mostrar posts del usuario
    }

    private void createPost() {
        System.out.println("Creando un nuevo post. Por favor, escribe el contenido de tu post:");
        scanner.nextLine(); // Limpiar buffer
        String postContent = scanner.nextLine();
        System.out.println("Tu post: " + postContent);
        // Implementar lógica para crear un nuevo post en la base de datos
    }

    private void createComment() {
        System.out.println("Escribe tu comentario:");
        scanner.nextLine(); // Limpiar buffer
        String commentContent = scanner.nextLine();
        System.out.println("Tu comentario: " + commentContent);
        // Implementar lógica para crear un nuevo comentario en la base de datos
    }
}
