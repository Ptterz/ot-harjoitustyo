package appen.domain;

public class Calculator {

    public long laske(String lasku) {

        long kertoma = 1;
        String lauseke = lasku;

        if (lasku.contains("(")) {
            int lio = lauseke.lastIndexOf("(");
            int io = lauseke.indexOf(")", lio);

            return laske(lauseke.substring(0, lio) + laske(lauseke.substring(lio + 1, io)) + lauseke.substring(io + 1));
        }

        if (lauseke.contains("+")) {
            String[] plussa = lauseke.split("\\+");
            long tulos = 0;
            for (int i = 0; i < plussa.length; i++) {
                tulos += laske(plussa[i]);
            }
            return tulos;
        }

        if (lauseke.contains("-")) {
            String[] miinus = lauseke.split("\\-");
            long tulos = 0;
            for (int i = 0; i < miinus.length; i++) {
                tulos -= laske(miinus[i]);
            }
            return tulos;
        }

        if (lauseke.contains("/")) {
            String[] jako = lauseke.split("\\/");
            long osamaara = laske(jako[0]);
            for (int i = 1; i < jako.length; i++) {
                osamaara = Math.round((double) osamaara / laske(jako[i]));
            }
            return osamaara;
        }

        if (lauseke.contains("*")) {
            String[] kerto = lauseke.split("\\*");
            for (int i = 0; i < kerto.length; i++) {
                kertoma *= Long.parseLong(kerto[i]);
            }
            return kertoma;
        }

        return Long.parseLong(lauseke);
    }
}
