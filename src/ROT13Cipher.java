public class ROT13Cipher {
    public static String encrypt(String s)
    {
        String result = "";

        for (int i = 0; i < s.length(); i++)
        {
            char currentChar = s.charAt(i);
            if ((currentChar >= 65 && currentChar <= 90)) {

                currentChar = (char) ((int)currentChar + 13);

                if (currentChar > 90) {
                    currentChar = (char) ((int) currentChar - 90 + 65 - 1);
                }

            }
            else if((currentChar >= 97 && currentChar <= 122))
            {
                currentChar = (char) ((int)currentChar + 13);

                if (currentChar > 122) {
                    currentChar = (char) ((int) currentChar - 122 + 97 - 1);
                }

            }
            result+= currentChar;
        }
        return result;
    };
    public static String decrypt (String s){
        String result = "";

        for (int i = 0; i < s.length(); i++)
        {
            char currentChar = s.charAt(i);
            if(currentChar <65 || currentChar>90 && currentChar<97 || currentChar>122)
            {
                result+= currentChar;
            }
            else {

                currentChar = (char) ((int)currentChar - 13);

                if (currentChar <65)
                {
                    currentChar = (char) ((int) 90 - (65 - currentChar) + 1);
                }

                else if( currentChar < 97 && currentChar > 83)
                {
                    currentChar = (char) ((int) 122 - (97 - currentChar) + 1);
                }

                result+=currentChar;

            }

        }
        return result;
    };
}
