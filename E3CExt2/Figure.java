package E3CExt2;

public enum Figure {
    Ovale("O"),

    Triangle("T"),

    Losange("L");

    private String abreviation;

    private Figure(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getAbreviation() {
        return this.abreviation;
    }

    /**
     * Représente la figure (forme) d'une Carte : Ovale (O), Rectangle (R), Triangle (T), etc.
     */
    @Override
    public String toString() {
        return this.abreviation;
    }
}

