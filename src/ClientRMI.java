import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ClientRMI {
    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            ICarnetAdresse carnet = (ICarnetAdresse) registry.lookup("CarnetAdresse");

        } catch (Exception e) {
            System.err.println("Erreur client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}