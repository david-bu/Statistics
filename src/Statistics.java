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
    public static int[] convertToIntArray(String[] arr) throws NumberFormatException {
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
    public static double calcMittelwert(int[] integerArray) {
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
     * @param mittelwert Mittelwert aller Werte, aus denen die Standardabweichung berechnet werden soll
     * @param size Anzahl aller Werte, auch doppelte (Summe aller CountWerte der valueCountMap)
     * @return die Standardabweichung
     */
    public static double calcStandardabweichung(HashMap<Integer, Integer> valueCountMap, double mittelwert, int size) {
        return Math.sqrt(calcVarianz(valueCountMap, mittelwert, size));
    }

    /**
     * Berechnet die Varianz.
     *
     * @param valueCountMap Map aus allen verschiedenen Werten mit ihrer jeweiligen Anzahl an Vorkommen
     * @param mittelwert Mittelwert aller Werte, aus denen die Standardabweichung berechnet werden soll
     * @param size Anzahl aller Werte, auch doppelte (Summe aller CountWerte der valueCountMap)
     * @return die Varianz
     */
    public static double calcVarianz(HashMap<Integer, Integer> valueCountMap, double mittelwert, int size) {
        double varianz = 0;
        for (Map.Entry<Integer, Integer> entry : valueCountMap.entrySet()) {
            varianz += Math.pow(entry.getKey() - mittelwert, 2) * (entry.getValue() / ((double)size - 1));
        }
        return varianz;
    }

    /**
     * Gibt ein Histogramm auf der Standardkonsole aus.
     *
     * @param valueCountMap Map aus allen verschiedenen Werten mit ihrer jeweiligen Anzahl an Vorkommen
     */
    public static void printHistogramm(HashMap<Integer, Integer> valueCountMap) {
        for (Map.Entry<Integer, Integer> entry : valueCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + "*".repeat(entry.getValue()));
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No input!");
            return;
        }

        int[] integerArray;
        try {
            integerArray = convertToIntArray(args);
        } catch (NumberFormatException e) {
            System.out.println("No valid input! Input must be numbers.\nMaybe values are too big!");
            return;
        }

        // enthält jeden verschiedenen vorkommenden Wert mit seiner Anzahl an Vorkommen
        HashMap<Integer, Integer> valueCountMap = intArrayToMap(integerArray);
        double mittelwert = calcMittelwert(integerArray);
        int summe = calcSum(integerArray);
        double standardabweichung = calcStandardabweichung(valueCountMap, mittelwert, integerArray.length);
        double varianz = calcVarianz(valueCountMap, calcMittelwert(integerArray), integerArray.length);

        System.out.println("Mittelwert: " + mittelwert);
        System.out.println("Summe: " + summe);
        System.out.println("Standardabweichung: " + standardabweichung);
        System.out.println("Varianz: " + varianz);
        System.out.println();

        printHistogramm(valueCountMap);
    }

}