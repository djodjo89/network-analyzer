# Installation du programme
Le programme se presentant sous la forme d'un jar executable, il n'y a pas besoin de l'installer. Neanmoins Java doit etre installe sur la machine
destinee a lancer le programme.

## Lancement
Une fois le jar executable en votre possession, il vous suffit de lancer la commande

```java -jar network-analyzer```

## Utilisation
Lorsque vous ouvrez le programme, l'interface est pratiquement vide car le programme ne dispose pas de donnees a analyser.

Pour lui en donner, cliquez sur "Fichier -> Ouvrir" dans le menu situe en haut a gauche de l'ecran.
Puis selectionnez un fichier .txt bien formatte contenant des trames reseau.


Si tout se passe bien, vous devriez voire s'afficher a l'ecran la liste des trames contenues dans le fichier, a travers laquelle vous pouvez naviguer
en cliquant sur la trame qui vous interesse. Cela aura pour effet d'afficher les entetes detaillees de la trame dans la partie basse de l'interface.
Vous pouvez ensuite cliquer sur les fleches presentes a gauche des champs pour afficher leur contenu, ou cliquer a nouveau pour le cacher.

Si vous souhaitez enregistrer les donnees obtenues sous une forme lisible par un humain, cliquez sur "Fichier -> Sauvegarder". 
Vous pourrez alors choisir ou et sous quel nom vous souhaitez enregistrer le fichier json contenant les donnees des trames.

## Fermeture
Pour fermer le programme vous pouvez soit cliquer sur la croix en haut a droite de l'interface, soit aller dans "Fichier -> Fermer"
