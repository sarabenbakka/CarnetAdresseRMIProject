Here’s a simple README file for your project following the same structure:  

```markdown
# Carnet d'Adresses RMI  
A Java application with a GUI for managing contacts using Remote Method Invocation (RMI). Users can add, search, and display contacts remotely.  

## Setup  
Create project folders and files:  
```
carnet-adresse-rmi/
├── src/
│   ├── ICarnetAdresse.java
│   ├── CarnetAdresseImpl.java
│   ├── ServeurRMI.java
│   ├── ClientRMI.java
│   ├── CarnetAdresseGUI.java
└── README.md
```

## Compilation & Running  
Compile all Java files:  
```bash
javac src/*.java
```
Start the RMI registry:  
```bash
rmiregistry &
```
Run the server:  
```bash
java -cp src CarnetAdresseServer
```
Run the client:  
```bash
java -cp src CarnetAdresseClient
```

## Features  
- Add a new contact ("Ajouter")  
- Search for a contact by name ("Rechercher")  
- Display all stored contacts ("Afficher Tous")  
- Remote communication using Java RMI  

## Requirements  
- Java JDK 8 or higher  
- RMI registry service  
- Text editor or IDE  
```

