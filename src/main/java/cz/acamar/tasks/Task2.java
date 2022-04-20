package cz.acamar.tasks;

public class Task2 {

    /**
     * Task 2. Squares of a sorted array.
     * <p>
     * Given an integer array sorted in non-decreasing order,
     * return an array of the squares of each number sorted in non-decreasing order.
     * <p>
     * Example input: [-4,-1,0,3,10]
     * Expected output: [0,1,9,16,100]
     * <p>
     * Explanation: After squaring, the array becomes [16,1,0,9,100].
     * After sorting, it becomes [0,1,9,16,100].
     *
     * @param input - an integer array sorted in non-decreasing order
     * @return - an array of the squares of each number sorted in non-decreasing order
     */
    public int[] squaresOfSortedArray(int[] input) {
        int[] resultArray = new int[input.length];

        int inputArrayLength = input.length;
        int firstPositiveNumberIndex;

        for (firstPositiveNumberIndex = 0; firstPositiveNumberIndex < inputArrayLength; firstPositiveNumberIndex++) {
            if (input[firstPositiveNumberIndex] >= 0) {
                break;
            }
        }

        if (firstPositiveNumberIndex == 0) { //If there are only positive numbers in the array
            for (int i = 0; i < resultArray.length; i++) {
                resultArray[i] = square(input[i]);
            }
        } else if (firstPositiveNumberIndex == inputArrayLength - 1) { //If there are only negative numbers in the array
            for (int i = resultArray.length - 1; i > 0; i--) {
                resultArray[i] = square(input[i]);
            }
        } else {
            int negativeNumbersIndex = firstPositiveNumberIndex - 1;
            int positiveNumbersIndex = firstPositiveNumberIndex;

            for (int i = 0; i < resultArray.length; i++) {
                if (negativeNumbersIndex < 0) { //If all negative numbers are already squared
                    resultArray[i] = square(input[positiveNumbersIndex]);
                    positiveNumbersIndex++;
                    continue;
                }

                if (positiveNumbersIndex > resultArray.length - 1) { //If all positive numbers are already squared
                    resultArray[i] = square(input[negativeNumbersIndex]);
                    negativeNumbersIndex--;
                    continue;
                }

                if (square(input[negativeNumbersIndex]) < square(input[positiveNumbersIndex])) {
                    resultArray[i] = square(input[negativeNumbersIndex]);
                    negativeNumbersIndex--;
                } else {
                    resultArray[i] = square(input[positiveNumbersIndex]);
                    positiveNumbersIndex++;
                }
            }
        }

        return resultArray;
    }

    private int square(int value) {
        return Math.multiplyExact(value, value); //To avoid int overflow
    }
}
