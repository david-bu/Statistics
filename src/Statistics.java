import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

    public static ArrayList<Integer> convertToIntArray(String[] arr) throws NumberFormatException {
        ArrayList<Integer> integerArray = new ArrayList<>();
        for (String str : arr) {
            Integer integer = Integer.parseInt(str);

            integerArray.add(integer);
        }
        return integerArray;
    }

    public static HashMap<Integer, Integer> intArrayToMap(ArrayList<Integer> integerArray) {
        HashMap<Integer, Integer> integerMap = new HashMap<>(integerArray.size());
        for (int integer : integerArray) {
            int count = integerMap.getOrDefault(integer, 0) + 1;
            integerMap.put(integer, count);
        }
        return integerMap;
    }

    public static double calcMittelwert(ArrayList<Integer> integerArray) {
        return calcSum(integerArray)/(double)integerArray.size();
    }

    public static int calcSum(ArrayList<Integer> integerArray) {
        int sum = 0;
        for (int integer : integerArray) {
            sum += integer;
        }
        return sum;
    }

    public static double calcStandardabweichung(HashMap<Integer, Integer> integerMap, double mittewert, int size) {
        return Math.sqrt(calcVarianz(integerMap, mittewert, size));
    }

    public static double calcVarianz(HashMap<Integer, Integer> integerMap, double mittelwert, int size) {
        double varianz = 0;
        for (Map.Entry<Integer, Integer> entry : integerMap.entrySet()) {
            varianz += Math.pow(entry.getKey() - mittelwert, 2) * (entry.getValue() / ((double)size - 1));
        }
        return varianz;
    }

    public static void printHistogramm(HashMap<Integer, Integer> integerMap) {
        for (Map.Entry<Integer, Integer> entry : integerMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + "*".repeat(entry.getValue()));
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No input!");
            return;
        }
        ArrayList<Integer> integerArray;
        try {
            integerArray = convertToIntArray(args);
        } catch (NumberFormatException e) {
            System.out.println("No valid input! Input must be numbers.");
            return;
        }
        System.out.println("Mittelwert: " + calcMittelwert(integerArray));
        System.out.println("Summe: " + calcSum(integerArray));

        HashMap<Integer, Integer> integerMap = intArrayToMap(integerArray);
        System.out.println("Standardabweichung: " + calcStandardabweichung(integerMap, calcMittelwert(integerArray), integerArray.size()));
        System.out.println("Varianz: " + calcVarianz(integerMap, calcMittelwert(integerArray), integerArray.size()));
        System.out.println();
        printHistogramm(integerMap);
    }

}