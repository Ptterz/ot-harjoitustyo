package appen.domain;

public class Calculator {

    public long laske(String lasku) {

        if (lasku.contains("(")) {
            int lio = lasku.lastIndexOf("(");
            int io = lasku.indexOf(")", lio);

            return laske(lasku.substring(0, lio) + laske(lasku.substring(lio + 1, io)) + lasku.substring(io + 1));
        } else if (lasku.contains("+")) {
            String[] plussa = lasku.split("\\+");
            long tulos = 0;
            for (int i = 0; i < plussa.length; i++) {
                tulos += laske(plussa[i]);
            }
            return tulos;
        } else if (lasku.contains("-")) {
            String[] miinus = lasku.split("\\-");
            long erotus = laske(miinus[0]);
            for (int i = 1; i < miinus.length; i++) {
                erotus -= laske(miinus[i]);
            }
            return erotus;
        } else if (lasku.contains("/")) {
            String[] jako = lasku.split("\\/");
            long osamaara = laske(jako[0]);
            for (int i = 1; i < jako.length; i++) {
                osamaara = Math.round((double) osamaara / laske(jako[i]));
            }
            return osamaara;
        } else if (lasku.contains("*")) {
            String[] kerto = lasku.split("\\*");
            long kertoma = 1;
            for (int i = 0; i < kerto.length; i++) {
                kertoma *= Long.parseLong(kerto[i]);
            }
            return kertoma;
        } else {
            return Long.parseLong(lasku);
        }
    }
}
