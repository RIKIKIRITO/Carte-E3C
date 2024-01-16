package E3CBase;

/**
 * La classe Carte représente une carte possèdant une figure répétée un certain nombre de fois avec une texture et une couleur.
 * On a besoin de connaître :
 * - La figure représentée,
 * - Le nombre de fois où la figure est représentée,
 * - La couleur de la figure,
 * - La texture de la figure.
 */
public class Carte {

    /**
     * Pre-requis : nbFigures > 0
     * Action : Construit une carte contenant nbFigures "figures" qui possèdent une "texture" et une "couleur"
     * Exemple : new Carte(Couleur.ROUGE, 2, Figure.OVALE, Texture.PLEIN) représente une carte contenant 2 figures ovales rouge et pleines
     */
    private int nbFigures;
    private final Couleur couleur;
    private final Figure figure;
    private final Texture texture;


    public Carte(Couleur couleur, int nbFigures, Figure figure, Texture texture) {

        this.texture = texture;

        this.figure = figure;

        this.couleur = couleur;
        this.nbFigures = nbFigures;

    }

    /**
     * Résultat : Le nombre de fois où la figure est représentée sur la carte.
     */

    public int getNbFigures() {
        return this.nbFigures;
    }

    /**
     * Résultat : La figure représentée sur la carte.
     */

    public Figure getFigure() {
        return this.figure;
    }

    /**
     * Résultat : La couleur représentée sur les figures de la carte.
     */

    public Couleur getCouleur() {
        return this.couleur;
    }

    /**
     * Résultat : La texture représentée sur les figures de la carte.
     */

    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Action : compare les attributs de "this" et de "carte"
     * afin de déterminer si this est plus petit, égal ou plus grand que "carte"
     * <p>
     * L'odre d'importance des attributs est celui donné dans le constructeur (du plus prioritaire au moins prioritaire) :
     * Couleur, nombre de figures, figure, texture.
     * Pour comparer les couleurs, les figures et les textures, on utilisera leur position (ordinal) dans
     * leurs énumérations respectives.
     * Ainsi, pour toute paire {c1,c2} de Carte, c1 est inférieure à c2 si et seulement si
     * la valeur de c1 est inférieure à celle de c2 pour la caractéristique ayant la plus grande priorité
     * parmi celles pour lesquelles c1 et c2 ont des valeurs différentes.
     * <p>
     * <p>
     * Résultat :
     * 0 si "this" est égal à "carte"!
     * Un nombre négatif si "this" est inférieur à "carte"
     * Un nombre strictement positif si "this "est supérieur à "carte"
     */

    public int compareTo(Carte carte) {
        int valeur_couleur = this.couleur.ordinal() - carte.couleur.ordinal();
        int valeur_nbfigure = this.nbFigures - carte.nbFigures;
        int valeur_figure = this.figure.ordinal() - carte.figure.ordinal();
        int valeur_texture = this.texture.ordinal() - carte.texture.ordinal();
        if ( this.couleur != carte.couleur ||(valeur_nbfigure != 0 || (valeur_figure != 0 || (valeur_texture != 0)))) {
            if (valeur_couleur < 0) {
                return -1;
            } else if (valeur_couleur > 0) {
                return 1;
            }
            if (valeur_nbfigure < 0) {
                return -1;
            } else if (valeur_nbfigure > 0) {
                return 1;
            }
            if (valeur_figure < 0) {
                return -1;
            } else if (valeur_figure > 0) {
                return 1;
            }
            if (valeur_texture < 0) {
                return -1;
            } else if (valeur_texture > 0) {
                return 1;
            }
        }
        return 0;
    }


    public String Getcouleurcode() {
        if (getCouleur() == Couleur.Vert) {
            return "\u001B[32m";
        }
        if (getCouleur() == Couleur.Rouge) {
            return "\u001B[31m";
        }
        if (getCouleur() == Couleur.Blue) {
            return "\u001B[34m";
        }
        if (getCouleur() == Couleur.Purple) {
            return "\u001B[35m";
        }
        return "\u001B[30m";

    }

    /**
     * Résultat :
     * Une chaîne de caractères représentant la carte de la manière suivante :
     * - Le texte est coloré selon la couleur de la carte
     * - La chaîne de caractères retournée doit faire apparaitre toutes les caractériqtiques d'une carte sauf la couleur puisque le texte est affiché en couleur
     * (Vous devez choisir une représentation agréable pour l'utilisateur)
     */
    @Override
    public String toString() {

        String couleur = Getcouleurcode();
        String carte = "##########\n";
        carte += "# " + couleur + this.texture +"   #\n";
        carte += "#   " + couleur + this.nbFigures + "    #\n";
        carte += "#   " + couleur + this.figure + "    #\n";
        carte += "##########";
        String reset = "\u001B[0m";
        return couleur + carte + reset + "    \n";
    }
}










