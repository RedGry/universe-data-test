import java.util.Arrays;

public class LetterCounter {
    /**
     * Напишите функцию, char[] getFirstEncounteredLetters(String str, int n),
     * которая возвращает массив длиной n, в котором в ячейке под индексом k-1
     * будет находиться первый символ, встречающийся в строке str ровно k раз. На вход подаются
     * только английские буквы в нижнем регистре (a - z) -- 26.
     */
    public static char[] getFirstEncounteredLetters(String str, int n) {
        int[] countLetters = new int[26];
        int[] firstOccurrence = new int[26];

        Arrays.fill(firstOccurrence, -1);

        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a'; // 'a' - 'a' = 97 - 97 = 0

            if (firstOccurrence[index] == -1) firstOccurrence[index] = i;
            countLetters[index]++;
        }

        char[] result = new char[n];

        for (int k = 1; k <= n; k++) {
            int minIdx = -1;

            for (int i = 0; i < 26; i++) {
                if (countLetters[i] != k) continue;
                if (countLetters[i] == k && (minIdx == -1 || firstOccurrence[i] < firstOccurrence[minIdx])) {
                    minIdx = i;
                }
            }

            result[k - 1] = minIdx != -1 ? (char) ('a' + minIdx) : ' ';
        }

        return result;
    }
}
