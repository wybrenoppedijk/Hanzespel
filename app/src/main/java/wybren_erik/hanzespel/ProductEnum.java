package wybren_erik.hanzespel;

public enum ProductEnum {
    BIER(100),
    STOKVIS(300),
    ZOUT(150),
    VATEN(300),
    WAS(200),
    BONT(500),
    LAKEN(400),
    VLEES(250),
    IJZER(300),
    GRAAN(200),
    HOUT(100);

    private int price;

    ProductEnum(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return format(super.toString());
    }

    private String format(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}

