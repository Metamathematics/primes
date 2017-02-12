package prime;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PrimesInteger {

	public static final long G = 3L * 5L * 7L * 11 * 13 * 17 * 19 * 23 * 29 * 31 * 37 * 41 * 43;

	public static void main(String[] args) {
		//primesThree();

		long number = 3L * 5L * 7L * 11 * 13 * 17 * 19 * 23 * 29 * 31 * 37-2;
		
		System.out.println(number + ""
				+ "\n" + isPrime(number));
	}

	public static void primesFour() {
		List<Integer> primes = new ArrayList();
		for (int i = 80989; primes.size() < 7925; i += 2) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		System.out.println(primes);
	}

	public static void primesTwo() {
		long number = 0;
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				number = sc.nextLong();
			} catch (InputMismatchException e) {
				break;
			}
			System.out.println(isPrime(number));
		}
	}

	public static void primesThree() {
		long number = 3L * 5L * 7L * 11 * 13 * 17 * 19 * 23 * 29 * 31 * 37-2;

		long up = firstPrimeArround(number, true);
		long down = firstPrimeArround(number, false);

		System.out.println(up + " \n" + number + " \n" + down);
		System.out.println(up - number);
		System.out.println(number - down);
	}

	public static long firstPrimeArround(long number, boolean up) {
		if (isEven(number)) {
			number++;
		}
		int direction = 1;
		if (!up) {
			direction = -1;
		}
		for (;; number += 2 * direction) {
			if (isPrime(number)) {
				break;
			}

		}
		return number;
	}

	public static List<Integer> allPrimeDividers(long number) {
		if (number < 2)
			throw new IllegalArgumentException("The number is < 2");
		long limit = (long) number / 2 + 1;

		List<Integer> dividersList = new ArrayList<Integer>();

		if (isEven(number)) {
			dividersList.add(2);
		}
		for (int i = 3; limit >= i; i += 2) {
			if (isPrime(i) && number % i == 0) {
				dividersList.add(i);
			}
		}
		return dividersList;
	}

	public static boolean isPrime(long number) {
		if (number < 2)
			throw new IllegalArgumentException("The number is < 2");
		long limit = (long) Math.sqrt(number) + 1;

		if (isEven(number)) {
			if (number == 2) {
				return true;
			}
			return false;
		}
		for (int i = 3; limit >= i; i += 2) {
			if (number % i == 0) {
				System.out.println("The first divider is " + i);
				return false;
			}
		}

		return true;
	}

	public static boolean isEven(long number) {
		if ((number & 1) == 0)
			return true;
		else
			return false;
	}
}
