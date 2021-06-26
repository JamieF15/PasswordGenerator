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

        //clear the string builder
        password.delete(0, password.length());

        switch (determinePasswordAnatomy(
                includeUppercaseLetters,
                includeLowercaseLetters,
                includeSpecialCharacters,
                includeEasyInput)){

            case "Upper":
                generatePassword(passwordLength, UPPERCASELETTERS);
                break;

            case "Lower":
                generatePassword(passwordLength, LOWERCASELETTERS);
                break;

            case "UpperLower":
                generatePassword(passwordLength, concatenatePools(UPPERCASELETTERS, LOWERCASELETTERS));
                break;
        }
    }

    char[] concatenatePools(char[] pool1, char[] pool2){

        StringBuilder newPool = new StringBuilder();
        newPool.append(UPPERCASELETTERS);
        newPool.append(LOWERCASELETTERS);
        return newPool.toString().toCharArray();
    }
    void generatePassword(int length, char[] charPool){

        for(int i = 0; i <= length - 1; i++) {
            password.append(getRandomChar(length, charPool));
        }

    }
    //returns the password stringbuilder
    public String getPassword(){
        return password.toString();
    }

    //
    int getRandomInt(int max){
        return random.nextInt(max);
    }

    char getRandomChar(int passwordLength, char[] charPool){

        for(int i = 0; i < passwordLength - 1; i++) {
            return charPool[getRandomInt(charPool.length)];
        }

        return '0';
    }
    public String determinePasswordAnatomy(boolean includeUppercaseLetters,
                                           boolean includeLowercaseLetters,
                                           boolean includeSpecialCharacters,
                                           boolean includeEasyInput){

        StringBuilder passwordAttributes = new StringBuilder();

        for(int i = 0; i < FrameContents.checkBoxes.length; i++){
            //0 upper
            //1 lower
            //2 symbols
            //3 easy

            if(FrameContents.checkBoxes[i].isSelected()){

                switch (i){

                    case 0:
                        passwordAttributes.append("Upper");
                        break;

                    case 1:
                        passwordAttributes.append("Lower");
                        break;

                    case 2:

                    case 3:

                        break;
                }

            }
        }

        return passwordAttributes.toString();

    }
}
