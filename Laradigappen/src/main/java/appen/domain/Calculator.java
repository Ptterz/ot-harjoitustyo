package appen.domain;

public class Calculator {

    /**
     * A recursive method to calculate the value of a given formula.
     *
     * @param formula The formula to be calculated
     * @return Return the value of the given formula
     * @since 1.0
     */
    public long calculate(String formula) {

        if (formula.contains("(")) {
            int lio = formula.lastIndexOf("(");
            int io = formula.indexOf(")", lio);

            return calculate(formula.substring(0, lio) + calculate(formula.substring(lio + 1, io)) + formula.substring(io + 1));
        } else if (formula.contains("+")) {
            String[] plus = formula.split("\\+");
            long equ = 0;
            for (int i = 0; i < plus.length; i++) {
                equ += calculate(plus[i]);
            }
            return equ;
        } else if (formula.contains("-")) {
            String[] minus = formula.split("\\-");
            long sub = calculate(minus[0]);
            for (int i = 1; i < minus.length; i++) {
                sub -= calculate(minus[i]);
            }
            return sub;
        } else if (formula.contains("/")) {
            String[] div = formula.split("\\/");
            long divide = calculate(div[0]);
            for (int i = 1; i < div.length; i++) {
                divide = Math.round((double) divide / calculate(div[i]));
            }
            return divide;
        } else if (formula.contains("*")) {
            String[] multi = formula.split("\\*");
            long multiply = 1;
            for (int i = 0; i < multi.length; i++) {
                multiply *= Long.parseLong(multi[i]);
            }
            return multiply;
        } else {
            return Long.parseLong(formula);
        }
    }
}
