package wybren_erik.hanzespel;

public enum Location {

    KAMPEN("Kampen"),
    BERGEN("Bergen"),
    LUBECK("Lübeck"),
    STRALSUND("Stralsund"),
    RIGA("Riga"),
    TALLIN("Tallin"),
    VISBY("Visby"),
    AALBORG("Aalborg"),
    STOCKHOLM("Stockholm"),
    DANZIG("Danzig"),
    TURKU("Turku");

    private String locName;

    Location(String visibleName) {
        this.locName = visibleName;
    }

    public Product getProduct() {
        switch (this) {
            case KAMPEN:
                return Product.BIER;
            case BERGEN:
                return Product.STOKVIS;
            case LUBECK:
                return Product.ZOUT;
            case STRALSUND:
                return Product.VATEN;
            case RIGA:
                return Product.WAS;
            case TALLIN:
                return Product.BONT;
            case VISBY:
                return Product.LAKEN;
            case AALBORG:
                return Product.VLEES;
            case STOCKHOLM:
                return Product.IJZER;
            case DANZIG:
                return Product.GRAAN;
            case TURKU:
                return Product.HOUT;
        }
        return Product.BIER; // Shouldn't happen
    }

    public String toString() {
        return locName;
    }

}
