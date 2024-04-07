package network;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            UsuarioController userController = new UsuarioController();
            PostController postController = new PostController();
            Scanner scanner = new Scanner(System.in);
            int userId = -1;

            while (true) {
                if (userId < 0) {
                    System.out.println("1. Login\n2. Registro\n3. Salir\n\n");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (choice) {
                        case 1:
                            userController.login();
                            if (userController.getCurrentUser() != null) {
                                userId = userController.getCurrentUser().getId();
                            }
                            break;
                        case 2:
                            userController.register();
                            break;
                        case 3:
                            return; 
                    }
                } else {
                    System.out.println("\n\n1. Crear Post\n2. Ver TODOS los Post\n3. Dar like a un Post\n4. Nuevo Comentario\n5. Ver otros Post\n6. Mis Post\n7. Logout\n\n");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (choice) {
                        case 1:
                            postController.createPost(userId);
                            break;
                        case 2:
                            postController.viewAllPosts();
                            break;
                        case 3:
                            System.out.print("Escribe el ID del post que quieres dar like: ");
                            int postIdToLike = scanner.nextInt();
                            scanner.nextLine(); 
                            postController.likePost(postIdToLike);
                            break;
                        case 4:
                            System.out.print("Escribe el ID del post que quieres comentar:: ");
                            int postIdToComment = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Enter your comment: ");
                            String commentText = scanner.nextLine();
                            postController.commentPost(postIdToComment, userId, commentText);
                            break;
                        case 5:
                            postController.viewOthersPosts(userId);
                            break;
                        case 6:
                        	postController.viewMyPosts(userId);
                        	break;
                        case 7:
                            userController.logout();
                            userId = -1; // Resetea el userID para mostrar el menu de login
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
