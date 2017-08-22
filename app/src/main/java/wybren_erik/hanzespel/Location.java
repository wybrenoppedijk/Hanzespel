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
    TURKU("Turku"),
    SEA("Zee");

    private String locName;

    Location(String visibleName) {
        this.locName = visibleName;
    }

    public static Location fromString(String name) {
        switch (name.toLowerCase()) {
            case "kampen":
                return KAMPEN;
            case "bergen":
                return BERGEN;
            case "lübeck":
                return LUBECK;
            case "stralsund":
                return STRALSUND;
            case "riga":
                return RIGA;
            case "tallin":
                return TALLIN;
            case "visby":
                return VISBY;
            case "aalborg":
                return AALBORG;
            case "stockholm":
                return STOCKHOLM;
            case "danzig":
                return DANZIG;
            case "turku":
                return TURKU;
            case "zee":
                return SEA;
            default:
                return KAMPEN;
        }
    }

    public ProductEnum getProduct() {
        switch (this) {
            case KAMPEN:
                return ProductEnum.BIER;
            case BERGEN:
                return ProductEnum.STOKVIS;
            case LUBECK:
                return ProductEnum.ZOUT;
            case STRALSUND:
                return ProductEnum.VATEN;
            case RIGA:
                return ProductEnum.WAS;
            case TALLIN:
                return ProductEnum.BONT;
            case VISBY:
                return ProductEnum.LAKEN;
            case AALBORG:
                return ProductEnum.VLEES;
            case STOCKHOLM:
                return ProductEnum.IJZER;
            case DANZIG:
                return ProductEnum.GRAAN;
            case TURKU:
                return ProductEnum.HOUT;
        }
        return ProductEnum.BIER; // Shouldn't happen
    }

    public String toString() {
        return locName;
    }

}
