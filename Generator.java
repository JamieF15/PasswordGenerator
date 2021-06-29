import java.util.HashSet;
import java.util.Random;

public class Generator {

    static Random random = new Random();

    private static final char[] UPPERCASELETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] LOWERCASELETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] NUMBERS = "0123456789".toCharArray();
    private static final char[] SYMBOLS = "!£$%^&*()_+-=[]{};:'@#~,.|/?".toCharArray();
    private static final char[] AMBIGUOUSCHARS = "lI".toCharArray();

    public static StringBuilder password = new StringBuilder();

    //generates a password based on a length and its desired attributes
    public static void generatePassword(int passwordLength) {

        //clear the string builder
        password.delete(0, password.length());

        //switch on the determination of the password anatomy
        switch (determinePasswordAnatomy()) {
            case "Uppers" -> assemblePassword(passwordLength, UPPERCASELETTERS);
            case "Lowers" -> assemblePassword(passwordLength, LOWERCASELETTERS);
            case "Numbers" -> assemblePassword(passwordLength, NUMBERS);
            case "Symbols" -> assemblePassword(passwordLength, SYMBOLS);
            case "SymbolsNumbers" -> assemblePassword(passwordLength, concatenatePools(NUMBERS, SYMBOLS));
            case "UppersSymbols" -> assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, SYMBOLS));
            case "UppersNumbers" -> assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, NUMBERS));
            case "LowersNumbers" -> assemblePassword(passwordLength, concatenatePools(LOWERCASELETTERS, NUMBERS));
            case "LowersSymbols" -> assemblePassword(passwordLength, concatenatePools(LOWERCASELETTERS, SYMBOLS));
            case "UppersLowers" -> assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, LOWERCASELETTERS));
            case "UppersLowersSymbols" -> assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, concatenatePools(LOWERCASELETTERS, SYMBOLS)));
            case "UppersLowersNumbers" -> assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, concatenatePools(LOWERCASELETTERS, NUMBERS)));
            case "LowersSymbolsNumbers" -> assemblePassword(passwordLength, concatenatePools(LOWERCASELETTERS, concatenatePools(NUMBERS, SYMBOLS)));
            case "UppersSymbolsNumbers" -> assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, concatenatePools(SYMBOLS, NUMBERS)));
            case "UppersLowersSymbolsNumbers" -> assemblePassword(passwordLength, concatenatePools(concatenatePools(UPPERCASELETTERS, LOWERCASELETTERS), concatenatePools(NUMBERS, SYMBOLS)));
        }

        //if the password does not meet common criteria or the parameters set, re-generate one
        if (!passwordIsValid()) {
            //recur the method to generate a password
            generatePassword(passwordLength);
        }
    }

    //checks if the password contains enough unique characters
    static boolean passwordHasEnoughUniqueChars() {

        //hashset to store the unique characters
        HashSet uniqueChars = new HashSet();

        //this formula states that the password needs to contain at least around 25% unique characters
        double numberOfUniqueCharsNeeded = Math.ceil(password.length() / 4.0);

        //loop through the password and add each
        for (int i = 0; i < password.length(); i++) {

            uniqueChars.add(password.charAt(i));
        }

         return uniqueChars.size() >= numberOfUniqueCharsNeeded;
    }

    //checks if the password is valid based on the desired parameters
    static boolean passwordIsValid() {

        return !passwordHasAmbiguousChar() && passwordHasEnoughUniqueChars();
    }

    //checks if the password has an ambiguous char
    static boolean passwordHasAmbiguousChar() {

        //check if the 'easy input' checkbox is checked
        if (FrameContents.getCheckBoxes()[4].isSelected()) {

            //loop through the password
            for (int i = 0; i < password.length(); i++) {

                //loop through the ambiguous chars array
                for (char ambiguousChar : AMBIGUOUSCHARS) {

                    //check if any element of the password has is an ambiguous char
                    if (password.charAt(i) == ambiguousChar) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    //adds two pools of char arrays together
    static char[] concatenatePools(char[] pool1, char[] pool2) {

        //add the two pools together
        String newPool = String.valueOf(pool1) + String.valueOf(pool2);

        return newPool.toCharArray();
    }

    //assembles a password
   private static void assemblePassword(int length, char[] charPool) {

        for (int i = 0; i < length; i++) {
            password.append(getRandomChar(charPool));
        }
    }

    //returns the password stringbuilder as a string
    public String getPassword() {
        return password.toString();
    }

    //generates a random integer
    static int getRandomInt(int max) {
        return random.nextInt(max);
    }

    //generates a random char from a given array of chars
    static char getRandomChar(char[] charPool) {

        //generate a random char for the length of the password
        return charPool[getRandomInt(charPool.length)];
    }

    //works out the characteristics of the password to generate
    public static String determinePasswordAnatomy() {

        //instantiate a string builder
        StringBuilder passwordAttributes = new StringBuilder();

        //loop through the checkboxes
        for (int i = 0; i < FrameContents.getCheckBoxes().length; i++) {
            //0 upper
            //1 lower
            //2 easy
            //3 numbers
            //4 symbols

            //if the check box is selected, determine what one is and append it to the stringbuilder
            if (FrameContents.getCheckBoxes()[i].isSelected()) {

                switch (i) {
                    case 0 -> passwordAttributes.append("Uppers");
                    case 1 -> passwordAttributes.append("Lowers");
                    case 2 -> passwordAttributes.append("Symbols");
                    case 3 -> passwordAttributes.append("Numbers");
                }
            }
        }
        //return the password attributes
        return passwordAttributes.toString();
    }
}