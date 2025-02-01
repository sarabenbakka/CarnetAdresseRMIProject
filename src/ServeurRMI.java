import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServeurRMI {

    public static void main(String[] args) {
      try{
          CarnetAdresseImpl carnet = new CarnetAdresseImpl();
          Registry registry = LocateRegistry.createRegistry(1099);

          registry.rebind("CarnetAdresse", carnet);

          System.out.println("Serveur RMI démarré");
      }
      catch (Exception e) {
          System.err.println("Erreur serveur: " + e.getMessage());
          e.printStackTrace();
      }

    }
}

