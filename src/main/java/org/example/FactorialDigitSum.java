package org.example;

import java.math.BigInteger;


public class FactorialDigitSum {
    public static void main(String[] args) {
        //1: Calculate 100!
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= 100; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        //2: Convert factorial to string
        String factorialString = factorial.toString();

        //3: Calculate the sum of digits
        int digitSum = 0;
        for (char digit : factorialString.toCharArray()) {
            digitSum += Character.getNumericValue(digit);
        }

        //result
        System.out.println("The sum of the digits in 100! is: " + digitSum);
    }
}
