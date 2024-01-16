package E3CExt2;
public class Coordonnees {


/**
 * La classe Coordonnees représente les coordonnées (i,j) d'une Carte sur la Table
 * ou i représenta la ligne et j la colonne
 * Cette classe est utilisée uniquement pour intéragir avec l'utilisateur
 *  */


    /**
     * Pre-requis : x,y>=0
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme numéro de colonne
     */
    private char ligne;
    private int colonne;

    public Coordonnees(char ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Pre-requis : input est sous la forme  suivante : int,int
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme numéro de colonne
     */
    public Coordonnees(String input) {
        String[] splited = input.split(",");
        //splitted est un tableau de String qui contient les sous chaines de caracteres contenues dans input et séparées par ','
        this.ligne = splited[0].charAt(0);
        this.colonne = Integer.parseInt(splited[1]);
    }

    /**
     * Action : Retourne le numéro de la ligne
     */
    public char getLigne() {
        return this.ligne;
    }

    /**
     * Action : Retourne le numéro de la colonne
     */
    public int getColonne() {
        return this.colonne;
    }

    /**
     * Pre-requis : aucun
     * Action : Retourne vrai si la variable input est dans un format valide à savoir int,int
     * Aide : On peut utiliser Ut.estNombre pour vérifier qu'une chaîne de caractères est bien un nombre.
     */
    public static boolean formatEstValide(String input) {
        String[] splited = input.split(",");
        return input.contains(",") && splited.length == 2 && estLettre(splited[0]) && Ut.estNombre(splited[1]);
    }

    private static boolean estLettre(String str) {
        return str.length() == 1 && Character.isLetter(str.charAt(0));
    }
}
