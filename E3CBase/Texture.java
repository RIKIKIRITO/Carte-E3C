package E3CBase;

public enum Texture {
    Cuir ("C"),
    Poin ("PO"),
    Bois ("B"),
    Rayé ("R");

    private String abreviation ;
    private Texture(String a) {
        this.abreviation = a ;
    }
    public String getAbreviation() {
        return this.abreviation ;
    }
}

/**
 * Représente la texture d'une Carte : pleine , à pois...
 */


