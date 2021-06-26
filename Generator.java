import java.util.Random;

public class Generator {

    Random random = new Random();

    private final char[] UPPERCASELETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] LOWERCASELETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] NUMBERS = "0123456789".toCharArray();
    private final char[] SYMBOLS = "(@?><~|+_-=#{]}{*&^%$£!)".toCharArray();

    public static StringBuilder password = new StringBuilder();

    public void generatePassword(boolean includeUppercaseLetters,
                                 boolean includeLowercaseLetters,
                                 boolean includeSpecialCharacters,
                                 boolean includeEasyInput,
                                 int passwordLength) {

        password.delete(0, password.length());

        switch (determinePasswordAnatomy(
                includeUppercaseLetters,
                includeLowercaseLetters,
                includeSpecialCharacters,
                includeEasyInput)){

            case "UppercaseOnly":

                for(int i = 0; i <= passwordLength - 1; i++) {
                    password.append(getRandomChar(passwordLength, UPPERCASELETTERS));
                }
                break;
        }
    }

    public String getPassword(){
        return password.toString();
    }

    int getRandomInt(char[] charPool){
        return random.nextInt(charPool.length);
    }

    char getRandomChar(int passwordLength, char[] charPool){

        for(int i = 0; i < passwordLength - 1; i++) {
            return charPool[getRandomInt(charPool)];
        }

        return '0';
    }
    public String determinePasswordAnatomy(boolean includeUppercaseLetters,
                                           boolean includeLowercaseLetters,
                                           boolean includeSpecialCharacters,
                                           boolean includeEasyInput){


        for(int i = 0; i < FrameContents.checkBoxes.length; i++){

        s
        }

        if (includeUppercaseLetters){
            return "UppercaseOnly";
        }else if (includeUppercaseLetters && includeLowercaseLetters){
            return "UpperAndLowerCaseOnly";
        }else if(includeUppercaseLetters && includeLowercaseLetters){
            return "UppercaseAndLowercaseOnly";
        }else if(includeUppercaseLetters && includeEasyInput) {
            return "UppercaseAndEasyOnly";
        }else if (includeUppercaseLetters && includeLowercaseLetters && includeSpecialCharacters){
            return "UpperAndLowerAndSpecialOnly";
        } else if (includeUppercaseLetters && includeLowercaseLetters && includeSpecialCharacters && includeEasyInput){
            return "UpperAndLowerAndSpecialAndEasyInputOnly";
        }else if(includeLowercaseLetters){
            return "LowerCaseOnly";
        }else if(includeLowercaseLetters && includeSpecialCharacters){
            return "LowerCaseAndSpecialOnly";
        }else if(includeSpecialCharacters && includeEasyInput){
            return "SpecialAndEasyOnly";
        }

        return null;
    }
}
