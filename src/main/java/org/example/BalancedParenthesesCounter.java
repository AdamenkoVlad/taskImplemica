package org.example;

import java.util.Scanner;

public class BalancedParenthesesCounter {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter the number of bracket pairs (N): ");
        int pairCount = inputScanner.nextInt();

        if (pairCount < 0) {
            System.out.println("The number of bracket pairs must be a non-negative integer.");
            return;
        }

        long totalBalancedSequences = calculateBalancedParentheses(pairCount);
        System.out.println("Number of valid bracket sequences: " + totalBalancedSequences);
    }

    // Method to calculate the number of balanced parentheses sequences
    public static long calculateBalancedParentheses(int N) {
        // Array to store the count of balanced sequences for each pair count
        long[] balancedCounts = new long[N + 1];

        // Base case: there's one valid sequence for 0 pairs (an empty string)
        balancedCounts[0] = 1;

        // Compute the count for each number of bracket pairs
        for (int i = 1; i <= N; i++) {
            long currentCount = 0;

            // Iterate through all possible combinations of left and right subexpressions
            for (int j = 0; j < i; j++) {
                int rightSubExpression = i - j - 1;
                // Multiply counts of left and right subexpressions and add to the total
                currentCount += balancedCounts[j] * balancedCounts[rightSubExpression];
            }

            // Store the calculated count for the current number of pairs
            balancedCounts[i] = currentCount;
        }

        return balancedCounts[N];
    }
}
