# Projet ObjectAid 
### Laury Lucie, Miesch Nathanaël, Dardenne Gregory, Melbeck Nathan

## Initialisation
Pour le bon fonctionnement de l'application vous devez
ajouter un module pour l'enregistrement de l'image.
Suivez les instructions suivantes pour le module:

File -> Project Structure... -> Librairies -> "+" dans la fenêtre de gauche
-> From Maven.. -> rechercher "org.openjfx:javafx-swing:11" -> valider -> apply

## Utilisation
Nous sommes dans le projet ObjectAid_SAE donc le package enregistrer
par l'analyseur est "com.example.object_aid"
Donc tout les classes se trouvant dans le src n'ayant pas le package "com.example.object_aid"
sont considérés comme externe au projet.

## Fonctionnalités 

- Glisser déposer (d'un fichier ".java", d'un dossier)
- Afficher ou retirer des éléments dans une classe
- Déplacer une classe dans le graphe
- Ajout d'information dans une classe (attributs, methodes)
- Générer le squelette d'une classe
