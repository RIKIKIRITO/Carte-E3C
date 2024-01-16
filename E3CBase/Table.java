package E3CBase;

/**
 * La classe Table représente une table de jeu contenant des cartes.
 *
 * La table est représentée graphiquement par une matrice.
 * On peut donc avoir des tables de dimensions 3x3, 3x4, 4x4, 5x5, 10x15...
 * En mémoire, la Table est représentée par un simple tableau (à une dimension)
 * Quand elle est initialisée, la table est vide.
 *
 * Pour désigner une carte sur la table, on utilise des coordonées (i,j) ou i représenta la ligne et j la colonne.
 * Les lignes et colonnes sont numérotés à partir de 1.
 * Les cartes sont numérotées à partir de 1.
 *
 * Par exemple, sur une table 3x3, la carte en position (1,1) est la premiere carte du tableau, soit celle à l'indice 0.
 * La carte (2,1) => carte numéro 4 stockée à l'indice 3  dans le tableau représentant la table
 * La carte (3,3) => carte numéro 9 stockée à l'indice 8  dans le tableau représentant la table
 */

public class Table {

    private Carte[] table;

    private Coordonnees coordonnees;
    private int hauteur;
    private int largeur;
    private Paquet paquet;

    /**
     * Pre-requis : hauteur >=3, largeur >=3
     * <p>
     * Action : Construit une table "vide" avec les dimensions précisées en paramètre.
     * <p>
     * Exemple : hauteur : 3, largeur : 3 => construit une table 3x3 (pouvant donc accueillir 9 cartes).
     */

    public Table(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.table = new Carte[hauteur * largeur];



    }
    public void  setPaquet(Paquet paquet){
        this.paquet = paquet;
    }
    public int getHauteur(){
        return this.hauteur;
    }
    public  int getLargeur(){
        return this.largeur;
    }



    /**
     * Résullat : Le nombre de cartes que la table peut stocker.
     */

    public int getTaille() {
        int compteur = 0;
        for (int i = 0; i < this.table.length; i++) {
                compteur++;
            }
        return compteur;
    }
    public void placecarte (int[] valeurcarte){
            Carte[] carteapiocher = paquet.piocher(valeurcarte.length);
        int [] compt = new int[getHauteur() * getLargeur()];
            for (int i = 0; i<valeurcarte.length; i++){
                for (int j = 0 ; j<table.length;j++){
                    if (compt[j] ==valeurcarte[i] ){
                        delete(i);
                        this.table[j] = carteapiocher[i];

                    }
                }

            }

    }
    public void delete (int  i){
                table[i]= null;
    }


    public void placetoutecarte (){
        Carte[] carteapiocher = paquet.piocher(this.table.length);
        for (int i = 0; i<this.table.length; i++){
            this.table[i] = carteapiocher[i];
        }
    }

    public Carte[] getCartes() {
        Carte[] cartes = new Carte[this.table.length];
        int index = 0;

        for (int i = 0; i < this.table.length; i++) {
            cartes[index] = this.table[i];
                index++;
        }

        return cartes;
    }
public  Carte[] getCarteslectionner (int[] valeur){
    Carte[] cartes = new Carte[this.table.length];
        for (int i = 0; i < this.table.length; i++) {
        cartes[i] = this.table[i];

    }
        return cartes;
}

    /**
     * Pre-requis : la table est pleine
     * Action : Affiche des cartes de la table sous forme de matrice
     * L'affichage des cartes doit respecter le format défini dans la classe Carte (chaque carte doit donc être colorée).
     * On ne donne volontairement pas d'exemple puisque celà depend du choix fait pour votre représentation de Carte
     */

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= hauteur; i++) {
            for (int j = 1; j <= largeur; j++) {
                int indice = (i - 1) * largeur + j;
                Carte carte = table[indice - 1];

                if (carte != null) {
                    result.append(carte.toString()).append("\n");
                }
            }
        }

        return result.toString();
    }


    /**
     * Résullat : Vrai la carte située aux coordonnées précisées en paramètre est une carte possible pour la table.
     */
    public boolean carteExiste(Coordonnees coordonnees) {
        int ligne = coordonnees.getLigne() ;
        int colonne = coordonnees.getColonne() ;
        if (ligne >= 0 && colonne >= 0){
            if (ligne <= this.largeur ){
                    if ( colonne <= this.hauteur) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Pre-requis :
     * Il reste des cartes sur la table.
     * <p>
     * Action : Fait sélectionner au joueur (par saisie de ses coordonnées) une carte valide (existante) de la table.
     * L'algorithme doit faire recommencer la saisie au joueur s'il ne saisit pas une carte valide.
     * <p>
     * Résullat : Le numéro de carte sélectionné.
     */

    public int faireSelectionneUneCarte() {

        int ligne = 0;
        int colonne = 0;
        int valeur =0;
        do {
            System.out.println("Saisissez la ligne :");
            ligne = Ut.saisirEntier();

            System.out.println("Saisissez la colonne : ");
            colonne = Ut.saisirEntier();

            if (!carteExiste(new Coordonnees(ligne, colonne))) {
                System.out.println("Saisir une carte valide.");
            }
        } while (!carteExiste(new Coordonnees(ligne, colonne)));
        for (int i = 0; i< colonne; i++){
            for (int j = 0 ; j<ligne; j++){
                valeur++;
            }
        }
        return valeur;
    }



    /**
     * Pre-requis : 1<=nbCartes <= nombre de Cartes de this
     * Action : Fait sélectionner nbCartes Cartes au joueur  sur la table en le faisant recommencer jusqu'à avoir une sélection valide.
     * Il ne doit pas y avoir de doublons dans les numéros de cartes sélectionnées.
     * Résullat : Un tableau contenant les numéros de cartes sélectionnées.
     */

    public int[] selectionnerCartesJoueur(int nbCartes) {

        int[] select= new int[nbCartes];
        for (int i = 0; i < nbCartes; i++) {
            select[i] = faireSelectionneUneCarte();

        }

        return select;
    }


    /**
     * Action : Affiche les cartes de la table correspondantes aux numéros de cartes contenus dans selection
     * Exemple de format d'affichage : "Sélection : 2-O-H 3-O-H 2-C-H"
     * Les cartes doivent être affichées "colorées"
     */

    public void afficherSelection(int[] select) {

        System.out.print("Sélections : ");
        for (int numCarte : select) {
            int ligne = (numCarte - 1) / largeur + 1;
            int colonne = (numCarte - 1) % largeur + 1;

            Carte carte = table[numCarte - 1];
            String couleurCode = carte.Getcouleurcode();

            System.out.print(numCarte + "-" + couleurCode + carte.getFigure() + "-" + carte.getCouleur() + "-"
                    + carte.getTexture() + " ");
        }

        System.out.println();
    }

}







