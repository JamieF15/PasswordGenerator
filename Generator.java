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

                for(int i = 0; i <= passwordLength; i++) {
                    password.append(getRandomUppercaseLetter(passwordLength));
                }
                break;
        }
    }

    public String getPassword(){
        return password.toString();
    }

    char getRandomUppercaseLetter(int passwordLength){

        for(int i = 0; i < passwordLength; i++) {
            return UPPERCASELETTERS[random.nextInt(UPPERCASELETTERS.length)];
        }

        return '0';
    }
    public String determinePasswordAnatomy(boolean includeUppercaseLetters,
                                           boolean includeLowercaseLetters,
                                           boolean includeSpecialCharacters,
                                           boolean includeEasyInput){

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
