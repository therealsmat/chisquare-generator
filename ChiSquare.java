import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChiSquare {
    private Map<Character, Double> englishCharFrequencies = new HashMap<Character, Double>();

    private double []frequecies = new double[]{
        0.127,0.091,0.082,0.075,0.070,0.067,0.063,0.061,0.06,0.043,0.04,0.028,0.028,0.024,0.024,0.022,0.02,0.02,0.019,0.015,0.01,0.008,0.0015,0.0015,0.001,0.0007
    };
    private char[] alphabets = new char[]{'E','T','A','O','I','N','S','H','R','D','L','C','U','M','W','F','G','Y','P','B','V','K','J','X','Q','Z'};

    private String string;
    private Map<Character, Integer> characterCounts;
    private int key;
    private int strLength;

    ChiSquare() {
        this.buildEnglishCharacterFrequencies();
    }

    // build the english character frequencies list
    private void buildEnglishCharacterFrequencies()
    {
        for(int x = 0; x < 26; x++) {
            this.englishCharFrequencies.put(this.alphabets[x], this.frequecies[x]);
        }
    }

    //return the english character frequencies list
    public Map<Character, Double> getEnglishCharFrequencies()
    {
        return this.englishCharFrequencies;
    }

    // find the chi-square value of the provided string
    public Map<String, String> findChiSquare(String string, Map<Character, Integer> characterCounts, int index)
    {
        this.string = string;
        this.characterCounts = characterCounts;
        this.key = index;
        this.strLength = this.string.length();

        return this.computeChiValues();
    }

    // compute the chi values of the string, returning a detail of the key, chi-value and string
    private Map<String, String> computeChiValues()
    {
        int strLen = this.strLength;
        Iterator it = this.characterCounts.entrySet().iterator();
        double totalChi = 0.0;

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            double alphabetFrequency = this.getEnglishCharFrequencies().get(pair.getKey());
            double frequencyInString = (strLen * alphabetFrequency);

            double numerator = ((int) (pair.getValue())) - frequencyInString;
            numerator = numerator * numerator;
            double chiValue = numerator / frequencyInString;

            totalChi += chiValue;
        }

        Map<String, String> response = new HashMap<String, String>();
        response.put("key", this.key + "");
        response.put("string", this.string);
        response.put("chi_value", String.format("%.2f", totalChi));
        return response;
    }


}
