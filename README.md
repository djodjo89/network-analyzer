# Network analyzer
Un analyseur réseau qui prend en entree un fichier contenant des trames réseau, les affiche dans une interface graphique, et permet
de les sauvegarder sous la forme d'un fichier json lisible.

## Modèle
Le modèle est divise en trois parties :
* Fichier, avec un extracteur et un sauvegardeur : 
  * L'extracteur est capable d'analyser un fichier rempli de trames et de sortir une liste de trames
  * Le sauvegardeur récupère une liste de trames et la sauvegarde sous forme de fichier JSON en enlevant les champs inutiles au programme


* Réseau, avec la representation sous forme d'objets des différentes donnees contenus dans les trames :
  * L'ObjetReseau, un arbre dont tous les objets suivants héritent, qui permet de les ranger de facon hiérarchique
  * La Trame, un objet haut niveau contenant une liste d'en-tetes ainsi que des meta-donnees comme l'IP source ou celle de destination, ou encore son numéro
  * L'Entête, un objet contenant le protocole et la description de l'entête (par exemple "Internet Protocol Version 4" et "Source: 192.168.1.1, Destination: 192.168.1.2") ainsi qu'une liste de champs contenant les donnees de la trame
  * Ces Champs sont l'entité la plus bas niveau du programme : elles ont une etiquette et une valeur (souvent une chaine hexadécimale ou une valeur décimale), mais les ListeChamps sont des Champs spéciaux ayant pour valeur une liste d'autres champs, par exemple le Champ Option qui contient Record Route, Type et Pointer


* Type, la partie la plus abstraite du code permettant d'effectuer des conversions d'hexadecimal vers decimal ou de decimal vers binaire, voire de combiner les deux :
  * NumericType, qui prend en paramètre un entier et ressort un nombre binaire ou hexadecimal sous forme de chaine de caractères
  * StringType, qui prend en paramètre une chaine de caractère hexadécimale ou binaire et donne en retour un entier

## Vue
La vue, hormis le menu d'action est divisée en deux parties :
* VueTramesTable, une trame affichée dans une table avec les metadonnées associées, parmi d'autres VueTrameItem. L'utilisateur peut sélectionner une de ces trames pour mettre a jour la "trame courante"
* VueTrameEntete, l'affichage détaillé de la trame sélectionnée (la premiere par défaut) avec les entêtes et les champs associes, le tout sous forme de menu arborescent avec des couples cle-valeur

## Contrôleur
Le contrôleur fait le lien, grace a une liste observable de trames, entre le modèle et la vue.
Son menu d'action permet également a l'utilisateur d'ouvrir ou de sauvegarder un fichier, mais aussi d'afficher les erreurs en cas de problème, a la lecture d'un fichier par exemple