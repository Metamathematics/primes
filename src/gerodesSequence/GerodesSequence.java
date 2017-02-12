package gerodesSequence;

import java.math.BigInteger;

import prime.BigPrimes;

import static prime.Primes2000.*;

public class GerodesSequence {

	final static BigInteger minusONE = new BigInteger("-1");
	final static BigInteger TWO = new BigInteger("2");

	public static void main(String[] args) {

		BigInteger[] arr = generateGerodesNumbers(15);
		for (BigInteger bi : arr) {
			BigPrimes.firstNextPrime(arr[2], true);
			System.out.println(bi);
			BigPrimes.firstNextPrime(arr[2], false);
		}

	}

	public static BigInteger[] generateGerodesNumbers(int index) {
		BigInteger[] gerodesNumbers = new BigInteger[5];

		gerodesNumbers[2] = oddPrimorialNumbers(index);

		gerodesNumbers[0] = gerodesNumbers[2].subtract(TWO.add(TWO));
		gerodesNumbers[1] = gerodesNumbers[2].subtract(TWO);
		gerodesNumbers[3] = gerodesNumbers[2].add(TWO);
		gerodesNumbers[4] = gerodesNumbers[2].add(TWO.add(TWO));

		return gerodesNumbers;
	}

	// Primorial numbers (first definition): product of first n primes.
	// Sometimes written prime(n)#.
	public static BigInteger oddPrimorialNumbers(int index) {
		if (index == 0)
			return BigInteger.ZERO;
		BigInteger primorialNumber = BigInteger.ONE;
		if (index < 0) {
			index *= -1;
			primorialNumber = primorialNumber.multiply(minusONE);
		}
		for (; 1 <= index; --index) {
			primorialNumber = primorialNumber.multiply(new BigInteger(String.valueOf(primes[index])));
		}
		return primorialNumber;
	}
}
