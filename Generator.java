public class Generator {

    private final char[] UPPERCASELETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] LOWERCASELETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] NUMBERS = "0123456789".toCharArray();
    private final char[] SYMBOLS = "(@?><~|+_-=#{]}{*&^%$£!)".toCharArray();

    static StringBuilder password = new StringBuilder();

    public void generatePassword(boolean includeUppercaseLetters,
                                 boolean includeLowercaseLetters,
                                 boolean includeSpecialCharacters,
                                 boolean includeEasyInput,
                                 int passWordLength) {

        switch (determinePasswordAnatomy(
                includeUppercaseLetters,
                includeLowercaseLetters,
                includeSpecialCharacters,
                includeEasyInput)){

            case "UppercaseOnly":

                break;
        }
    }

    public String determinePasswordAnatomy(boolean includeUppercaseLetters,
                                           boolean includeLowercaseLetters,
                                           boolean includeSpecialCharacters,
                                           boolean includeEasyInput){

        if (includeUppercaseLetters){
            return "UppercaseOnly";
        }else if (includeUppercaseLetters && includeLowercaseLetters){
            return "UpperAndLowerCaseOnly";
        }else if (includeUppercaseLetters && includeLowercaseLetters && includeSpecialCharacters){
            return "UpperAndLowerAndSpecialOnly";
        } else if (includeUppercaseLetters && includeLowercaseLetters && includeSpecialCharacters && includeEasyInput){
            return "UpperAndLowerAndSpecialAndEasyInputOnly";
        }

        return null;
    }
}
