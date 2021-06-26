import java.util.Random;

public class Generator {

    Random random = new Random();

    private final char[] UPPERCASELETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] LOWERCASELETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] NUMBERS = "0123456789".toCharArray();
    private final char[] SYMBOLS = "(@?><~|+_-=#{]}{*&^%$£!)".toCharArray();
    private final  char[] AMBIGUOUSCHARS = "lI".toCharArray();

    public static StringBuilder password = new StringBuilder();

    public void generatePassword(int passwordLength) {

        //clear the string builder
        password.delete(0, password.length());

        if (FrameContents.getCheckBoxes()[4].isSelected() && !passwordHasAmbiguousChar()) {

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
        }else{
            System.out.println("NEW");
          //  generatePassword(passwordLength);
        }
    }

    boolean passwordHasAmbiguousChar(){

        for(int i = 0; i < password.length(); i++){

            for(int j = 0; j < AMBIGUOUSCHARS.length; j++){

                if(password.charAt(i) == AMBIGUOUSCHARS[j]){

                    return true;
                }
            }
        }
        return  false;
    }

    char[] concatenatePools(char[] pool1, char[] pool2){

        StringBuilder newPool = new StringBuilder();
        newPool.append(pool1);
        newPool.append(pool2);
        return newPool.toString().toCharArray();
    }

    void assemblePassword(int length, char[] charPool){

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

        //fixes a bug where if a password of length 1 is selected, it returns this for some reason
        return charPool[getRandomInt(charPool.length)];
    }

    public String determinePasswordAnatomy(){

        StringBuilder passwordAttributes = new StringBuilder();

        for(int i = 0; i < FrameContents.getCheckBoxes().length; i++){
            //0 upper
            //1 lower
            //2 easy
            //3 numbers
            //4 symbols

            if(FrameContents.getCheckBoxes()[i].isSelected()){

                switch (i){

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
        return passwordAttributes.toString();
    }
}
