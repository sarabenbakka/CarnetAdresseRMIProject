import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICarnetAdresse extends Remote {

    void ajouterContact(String nom, String telephone) throws RemoteException ;
    String rechercherContact(String nom) throws RemoteException;
    List<String> afficherTousLesContacts() throws RemoteException;
}
