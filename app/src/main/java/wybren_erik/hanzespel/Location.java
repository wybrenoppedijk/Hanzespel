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

    /**
     * Get the travel time to another location in milliseconds.
     *
     * @param to The location to go to.
     * @return The travel time in ms.
     */
    public int getTravelTime(Location to) {
        return 0; // TODO
    }

}
