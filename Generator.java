import java.util.HashSet;
import java.util.Random;

public class Generator {

    Random random = new Random();

    private final char[] UPPERCASELETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] LOWERCASELETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] NUMBERS = "0123456789".toCharArray();
    private final char[] SYMBOLS = "(@?><~|+_-=#{]}{*&^%$£!)".toCharArray();
    private final char[] AMBIGUOUSCHARS = "lI".toCharArray();

    public static StringBuilder password = new StringBuilder();

    //generates a password based on a length and its desired attributes
    public void generatePassword(int passwordLength) {

        //clear the string builder
        password.delete(0, password.length());

        //switch on the determination of the password anatomy
        switch (determinePasswordAnatomy()) {

            case "Upper":
                assemblePassword(passwordLength, UPPERCASELETTERS);
                break;

            case "Lower":
                assemblePassword(passwordLength, LOWERCASELETTERS);
                break;

            case "Numbers":
                assemblePassword(passwordLength, NUMBERS);
                break;

            case "Symbols":
                assemblePassword(passwordLength, SYMBOLS);
                break;

            case "NumbersSymbols":
                assemblePassword(passwordLength, concatenatePools(NUMBERS, SYMBOLS));
                break;

            case "UpperSymbols":
                assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, SYMBOLS));
                break;

            case "UpperNumbers":
                assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, NUMBERS));
                break;

            case "LowerNumbers":
                assemblePassword(passwordLength, concatenatePools(LOWERCASELETTERS, NUMBERS));
                break;

            case "LowerSymbols":
                assemblePassword(passwordLength, concatenatePools(LOWERCASELETTERS, SYMBOLS));
                break;

            case "UpperLower":
                assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, LOWERCASELETTERS));
                break;

            case "UpperLowerSymbols":
                assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, concatenatePools(LOWERCASELETTERS, SYMBOLS)));
                break;

            case "UpperLowerNumbers":
                assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, concatenatePools(LOWERCASELETTERS, NUMBERS)));
                break;

            case "LowerSymbolsNumbers":
                assemblePassword(passwordLength, concatenatePools(LOWERCASELETTERS, concatenatePools(NUMBERS, SYMBOLS)));
                break;

            case "UpperSymbolsNumbers":
                assemblePassword(passwordLength, concatenatePools(UPPERCASELETTERS, concatenatePools(SYMBOLS, NUMBERS)));
                break;

            case "UpperLowerSymbolsNumbers":
                assemblePassword(passwordLength, concatenatePools(concatenatePools(UPPERCASELETTERS, LOWERCASELETTERS),
                        concatenatePools(NUMBERS, SYMBOLS)));
                break;
        }

        //if the password does not meet common criteria or the parameters set, re-generate one
        if (!passwordIsValid()) {
            generatePassword(passwordLength);
        }
    }

    boolean passwordHasEnoughUniqueChars() {

        HashSet uniqueChars = new HashSet();

        //this formula states that the password needs to contain at least around 30% unique characters
        double numberOfUniqueCharsNeeded = Math.ceil(password.length() / 3);

        for (int i = 0; i < password.length(); i++) {

            uniqueChars.add(password.charAt(i));
        }
        if (uniqueChars.size() >= numberOfUniqueCharsNeeded) {
            return true;
        } else {
            System.out.println("bad");
            password.delete(0, password.length());
            return false;
        }
    }

    boolean passwordIsValid() {

        if (!passwordHasAmbiguousChar() && passwordHasEnoughUniqueChars()) {
            return true;
        } else {
            return false;
        }
    }

    //checks if the password has an ambiguous char
    boolean passwordHasAmbiguousChar() {

        //check if the 'easy input' checkbox is checked
        if (FrameContents.getCheckBoxes()[4].isSelected()) {

            //loop through the password
            for (int i = 0; i < password.length(); i++) {

                //loop through the ambiguous chars array
                for (int j = 0; j < AMBIGUOUSCHARS.length; j++) {

                    //check if any element of the password has is an ambiguous char
                    if (password.charAt(i) == AMBIGUOUSCHARS[j]) {

                        return true;
                    }
                }
            }
            // return false;
        }
        return false;
    }

    //adds two pools of char arrays together
    char[] concatenatePools(char[] pool1, char[] pool2) {

        //the new pool of chars
        StringBuilder newPool = new StringBuilder();

        //add the first pool to the new pool
        newPool.append(pool1);

        //add the second pool to the new pool
        newPool.append(pool2);

        //return the new pool as a char array
        return newPool.toString().toCharArray();
    }

    //assembles a password
    void assemblePassword(int length, char[] charPool) {

        for (int i = 0; i < length; i++) {
            password.append(getRandomChar(length, charPool));
        }
    }

    //returns the password stringbuilder as a string
    public String getPassword() {
        return password.toString();
    }

    //generates a random integer
    int getRandomInt(int max) {
        return random.nextInt(max);
    }

    //generates a random char from a given array of chars
    char getRandomChar(int passwordLength, char[] charPool) {

        //generate a random char for the length of the password
        for (int i = 0; i < passwordLength - 1; i++) {
            return charPool[getRandomInt(charPool.length)];
        }

        //fixes a bug where if a password of length 1 is selected, it returns this for some reason
        return charPool[getRandomInt(charPool.length)];
    }

    //works out the characteristics of the password to generate
    public String determinePasswordAnatomy() {

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

                    case 0:
                        passwordAttributes.append("Upper");
                        break;

                    case 1:
                        passwordAttributes.append("Lower");
                        break;

                    case 2:
                        passwordAttributes.append("Symbols");
                        break;

                    case 3:
                        passwordAttributes.append("Numbers");
                        break;
                }
            }
        }
        //return the password attributes
        return passwordAttributes.toString();
    }
}