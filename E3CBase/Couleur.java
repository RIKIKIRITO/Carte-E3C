package E3CBase;

public enum Couleur {
    Vert ("V"),
    Rouge ("R"),
    Blue ("B"),
    Purple ("P"),
    Black ("BL");

    private String abreviation ;
    private Couleur(String a) {
        this.abreviation = a ;
    }
        public String getAbreviation() {
        return this.abreviation ;
    }
    }
    /**
     * Représente la couleur d'une Carte : jaune, rouge ...
     * En plus de donner une liste énumérative des couleurs possibles,
     * cette enumération doit permettre à la méthode toString d'une Carte de réaliser un affichage en couleur.
     */


