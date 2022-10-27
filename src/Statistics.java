import java.util.HashMap;
import java.util.Map;

public class Statistics {

    /**
     * Konvertiert ein Array aus Strings in ein Array aus Integers, falls möglich, und gibt es zurück.
     *
     * @param arr ein Array von Strings, die aus ganzen Zahlen bestehen
     * @return ein Array aus Integers
     * @throws NumberFormatException wenn ein String nicht konvertiert werden kann, nichtnumerische Zeichen enthält
     * siehe Integer.parseInt
     */
    public static int[] convertStringToIntArray(String[] arr) throws NumberFormatException {
        int[] integerArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            integerArray[i] = Integer.parseInt(arr[i]);

        return integerArray;
    }

    /**
     * Prüft jede Zahl in dem Eingabearray auf Vorkommen und zählt die Erscheinungen.
     *
     * @param numberArray Liste von Nummern, deren Anzahl innerhalb des Arrays ausgegeben werden soll
     * @return eine HashMap mit den vorkommenden Zahlen als Keys und deren Anzahl an Vorkommen als Wert
     */
    public static HashMap<Integer, Integer> intArrayToMap(int[] numberArray) {
        HashMap<Integer, Integer> valueCountMap = new HashMap<>(numberArray.length);
        for (int integer : numberArray) {
            int count = valueCountMap.getOrDefault(integer, 0) + 1;
            valueCountMap.put(integer, count);
        }
        return valueCountMap;
    }

    /**
     * Berechnet den Mittelwert aus dem Array von Integers.
     *
     * @param integerArray - Array von allen Zahlen, aus denen der Durchschnitt berechnet werden soll
     * @return den Mittelwert aller Zahlen des Eingabearrays
     */
    public static double calcAverage(int[] integerArray) {
        return calcSum(integerArray)/(double)integerArray.length;
    }

    /**
     * Berechnet die Summe aus allen Zahlen des Eingabearrays.
     *
     * @param integerArray - Array von allen Zahlen, aus denen die Summe berechnet werden soll
     * @return die Summe aller Zahlen
     */
    public static int calcSum(int[] integerArray) {
        int sum = 0;
        for (int integer : integerArray) {
            sum += integer;
        }
        return sum;
    }

    /**
     * Berechnet die Standardabweichung mithilfe der Varianz (siehe calcVarianz).
     *
     * @param valueCountMap Map aus allen verschiedenen Werten mit ihrer jeweiligen Anzahl an Vorkommen
     * @param average Mittelwert aller Werte, aus denen die Standardabweichung berechnet werden soll
     * @param size Anzahl aller Werte, auch doppelte (Summe aller CountWerte der valueCountMap)
     * @return die Standardabweichung
     */
    public static double calcStandardDeviation(HashMap<Integer, Integer> valueCountMap, double average, int size) {
        return Math.sqrt(calcVariance(valueCountMap, average, size));
    }

    /**
     * Berechnet die Varianz.
     *
     * @param valueCountMap Map aus allen verschiedenen Werten mit ihrer jeweiligen Anzahl an Vorkommen
     * @param average Mittelwert aller Werte, aus denen die Standardabweichung berechnet werden soll
     * @param size Anzahl aller Werte, auch doppelte (Summe aller CountWerte der valueCountMap)
     * @return die Varianz
     */
    public static double calcVariance(HashMap<Integer, Integer> valueCountMap, double average, int size) {
        double varianz = 0;
        for (Map.Entry<Integer, Integer> entry : valueCountMap.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();
            varianz += (value - average) * (value - average) * (count / ((double)size - 1));
        }
        return varianz;
    }

    /**
     * Gibt ein Histogramm auf der Standardkonsole aus.
     *
     * @param valueCountMap Map aus allen verschiedenen Werten mit ihrer jeweiligen Anzahl an Vorkommen
     */
    public static void printHistogram(HashMap<Integer, Integer> valueCountMap) {
        for (Map.Entry<Integer, Integer> entry : valueCountMap.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();
            System.out.println(value + ": " + "*".repeat(count));
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No input!");
            return;
        }

        int[] integerArray;
        try {
            integerArray = convertStringToIntArray(args);
        } catch (NumberFormatException e) {
            System.out.println("No valid input! Input must be numbers.\nMaybe values are too big!");
            return;
        }

        // enthält jeden verschiedenen vorkommenden Wert mit seiner Anzahl an Vorkommen
        HashMap<Integer, Integer> valueCountMap = intArrayToMap(integerArray);
        double average = calcAverage(integerArray);
        int sum = calcSum(integerArray);
        double standardDeviation = calcStandardDeviation(valueCountMap, average, integerArray.length);
        double variance = calcVariance(valueCountMap, calcAverage(integerArray), integerArray.length);

        System.out.println("Mittelwert: " + average);
        System.out.println("Summe: " + sum);
        System.out.println("Standardabweichung: " + standardDeviation);
        System.out.println("Varianz: " + variance);
        System.out.println();

        printHistogram(valueCountMap);
    }

}