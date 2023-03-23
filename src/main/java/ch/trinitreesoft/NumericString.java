package ch.trinitreesoft;

import java.util.regex.Pattern;

public class NumericString {
    private NumericString() {
    }

    public static boolean isDouble(String string) {
        final String Digits = "(\\d+)";
        final String HexDigits = "(\\p{XDigit}+)";
        // an exponent is 'e' or 'E' followed by an optionally
        // signed decimal integer.
        final String Exp = "[eE][+-]?" + Digits;
        final String fpRegex =
                "[\\x00-\\x20]*" + // Optional leading "whitespace"
                        "[+-]?(" +         // Optional sign character
                        "NaN|" +           // "NaN" string
                        "Infinity|" +      // "Infinity" string

                        // A decimal floating-point string representing a finite positive
                        // number without a leading sign has at most five basic pieces:
                        // Digits . Digits ExponentPart FloatTypeSuffix
                        //
                        // Since this method allows integer-only strings as input
                        // in addition to strings of floating-point literals, the
                        // two sub-patterns below are simplifications of the grammar
                        // productions from the Java Language Specification, 2nd
                        // edition, section 3.10.2.

                        // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
                        "(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|" +

                        // . Digits ExponentPart_opt FloatTypeSuffix_opt
                        "(\\.(" + Digits + ")(" + Exp + ")?)|" +

                        // Hexadecimal strings
                        "((" +
                        // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
                        "(0[xX]" + HexDigits + "(\\.)?)|" +

                        // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
                        "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

                        ")[pP][+-]?" + Digits + "))" +
                        "[fFdD]?))" +
                        "[\\x00-\\x20]*";// Optional trailing "whitespace"

        String candidate = string.replace(',', '.');
        return Pattern.matches(fpRegex, candidate);
    }

    public static boolean isInt(String string) {
        if (isDouble(string)) {
            double d = parseAsDouble(string);
            return d == (Math.floor(d)) && !Double.isInfinite(d);
        } else {
            return false;
        }
    }

    public static double parseAsDouble(String string) {
        String d = string.replace(',', '.').strip();
        return Double.parseDouble(d);
    }

    public static int parseAsInt(String string) {
        return Integer.parseInt(string.strip());
    }
}


