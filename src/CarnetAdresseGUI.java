import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import com.formdev.flatlaf.FlatLightLaf;

public class CarnetAdresseGUI extends JFrame {
    private ICarnetAdresse carnet;
    private JTextField nomField;
    private JTextField telephoneField;
    private JTextArea resultatArea;

    public CarnetAdresseGUI() {
        super("Carnet d'Adresses");
        initialiserRMI();
        initialiserGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initialiserRMI() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            carnet = (ICarnetAdresse) registry.lookup("CarnetAdresse");
        } catch (Exception e) {
            showErrorMessage("Erreur de connexion au serveur RMI: " + e.getMessage());
        }
    }

    private void initialiserGUI() {
        setLayout(new BorderLayout(20, 20));

        // Panneau principal avec bordure et espacement
        JPanel panelWithBorder = new JPanel();
        Border border = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        panelWithBorder.setBorder(border);
        panelWithBorder.setLayout(new BorderLayout(10, 10));

        // Configuration du LookAndFeel moderne (FlatLaf)
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panneau de saisie avec un look plus moderne
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("Informations du Contact"));
        inputPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        nomField = new JTextField(20);
        nomField.setBackground(new Color(255, 255, 255));
        nomField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(nomField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Téléphone:"), gbc);
        gbc.gridx = 1;
        telephoneField = new JTextField(20);
        telephoneField.setBackground(new Color(255, 255, 255));
        telephoneField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(telephoneField, gbc);

        panelWithBorder.add(inputPanel, BorderLayout.NORTH);

        // Panneau des boutons avec des couleurs attrayantes
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.setBackground(new Color(85, 170, 85));
        ajouterButton.setForeground(Color.WHITE);
        ajouterButton.addActionListener(e -> ajouterContact());
        ajouterButton.setFocusPainted(false);
        ajouterButton.setBorderPainted(false);

        JButton rechercherButton = new JButton("Rechercher");
        rechercherButton.setBackground(new Color(51, 122, 183));
        rechercherButton.setForeground(Color.WHITE);
        rechercherButton.addActionListener(e -> rechercherContact());
        rechercherButton.setFocusPainted(false);
        rechercherButton.setBorderPainted(false);

        JButton afficherButton = new JButton("Afficher Tous");
        afficherButton.setBackground(new Color(255, 136, 34));
        afficherButton.setForeground(Color.WHITE);
        afficherButton.addActionListener(e -> afficherTousLesContacts());
        afficherButton.setFocusPainted(false);
        afficherButton.setBorderPainted(false);

        buttonPanel.add(ajouterButton);
        buttonPanel.add(rechercherButton);
        buttonPanel.add(afficherButton);
        panelWithBorder.add(buttonPanel, BorderLayout.CENTER);

        resultatArea = new JTextArea(10, 40);
        resultatArea.setEditable(false);
        resultatArea.setBackground(new Color(255, 255, 255));
        resultatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        panelWithBorder.add(new JScrollPane(resultatArea), BorderLayout.SOUTH);

        add(panelWithBorder, BorderLayout.CENTER);

        // Configuration finale de la fenêtre
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void ajouterContact() {
        try {
            String nom = nomField.getText().trim();
            String telephone = telephoneField.getText().trim();
            if (nom.isEmpty() || telephone.isEmpty()) {
                showErrorMessage("Veuillez remplir tous les champs");
                return;
            }
            carnet.ajouterContact(nom, telephone);
            resultatArea.setText("Contact ajouté :\nNom: " + nom + "\nTéléphone: " + telephone);
            nomField.setText("");
            telephoneField.setText("");
        } catch (Exception e) {
            showErrorMessage("Erreur: " + e.getMessage());
        }
    }

    private void rechercherContact() {
        try {
            String nom = nomField.getText().trim();
            if (nom.isEmpty()) {
                showErrorMessage("Entrez un nom");
                return;
            }
            String resultat = carnet.rechercherContact(nom);
            resultatArea.setText("Résultat :\n" + resultat);
        } catch (Exception e) {
            showErrorMessage("Erreur: " + e.getMessage());
        }
    }

    private void afficherTousLesContacts() {
        try {
            List<String> contacts = carnet.afficherTousLesContacts();
            StringBuilder sb = new StringBuilder("Contacts:\n\n");
            for (String contact : contacts) sb.append(contact).append("\n");
            resultatArea.setText(sb.toString());
        } catch (Exception e) {
            showErrorMessage("Erreur: " + e.getMessage());
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CarnetAdresseGUI().setVisible(true);
        });
    }
}
