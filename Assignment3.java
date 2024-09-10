/***
 * Question: Manipulation of String and Number
 * Owner Name: Ayush Ray
 * Date: 10-9-2024
 */

import java.util.Scanner;

public class Assignment3 {


    public static boolean isPowerOfTwo(int number) {
        return (number > 0) && ((number & (number - 1)) == 0);
    }

    public static void sortDescending(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    // Find and print all possible combinations of consecutive natural numbers that sum to a given number

    public static void findConsecutiveNumberCombinations(int targetNumber) {
        for (int start = 1; start <= targetNumber / 2; start++) {
            int currentSum = 0;
            StringBuilder sequence = new StringBuilder();
            for (int i = start; i <= targetNumber; i++) {
                currentSum += i;
                sequence.append(i).append(" + ");
                if (currentSum == targetNumber) {
                    sequence.delete(sequence.length() - 3, sequence.length());
                    System.out.println(sequence.toString());
                    break;
                } else if (currentSum > targetNumber) {
                    break;
                }
            }
        }
    }

    public static String encodeHighestDigits(int[] array, int[] series) {
        sortDescending(array);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < series.length; i++) {
            int index = series[i];
            int highestDigit = array[index - 1];
            char asciiChar = (char) (highestDigit + 48);
            result.append((int) asciiChar);
        }
        return result.toString();
    }

    public static int calculateDigitSum(int number) {
        int totalSum = 0;
        while (number > 0) {
            totalSum += number % 10;
            number = number / 10;
        }
        return totalSum;
    }

    public static void reduceNumberToSingleDigit(int number) {
        System.out.println(Constants.INITIAL_NUMBER + number);
        while (number >= 10) {
            number = calculateDigitSum(number);
            System.out.println(Constants.INTERMEDIATE_RESULT + number);
        }
        System.out.println(Constants.SINGLE_DIGIT_NUMBER + number);
    }

    public static void swapCharacters(char[] array, int firstIndex, int secondIndex) {
        char temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public static void generatePermutations(char[] characters, int startIndex) {
        printPermutation(characters, startIndex);
        for (int i = startIndex; i < characters.length; i++) {
            swapCharacters(characters, i, startIndex);
            generatePermutations(characters, startIndex + 1);
            swapCharacters(characters, i, startIndex); // Backtrack
        }
    }

    public static void printPermutation(char[] characters, int endIndex) {
        for (int i = 0; i < endIndex; i++) {
            System.out.print(characters[i]);
        }
        System.out.println();
    }

    public static String encryptStringWithPattern(String input, int[] shiftPattern) {
        StringBuilder encrypted = new StringBuilder();
        int patternLength = shiftPattern.length;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int shift = shiftPattern[i % patternLength];
                char encryptedChar = (char) ((c - base + shift) % 26 + base);
                encrypted.append(encryptedChar);
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    public static int[] parseShiftPattern(String patternInput) {
        String[] parts = patternInput.split(",");
        int[] pattern = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            pattern[i] = Integer.parseInt(parts[i].trim());
        }
        return pattern;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Constants.choose);
        System.out.println(Constants.Consecutive);
        System.out.println(Constants.Encoding);
        System.out.println(Constants.Single_digit);
        System.out.println(Constants.Permutation);
        System.out.println(Constants.Caesar);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print(Constants.ENTER_NUMBER);
                int targetNumber = scanner.nextInt();
                if (targetNumber <= 0) {
                    System.out.println(Constants.INTEGER_WARNING);
                } else if (isPowerOfTwo(targetNumber)) {
                    System.out.println(Constants.InvalidNum);
                } else {
                    findConsecutiveNumberCombinations(targetNumber);
                }
                break;

            case 2:
                System.out.print(Constants.Array_Size);
                int n = scanner.nextInt();
                int[] array = new int[n];
                System.out.println(Constants.Enter_Array);
                for (int i = 0; i < n; i++) {
                    array[i] = scanner.nextInt();
                }

                System.out.print(Constants.Series_Size);
                int m = scanner.nextInt();
                int[] series = new int[m];
                System.out.println(Constants.EnterSeriesArray);
                for (int i = 0; i < m; i++) {
                    series[i] = scanner.nextInt();
                }
                System.out.println("Encoded Output: " + encodeHighestDigits(array, series));
                break;

            case 3:
                System.out.print(Constants.Single_digit1);
                int number = scanner.nextInt();
                reduceNumberToSingleDigit(number);
                break;

            case 4:
                System.out.print(Constants.GeneratingPermutation);
                String input = scanner.next();
                generatePermutations(input.toCharArray(), 0);
                break;

            case 5:
                System.out.print(Constants.Encrypt);
                String cipherInput = scanner.nextLine();
                System.out.print(Constants.Comma);
                String patternInput = scanner.nextLine();
                if (patternInput.startsWith(" ")) {
                    System.out.println("invalid shift");
                }
                int[] shiftPattern = parseShiftPattern(patternInput);
                String encryptedString = encryptStringWithPattern(cipherInput, shiftPattern);
                System.out.println("Encrypted Output: " + encryptedString);
                break;

            default:
                System.out.println(Constants.Invalid);
        }

        scanner.close();
    }
}
