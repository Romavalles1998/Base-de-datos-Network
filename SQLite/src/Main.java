import java.util.Scanner;
        import java.util.Map;
import java.sql.Connection;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
    	 
    	try (Connection conn = ConexionDB.getConexion()) {
             // Aquí puedes interactuar con la base de datos
             System.out.println("Conectado a la base de datos!");
         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("No se pudo conectar a la base de datos.");
         }
        System.out.println("            _                      _    ");
        System.out.println(" _ __   ___| |___      _____  _ __| | __");
        System.out.println("| '_ \\ / _ \\ __\\ \\ /\\ / / _ \\| '__| |/ /");
        System.out.println("| | | |  __/ |_ \\ V  V / (_) | |  |   < ");
        System.out.println("|_| |_|\\___|\\__| \\_/\\_/ \\___/|_|  |_|\\_\\");
        System.out.println();
        Network networkApp = new Network();
        networkApp.run();
    }
}
