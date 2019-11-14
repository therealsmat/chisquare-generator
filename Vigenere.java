import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Vigenere {
    static String string = "GRHBFNQYNFVNQABMRZNARAQLYPVUUYERZIQRNFRZYGEVYRRSQRSGAGESRGUUVIFQFBNONVUUGRABRQCFUGYRFERBANYUUPBRFRNGNBRLSBERNENOBYYRVBZBZGNLFGJHZAGGGVBVZAAPNNSFZFORRGCOERZXZVRQFYENUUHLGBBAGJRPHNEEGNGNVJJIERNRUGANBZVINEBQFZABFABFFVEPAPVAOBBEFGBUERFPRQPRAHLRECVYCVGZVBVYCVGFGRERNAQUTNBRAVYNABRPARYNGBSAVEOYGVFPBNT";

    public static void main(String[] args) {
        ChiSquare chiSquare = new ChiSquare();

        printTableHeader();

        for (int x = 0; x < 26; x++) {
            String appropriateString = getAppropriateString(x);
            Map charFrequency =  Vigenere.getUniqueCharsFromString(appropriateString);
            Map chiValue = chiSquare.findChiSquare(appropriateString, charFrequency, x);

            System.out.println(chiValue.get("key") + " \t\t\t " + chiValue.get("chi_value") + " \t\t" + chiValue.get("string"));
        }
    }

    private static Map<Character, Integer> getUniqueCharsFromString(String string)
    {
        int strLen = string.length();
        String uniqueStrings = "";
        Map<Character, Integer> charFrequencies = new HashMap<Character, Integer>();

        for(int x = 0; x < string.length(); x++) {
            char cursor = string.charAt(x);
            if (charFrequencies.containsKey(cursor)) {
                int value = charFrequencies.get(cursor);
                charFrequencies.put(cursor, value + 1);
            } else {
                charFrequencies.put(cursor, 1);
            }
        }

        return charFrequencies;
    }

    private static String getAppropriateString(int shift)
    {
        if (shift == 0) return Vigenere.string;

        char[] strArr = Vigenere.string.toCharArray();
        String newString = "";
        for (char str: strArr) {
            int ascii = ((int) str) - shift;
            if (ascii < 65) {
                int noise = 65 - ascii;
                ascii = 90 - (noise - 1);
            }
            newString += (char) ascii;
        }
        return newString;
    }

    private static void printTableHeader()
    {
        System.out.println("Key \t\t Chi Value \t\t Cipher String");
        System.out.println("---- \t\t -------- \t\t ----------------------------------");
    }
}
