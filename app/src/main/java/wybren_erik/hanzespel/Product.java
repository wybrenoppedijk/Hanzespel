package wybren_erik.hanzespel;

public enum Product {
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

    Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

