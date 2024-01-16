package E3CExt2;

/**
 * La classe Jeu permet de faire des parties du jeu "E3Cète" soit avec un humain, soit avec un ordinateur.
 *
 * Règles :
 *
 *  - On possède un paquet de cartes qui représentent entre une et trois figures (losange, carre ou ovale), une texture
 *   (vide, hachuré ou plein) et une couleur (rouge, jaune ou bleu). La cardinalité des énumérations est fixée à 3 pour cette partie 2 de la SAE uniquement.
 *
 *  - Une table 3x3 permet de stocker 9 cartes. Au début de la partie, on dispose 9 cartes sur la table, piochées sur le dessus du paquet.
 *
 *  - A chaque tour, le joueur doit essayer de trouver un E3C.
 *
 *  - Le joueur doit désigner trois cartes par leurs coordonnées.
 *      - Si ces cartes forment un E3C, il gagne trois points sur son score.
 *      - Si ce n'est pas un E3C, il perd 1 point sur son score.
 *
 *   - Les trois cartes sont remplacées par de nouvelles cartes piochées dans le paquet.
 *
 *   - La partie se termine quand il n'y a plus de cartes dans le paquet (même s'il reste des cartes sur la table).
 *
 * On a donc besoin :
 *
 * - D'un paquet pour stocker toutes les cartes et avoir une pioche.
 * - D'une table.
 * - De quoi stocker le score du joueur (humain ou ordinateur).
 */
public class Jeu {
    private Table table = new Table(3, 3);
    private Paquet paquet;
    private Carte carte;
    private int score;


    /**
     * Action :
     * - Initialise le jeu "E3Cète" en initialisant le score du joueur, le paquet et la table.
     * - La table doit être remplie.
     */

    public Jeu() {
        this.score = 0;
        this.paquet = new Paquet(Couleur.values(), 3, Figure.values(), Texture.values());
        this.table.setPaquet(paquet);
        this.table.placetoutecarte();
        this.table.toString();

    }
    /**
     * Action : Pioche autant de cartes qu'il y a de numéros de cartes dans le tableau numerosDeCartes et les place
     * aux positions correspondantes sur la table.
     */

    public void piocherEtPlacerNouvellesCartes(int[] numerosDeCartes) {
        if (paquet.peutPiocher(paquet.getNbCartesRestantes())){
                table.placecarte(numerosDeCartes);
        }
    }

    /**
     * Action : Ré-initialise les données et variables du jeu afin de rejouer une nouvelle partie.
     */

    public void resetJeu() {
        this.score = 0;
        this.paquet = new Paquet(Couleur.values(), 3, Figure.values(), Texture.values());
        this.table.setPaquet(paquet);
        this.table.placetoutecarte();
        this.table.toString();
    }


    /**
     * Résullat : Vrai si les cartes passées en paramètre forment un E3C.
     */

    public static boolean estUnE3C(Carte[] cartes) {
        int i = 0;
        if (cartes[i].getCouleur() == cartes[i+1].getCouleur() && cartes[i+2].getCouleur() == cartes[i].getCouleur() ||(cartes[i].getCouleur() != cartes[i+1].getCouleur() && cartes[i+2].getCouleur() != cartes[i].getCouleur() && cartes[i+2].getCouleur() != cartes[i+1].getCouleur())){
            if (cartes[i].getFigure() ==  cartes[i+1].getFigure() && cartes[i].getFigure() == cartes[i+2].getFigure() || (cartes[i].getFigure() !=  cartes[i+1].getFigure() && cartes[i].getFigure() != cartes[i+2].getFigure() && cartes[i+1].getFigure() != cartes[i+2].getFigure())){
                if (cartes[i].getTexture() ==  cartes[i+1].getTexture() && cartes[i].getTexture() == cartes[i+2].getTexture() || (cartes[i].getTexture() != cartes[i+1].getTexture() && cartes[i].getTexture() != cartes[i+2].getTexture() && cartes[i+1].getTexture() != cartes[i+2].getTexture())) {
                    if (cartes[i].getNbFigures() == cartes[i+1].getNbFigures() && cartes[i].getNbFigures()== cartes[i+2].getNbFigures() || (cartes[i].getNbFigures() != cartes[i+1].getNbFigures() && cartes[i].getNbFigures() != cartes[i+2].getNbFigures() && cartes[i+1].getNbFigures() != cartes[i+2].getNbFigures())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }




    /**
     * Action : Recherche un E3C parmi les cartes disposées sur la table.
     * Résullat :
     *  - Si un E3C existe, un tableau contenant les numéros de cartes (de la table) qui forment un E3C.
     *  - Sinon, la valeur null.
     */

    public int[] chercherE3CSurTableOrdinateur() {
        Carte[] cartes = table.getCartes();
        int[] cartesE3C = new int[3];

        // Parcours des cartes sur la table pour trouver les trois cartes qui forment une E3C
        for (int i = 0; i < cartes.length; i++) {
            for (int j = i + 1; j < cartes.length; j++) {
                for (int k = j + 1; k < cartes.length; k++) {
                    int[] cartesS = {i , j , k };

                    //  vérification de si les cartes forment un E3C
                    if (estUnE3C(new Carte[]{cartes[i], cartes[j], cartes[k]})) {
                        cartesE3C = cartesS;
                        return cartesE3C;
                    }
                }
            }
        }

        // Pas E3C
        return new int[0];
    }

    /**
     * Action : Sélectionne alétoirement trois cartes sur la table.
     * La sélection ne doit pas contenir de doublons
     * Résullat : un tableau contenant les numéros des cartes sélectionnées alétaoirement
     */

    public int[] selectionAleatoireDeCartesOrdinateur() {
        int[] selection = new int[3];
        boolean check = false;
        int ligne;
        int colonne;
        int numeros;
        for (int i = 0; i < 3; i++) {
            while (check == false) {
                ligne = Ut.randomMinMax(0,this.table.getLargeur() );
                colonne = Ut.randomMinMax(0, this.table.getHauteur());
                numeros = ligne + colonne;
                for (int j = 0; j < selection.length; j++) {
                    if (numeros == selection[i]) {
                        check = false;
                    } else {
                        selection[i] = numeros;
                    }
                }

            }

        }
        return selection;
    }

    /**
     * Résullat : Vrai si la partie en cours est terminée.
     */

    public boolean partieEstTerminee() {return paquet.estVide();}

    /**
     * Action : Fait jouer un tour à un joueur humain.
     * La Table et le score du joueur sont affichés.
     * Le joueur sélectionne 3 cartes.
     *  - Si c'est un E3C, il gagne trois points.
     *  - Sinon, il perd un point.
     * Les cartes sélectionnées sont remplacées.
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */

    public void jouerTourHumain() {
        System.out.println( " Tour de l'humain : ");
        Carte[] teste = new Carte[3];
        System.out.println( " Table actuelle : ");
        table.toString();

        System.out.println( " Votre score : " + score );

        int[] cartesE3C = table.selectionnerCartesJoueur(3);

        if ( cartesE3C.length > 0){
            System.out.println(" Bonne réponse ");
            score += 3;
        }else {
            System.out.println(" Mauvaise réponse , -1 point. ");
            piocherEtPlacerNouvellesCartes(cartesE3C);
            score --;
        }
    }

    /**
     * Action : Fait jouer une partie à un joueur humain.
     * A la fin, le score final du joueur est affiché.
     */

    public void jouerHumain() {
        while (!partieEstTerminee()) {
            table.toString();
            jouerTourHumain();
            Ut.pause(200);
        }
        System.out.println("Partie terminée. Score final de l'humain : " + score);
        resetJeu();
    }


    /**
     * Action : Fait jouer un tour à l'ordinateur.
     * La Table et le score de l'ordinateur sont affichés.
     * L'ordinateur sélectionne des cartes :
     *  - L'ordinateur essaye toujours de trouver un E3C sur la table. S'il en trouve un, il gagne donc trois points.
     *  - S'il n'en trouve pas, il se rabat sur 3 cartes sélectionnées aléatoirement et perd un point.
     * Les cartes sélectionnées sont remplacées.
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */

    public void joueurTourOrdinateur() {

        System.out.println("Tour de l'ordinateur.");

        // table et le score de l'ordinateur
        System.out.println("Table actuelle :");
        table.toString();
        System.out.println("Score de l'ordinateur : " + score);

        // Cherche un E3C sur la table
        int[] cartesE3C = chercherE3CSurTableOrdinateur();

        if (cartesE3C != null) {
            // Si un E3C est trouvé, + trois points au score
            System.out.println("L'ordinateur a trouvé un E3C !");
            score += 3;
            piocherEtPlacerNouvellesCartes(cartesE3C);
        } else {
            // Si aucun E3C n'est trouvé, sélection aléatoire
            System.out.println("L'ordinateur n'a pas trouvé d'E3C. Sélection aléatoire de 3 cartes.");
            int[] cartesAleatoires = selectionAleatoireDeCartesOrdinateur();


            // Piocher de nouvelles cartes et les remplacer
            piocherEtPlacerNouvellesCartes(cartesAleatoires);

            // Retirer un point du score
            System.out.println("L'ordinateur perd un point.");
            score--;
        }
    }


    /**
     * Action : Fait jouer une partie à l'ordinateur.
     * Une pause est faite entre chaque tour (500 ms ou plus) afin de pouvoir observer la progression de l'ordinateur.
     * A la fin, le score final de l'ordinateur est affiché.
     * Rappel : Ut.pause(temps) permet de faire une pause de "temps" millisecondes
     */

    public void jouerOrdinateur() {

        while (!partieEstTerminee()) {
            Ut.afficher(table.toString());
            Ut.afficher(paquet.getNbCartesRestantes());
            joueurTourOrdinateur();
            Ut.pause(200);
        }
        System.out.println("Partie terminée. Score final de l'ordinateur : " + score);
        resetJeu();
    }

    /**
     * Action : Permet de lancer des parties de "E3Cète" au travers d'un menu.
     * Le menu permet au joueur de sélectionner une option parmi :
     *  - humain : lance une partie avec un joueur humain
     *  - ordinateur : lance une partie avec un ordinateur
     *  - terminer : arrête le programme.
     * Après la fin de chaque partie, les données de jeu sont ré-initialisées et le menu est ré-affiché
     * (afin de faire une nouvelle sélection).
     * Les erreurs de saisie doivent être gérées (si l'utilisateur sélectionne une option inexistante).
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */

    public void jouer() {


        String menu = " ================================================ \n";
        menu += " ||  "+ "      MENU SELECTION JEU                 " + " ||\n";
        menu += " ================================================ \n";
        menu += " || " + "          MODE                            " + " || \n";
        menu += " || " + " 1-Lancé une partie avec un joueur humain " + " || \n" ;
        menu += " || " + " 2-Lancé une partie avec un ordinateur    " + " || \n";
        menu += " || " + " 3- Finir la partie                        " + "|| \n" ;
        menu += " ================================================ \n";
        System.out.println(menu);
        System.out.println(" Choisir une option : ");
        int r = Ut.saisirEntier();
        do {
            if (r == 1) {
                jouerHumain();
            } else if (r == 2) {
                jouerOrdinateur();
            } else if (r == 3) {
                partieEstTerminee();
                resetJeu();
            }
           else {
            System.out.println("Veuillez saisir une option valide.");
           }
        } while (r < 1 || r > 3);
    }
}
