package Assignments;

public class LibraryISBNNormalizerValidator {

    static String normalizeCode(String raw) {

        return raw.trim().toUpperCase();
    }

    static void validateAndFormat(String code) {

        code = normalizeCode(code);

        if (code.length() != 13) {
            System.out.println("Invalid: code must be 13 characters");
            return;
        }

        boolean valid = true;

        // Check first 3 characters are letters
        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(code.charAt(i))) {
                valid = false;
            }
        }

        // Check remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {

            if (!Character.isDigit(code.charAt(i))) {
                valid = false;
            }
        }

        if (!valid) {
            System.out.println("Invalid: publisher code or ISBN digits are incorrect");
            return;
        }

        String publisher = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        System.out.println("[" + publisher + "] YEAR: " + year
                + " | CATALOG: " + catalog);
    }

    public static void main(String[] args) {

        String raw = " pen2026004251 ";

        validateAndFormat(raw);
    }
}