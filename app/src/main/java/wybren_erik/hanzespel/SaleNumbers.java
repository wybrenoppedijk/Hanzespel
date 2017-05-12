package wybren_erik.hanzespel;

class SaleNumbers {

    double getFactor(City c, Product p) {
        switch(c.getName()) {
            case "Kampen":
                switch(p) {
                    case BIER:
                        return 0.8;
                    case STOKVIS:
                        return 1.0;
                    case ZOUT:
                        return 1.3;
                    case VATEN:
                        return 1.4;
                    case WAS:
                        return 1.0;
                    case BONT:
                        return 1.2;
                    case LAKEN:
                        return 1.4;
                    case VLEES:
                        return 1.0;
                    case IJZER:
                        return 1.4;
                    case GRAAN:
                        return 1;
                    case HOUT:
                        return 1.3;
                    // No default on purpose
                }
                break;
            case "Bergen":
                switch(p) {
                    case BIER:
                        return 1.4;
                    case STOKVIS:
                        return 0.8;
                    case ZOUT:
                        return 1.1;
                    case VATEN:
                        return 1.2;
                    case WAS:
                        return 1.3;
                    case BONT:
                        return 1.7;
                    case LAKEN:
                        return 1.2;
                    case VLEES:
                        return 1.2;
                    case IJZER:
                        return 1.6;
                    case GRAAN:
                        return 1.5;
                    case HOUT:
                        return 1.1;
                    // No default on purpose
                }
                break;
            case "Lübeck":
                switch(p) {
                    case BIER:
                        return 1.4;
                    case STOKVIS:
                        return 1.2;
                    case ZOUT:
                        return 0.8;
                    case VATEN:
                        return 1.5;
                    case WAS:
                        return 1.4;
                    case BONT:
                        return 1.4;
                    case LAKEN:
                        return 1.5;
                    case VLEES:
                        return 1.4;
                    case IJZER:
                        return 1.3;
                    case GRAAN:
                        return 1.2;
                    case HOUT:
                        return 1.2;
                    // No default on purpose
                }
                break;
            case "Stralsund":
                switch(p) {
                    case BIER:
                        return 1.5;
                    case STOKVIS:
                        return 1.3;
                    case ZOUT:
                        return 1.2;
                    case VATEN:
                        return 0.8;
                    case WAS:
                        return 1.3;
                    case BONT:
                        return 1.3;
                    case LAKEN:
                        return 1.2;
                    case VLEES:
                        return 1.5;
                    case IJZER:
                        return 1.2;
                    case GRAAN:
                        return 1.2;
                    case HOUT:
                        return 1.3;
                    // No default on purpose
                }
                break;
            case "Riga":
                switch(p) {
                    case BIER:
                        return 1.8;
                    case STOKVIS:
                        return 1.3;
                    case ZOUT:
                        return 1.2;
                    case VATEN:
                        return 1.3;
                    case WAS:
                        return 0.8;
                    case BONT:
                        return 1.3;
                    case LAKEN:
                        return 1.3;
                    case VLEES:
                        return 1.4;
                    case IJZER:
                        return 1.3;
                    case GRAAN:
                        return 1.2;
                    case HOUT:
                        return 1.3;
                    // No default on purpose
                }
                break;
            case "Tallin":
                switch(p) {
                    case BIER:
                        return 2.0;
                    case STOKVIS:
                        return 1.6;
                    case ZOUT:
                        return 1.3;
                    case VATEN:
                        return 1.2;
                    case WAS:
                        return 1.2;
                    case BONT:
                        return 0.8;
                    case LAKEN:
                        return 1.4;
                    case VLEES:
                        return 1.6;
                    case IJZER:
                        return 1.4;
                    case GRAAN:
                        return 1.3;
                    case HOUT:
                        return 1.4;
                    // No default on purpose
                }
                break;
            case "Visby":
                switch(p) {
                    case BIER:
                        return 1.7;
                    case STOKVIS:
                        return 1.2;
                    case ZOUT:
                        return 1.4;
                    case VATEN:
                        return 1.2;
                    case WAS:
                        return 1.3;
                    case BONT:
                        return 1.4;
                    case LAKEN:
                        return 0.8;
                    case VLEES:
                        return 1.2;
                    case IJZER:
                        return 1.6;
                    case GRAAN:
                        return 1.4;
                    case HOUT:
                        return 1.1;
                    // No default on purpose
                }
                break;
            case "Aalborg":
                switch(p) {
                    case BIER:
                        return 1.3;
                    case STOKVIS:
                        return 1.2;
                    case ZOUT:
                        return 1.1;
                    case VATEN:
                        return 1.3;
                    case WAS:
                        return 1.2;
                    case BONT:
                        return 1.1;
                    case LAKEN:
                        return 1.4;
                    case VLEES:
                        return 0.8;
                    case IJZER:
                        return 1.3;
                    case GRAAN:
                        return 1.4;
                    case HOUT:
                        return 1.1;
                    // No default on purpose
                }
                break;
            case "Stockholm":
                switch(p) {
                    case BIER:
                        return 1.8;
                    case STOKVIS:
                        return 1.4;
                    case ZOUT:
                        return 1.2;
                    case VATEN:
                        return 1.4;
                    case WAS:
                        return 1.4;
                    case BONT:
                        return 1.1;
                    case LAKEN:
                        return 1.6;
                    case VLEES:
                        return 1.3;
                    case IJZER:
                        return 0.8;
                    case GRAAN:
                        return 1.5;
                    case HOUT:
                        return 1.2;
                    // No default on purpose
                }
                break;
            case "Danzig":
                switch(p) {
                    case BIER:
                        return 1.6;
                    case STOKVIS:
                        return 1.3;
                    case ZOUT:
                        return 1.3;
                    case VATEN:
                        return 1.4;
                    case WAS:
                        return 1.4;
                    case BONT:
                        return 1.2;
                    case LAKEN:
                        return 1.7;
                    case VLEES:
                        return 1.2;
                    case IJZER:
                        return 1.2;
                    case GRAAN:
                        return 0.8;
                    case HOUT:
                        return 1.4;
                    // No default on purpose
                }
                break;
            case "Turku":
                switch(p) {
                    case BIER:
                        return 2.0;
                    case STOKVIS:
                        return 1.5;
                    case ZOUT:
                        return 1.4;
                    case VATEN:
                        return 1.3;
                    case WAS:
                        return 1.5;
                    case BONT:
                        return 1.4;
                    case LAKEN:
                        return 1.3;
                    case VLEES:
                        return 1.3;
                    case IJZER:
                        return 1.4;
                    case GRAAN:
                        return 1.6;
                    case HOUT:
                        return 0.8;
                    // No default on purpose
                }
                break;
            default: // Shouldn't happen
                return 1.0;
        }
        return 1.0; // Shouldn't happen
    }

}
