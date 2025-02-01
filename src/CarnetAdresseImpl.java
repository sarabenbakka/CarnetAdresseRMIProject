import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarnetAdresseImpl extends UnicastRemoteObject implements ICarnetAdresse {

    private Map<String,String> contacts;

    public  CarnetAdresseImpl() throws  RemoteException {
        contacts = new HashMap<>();
    }

    @Override
    public void ajouterContact(String nom, String telephone) throws RemoteException {
        if (nom == null || nom.trim().isEmpty()) {
            throw new RemoteException("Le nom ne peut pas être vide");
        }
        if (telephone == null || telephone.trim().isEmpty()) {
            throw new RemoteException("Le numéro de téléphone ne peut pas être vide");
        }

        // Stocker le nom tel qu'il a été entré
        contacts.put(nom, telephone);
    }



    @Override
    public String rechercherContact(String nom) throws RemoteException {

        String telephone = contacts.get(nom);

        if (telephone == null) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(nom)) {
                    telephone = entry.getValue();
                    break;
                }
            }
        }

        if (telephone == null) {
            return "Contact inexistant";
        }

        return "Nom: " + nom + ", Téléphone: " + telephone;
    }

    @Override
    public List<String> afficherTousLesContacts() throws RemoteException {
        List<String> listeContacts = new ArrayList<>();

        if (contacts.isEmpty()) {
            listeContacts.add("Aucun contact dans le carnet");
            return listeContacts;
        }

        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            listeContacts.add(entry.getKey() + ": " + entry.getValue());
        }
        return listeContacts;
    }


}
