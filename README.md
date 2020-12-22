# Network analyzer
Un analyseur rseau qui prend en entree un fichier contenant des trames reseau, les affiche dans une interface graphique, et permet
de les sauvegarder sous la forme d'un fichier json lisible.

## Modele
Le modele est divise en trois parties :
* Fichier, avec un extracteur et un sauvagardeur : 
  * L'extracteur est capable d'analyser un fichier rempli de trames et de sortir une liste de trames
  * Le sauvegardeur recupere une liste de trames et la sauvegarde sous forme de fichier JSON en enlevant les champs inutiles au programme


* Reseau, avec la representation sous forme d'objets des differentes donnees contenus dans les trames :
  * L'ObjetReseau, un arbre dont tous les objets suivants heritent, qui permet de les ranger de facon hierarchique
  * La Trame, un objet haut niveau contenant une liste d'en-tetes ainsi que des meta-donnees comme l'IP source ou celle de destination, ou encore son numero
  * L'Entete, un objet contenant le protocole et la description de l'entete (par exemple "Internet Protocol Version 4" et "Source: 192.168.1.1, Destination: 192.168.1.2") ainsi qu'une liste de champs contenant les donnees de la trame
  * Ces Champs sont l'entite la plus bas niveau du programme : elles ont une etiquette et une valeur (souvent une chaine hexadecimale ou une valeur decimale), mais les ListeChamps sont des Champs speciaux ayant pour valeur une liste d'autres champs, par exemple le Champ Option qui contient Record Route, Type et Pointer


* Type, la partie la plus abstraite du code permettant d'effectuer des conversions d'hexadecimal vers decimal ou de decimal vers binaire, voire de combiner les deux :
  * NumericType, qui prend en parametre un entier et ressort un nombre binaire ou hexadecimal sous forme de chaine de caracteres
  * StringType, qui prend en parametre une chaine de caractere hexadecimale ou binaire et donne en retour un entier

## Vue
La vue, hormis le menu d'action est divisee en deux parties :
* VueTranesTable, une trame affichee dans une table avec les metadonnees associees, parmis d'autres VueTrameItem. L'utilisateur peut selectionner une de ces trames pour mettre a jour la "trame courante"
* VueTrameEntete, l'affichage detaille de la trame selectionnee (la premiere par defaut) avec les entetes et les champs associes, le tout sous forme de menu arborescent avec des couples cle-valeur

## Controleur
Le controleur fait le lien, grace a une liste observable de trames, entre le modele et la vue.
Son menu d'action permet egalement a l'utilisateur d'ouvrir ou de sauvegarder un fichier, mais aussi d'afficher les erreurs en cas de probleme, a la lecture d'un fichier par exemple