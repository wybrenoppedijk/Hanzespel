package wybren_erik.hanzespel;

public enum Location {

    KAMPEN,
    BERGEN,
    LUBECK,
    STRALSUND,
    RIGA,
    TALLIN,
    VISBY,
    AALBORG,
    STOCKHOLM,
    DANZIG,
    TURKU;

    Location() {
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

}
