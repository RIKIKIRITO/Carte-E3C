package E3CExt2;

import java.util.Random;

/**
 * La classe Paquet représente un paquet de cartes.
 * Les cartes sont stockées dans un tableau fixe et un indice (entier) permet de connaître le nombre de cartes
 * restantes (non piochées) dans le paquet. Quand on pioche, cet indice diminue.
 * Dans les traitements, on considère alors seulement les cartes se trouvant entre 0 et cet indice (exclus).
 * Par conséquent, on ne supprime pas vraiment les cartes piochées, on les ignore juste.
 * On a donc besoin de connaître :
 * - Le tableau stockant les cartes.
 * - Le nombre de cartes restantes dans le paquet.
 */
public class Paquet {

    /**
     * Pre-requis : figures.length > 0, couleurs.length > 0, textures.length > 0, nbFiguresMax > 0
     * <p>
     * Action : Construit un paquet de cartes mélangé contenant toutes les cartes incluant 1 à nbFiguresMax figures
     * qu'il est possibles de créer en combinant les différentes figures, couleurs et textures précisées en paramètre.
     * Bien sûr, il n'y a pas de doublons.
     * <p>
     * Exemple :
     * - couleurs = [Rouge, Jaune]
     * - nbFiguresMax = 2
     * - figures = [A, B]
     * - textures = [S, T]
     * Génère un paquet (mélangé) avec toutes les combinaisons de cartes possibles pour ces caractéristiques : 1-A-S (rouge), 1-A-T (rouge), etc...
     */
    private Carte[] cartes;
    private int nbCartesRestantes;

    public Paquet(Couleur[] couleurs, int nbFiguresMax, Figure[] figures, Texture[] textures) {
        nbCartesRestantes = getNombreCartesAGenerer(couleurs,nbFiguresMax,figures,textures);
        this.cartes = new Carte[nbCartesRestantes];
        int index = 0;
        for (Couleur couleur : couleurs) {
            for (int nbFigures = 1; nbFigures <= nbFiguresMax; nbFigures++) {
                for (Figure figure : figures) {
                    for (Texture texture : textures) {
                        this.cartes[index] = new Carte(couleur, nbFigures, figure, texture);
                        index++;
                    }
                }
            }
        }
        this.cartes = cartes;
        melanger();

    }

    /**
     * Action : Construit un paquet par recopie en copiant les données du paquet passé en paramètre.
     */
    public Paquet(Paquet paquet) {
        this.cartes = new Carte[paquet.cartes.length];
        for (int i = 0; i < paquet.cartes.length; i++) {
            this.cartes[i] = new Carte(paquet.cartes[i].getCouleur(), paquet.cartes[i].getNbFigures(), paquet.cartes[i].getFigure(), paquet.cartes[i].getTexture());
        }
        this.nbCartesRestantes = this.cartes.length;
    }
    public int getNbCartesRestantes(){
        return this.nbCartesRestantes;
    }


    /**
     * Pre-requis : figures.length > 0, couleurs.length > 0, textures.length > 0, nbFiguresMax > 0
     * <p>
     * Resultat : Le nombre de cartes uniques contenant entre 1 et nbFiguresMax figures qu'il est possible de générer en
     * combinant les différentes figures, couleurs et textures précisées en paramètre.
     */


    public static int getNombreCartesAGenerer(Couleur[] couleurs, int nbFiguresMax, Figure[] figures, Texture[] textures) {
        int totalFigures = nbFiguresMax * figures.length;
        return couleurs.length * totalFigures * textures.length ;
    }


    /**
     * Action : Mélange aléatoirement les cartes restantes dans le paquet.
     * Attention, on rappelle que le paquet peut aussi contenir des cartes déjà piochées qu'il faut ignorer.
     */
    public void melanger() {
        Random random = new Random();
        for (int i = nbCartesRestantes - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Carte c = cartes[i];
            this.cartes[i] = this.cartes[j];
            this.cartes[j] = c ;
        }
    }

    /**
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this) selon la méthode du tri selection.
     * Le tri est effectué à partir des données du paquet courant (this) mais celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées  qu'il faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=Ns4TPTC8whw&t=2s vidéo explicative
     */

    private static void triSelection(Carte[] cartes) {
        int c = cartes.length;
        for (int i = 0; i < c - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < c; j++) {
                if (cartes[j].compareTo(cartes[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            Carte k = cartes[minIndex];
            cartes[minIndex] = cartes[i];
            cartes[i] = k;
        }
    }

    public Paquet trierSelection() {
        Paquet paquetTrie = new Paquet(this);
        triSelection(paquetTrie.cartes);
        return paquetTrie;
    }

    /**
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this) selon la méthode du tri bulles.
     * Le tri est effectué à partir des données du paquet courant (this) mais celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées  qu'il faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=lyZQPjUT5B4&embeds_referring_euri=https%3A%2F%2Fwww.developpez.com%2F&source_ve_path=Mjg2NjY&feature=emb_logo
     * vidéo explicative
     */
    private static void triBulles(Carte[] cartes) {
        int c = cartes.length;
        for (int i = 0; i < c - 1; i++) {
            for (int j = 0; j < c - i - 1; j++) {
                if (cartes[j].compareTo(cartes[j + 1]) > 0) {
                    Carte k = cartes[j];
                    cartes[j] = cartes[j + 1];
                    cartes[j + 1] = k;
                }
            }
        }
    }

    public Paquet trierBulles() {
        Paquet paquetTrie = new Paquet(this);
        triBulles(paquetTrie.cartes);
        return paquetTrie;
    }

    /**
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this) selon la méthode du tri insertion.
     * Le tri est effectué à partir des données du paquet courant (this) mais celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées  qu'il faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=ROalU379l3U&t=1s : vidéo explicative
     */
    private static void triInsertion(Carte[] cartes) {
        int c = cartes.length;
        for (int i = 1; i < c; i++) {
            Carte k = cartes[i];
            int j = i - 1;

            while (j >= 0 && cartes[j].compareTo(k) > 0) {
                cartes[j + 1] = cartes[j];
                j = j - 1;
            }
            cartes[j + 1] = k;
        }
    }

    public Paquet trierInsertion() {
        Paquet paquetTrie = new Paquet(this);
        triInsertion(paquetTrie.cartes);
        return paquetTrie;
    }

    /**
     * Action : Permet de tester les différents tris.
     * On doit s'assurer que chaque tri permette effectivement de trier un paquet mélangé.
     * Des messages d'informations devront être affichés.
     * La méthode est "static" et ne s'effectue donc pas sur la paquet courant "this".
     */
    /**
     * Action : Permet de tester les différents tris.
     * On doit s'assurer que chaque tri permet effectivement de trier un paquet mélangé.
     * Des messages d'informations devront être affichés.
     * La méthode est "static" et ne s'effectue donc pas sur la paquet courant "this".
     */
    public static void testTris() {
        Couleur[] couleurs = {Couleur.Rouge, Couleur.Blue};
        Figure[] figures = {Figure.Triangle, Figure.Ovale};
        Texture[] textures = {Texture.Cuir, Texture.Cuir};

        Paquet paquet = new Paquet(couleurs, 2, figures, textures);

        System.out.println("Paquet initial : " + paquet);

        Paquet paquetTriSelection = paquet.trierSelection();
        System.out.println("Tri par sélection : " + paquetTriSelection);

        Paquet paquetTriBulles = paquet.trierBulles();
        System.out.println("Tri à bulles : " + paquetTriBulles);

        Paquet paquetTriInsertion = paquet.trierInsertion();
        System.out.println("Tri par insertion : " + paquetTriInsertion);
    }

    /**
     * Pre-requis : 0 < nbCartes <= nombre de cartes restantes dans le paquet.
     * <p>
     * Action : Pioche nbCartes Cartes au-dessus du Paquet this (et met à jour son état).
     * <p>
     * Résultat : Un tableau contenant les nbCartes Cartes piochées dans le Paquet.
     * <p>
     * Exemple :
     * Contenu paquet : [A, B, C, D, E, F, G]
     * Nombre de cartes restantes : 7. On considère donc seulement les cartes de 0 à 6.
     * <p>
     * piocher(3)
     * Contenu paquet : [A, B, C, D, E, F, G]
     * Nombre de cartes restantes : 4
     * Renvoie [G, F, E]
     */
    public Carte[] piocher(int nbCartes) {

        if (nbCartes > nbCartesRestantes) {
            nbCartes = nbCartesRestantes;
        }
        Carte[] cartesPiochees = new Carte[nbCartes];
        for (int i = 0; i < nbCartes; i++) {
            cartesPiochees[i] = this.cartes[nbCartesRestantes - nbCartes + i];
        }
        nbCartesRestantes -= nbCartes;
        return cartesPiochees;
    }


    /**
     * Résultat : Vrai s'il reste assez de cartes dans le paquet pour piocher nbCartes.
     */
    public boolean peutPiocher(int nbCartes) {
        if (nbCartes > nbCartesRestantes){
            return false;
        }
        return true;
    }

    /**
     * Résultat : Vrai s'il ne reste plus aucune cartes dans le paquet.
     */
    public boolean estVide() {return nbCartesRestantes == 0;}

    /**
     * Résultat : Une chaîne de caractères représentant le paquet sous la forme d'un tableau
     * [X, Y, Z...] représentant les cartes restantes dans le paquet.
     *
     * Exemple :
     * Contenu paquet : 1-O-P (rouge), 2-C-V (jaune), 3-L-P (jaune), 3-L-P (rouge), 1-L-V (bleu)
     * Nombre de cartes restantes : 3
     * Retourne [1-O-P, 2-C-V, 3-L-P] (et chaque représentation d'une carte est coloré selon la couleur de la carte...)
     */

    @Override
    public String toString() {
        String r = "[";
        for (int i = 0; i < nbCartesRestantes; i++) {
            r += cartes[i].toString();
            if (i < nbCartesRestantes - 1) {
                r += ", ";
            }
        }
        r += "]";
        return r;
    }
}




