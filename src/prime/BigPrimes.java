package prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BigPrimes {
	
	final static BigInteger sqrtHelper = new BigInteger("100000000");
	final static BigInteger TWO = new BigInteger("2");
	final static BigInteger THREE = new BigInteger("3");

	public static void main(String[] args) {
		System.out.println(isPrime(new BigInteger("672")));
	}

	public static List<BigInteger> allPrimeDividers(BigInteger number) {
		if (number.compareTo(new BigInteger("0")) < 0)
			throw new IllegalArgumentException("The number should be positive");
		BigInteger limit = number.divide(new BigInteger("2"));

		List<BigInteger> dividersList = new ArrayList<BigInteger>();

		if (isEven(number)) {
			dividersList.add(new BigInteger("2"));
		}
		for (BigInteger i = new BigInteger("3"); limit.compareTo(i) >= 0; i = i.add(new BigInteger("2"))) {
			if (isPrime(i) && number.mod(new BigInteger(String.valueOf(i))).toString().equals("0")) {
				dividersList.add(i);
			}
		}
		return dividersList;
	}

	public static BigInteger firstNextPrime(BigInteger number, boolean up) {
		// (up == true) = find the next BIGGER Prime
		// (up == false) = find the next SMALLER Prime
		int direction = 1;
		if (!up) {
			direction = -1;
		}
		// if the number is even add or subtract "1".
		if (isEven(number)) {
			number = number.add(new BigInteger(String.valueOf(direction)));
		}
		// find the next Prime
		for (;; number = number.add(new BigInteger(String.valueOf(2 * direction)))) {
			if (isPrime(number)) {
				break;
			}

		}
		return number;
	}

	public static boolean isPrime(BigInteger number) {
		if (number.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalArgumentException("The number should be positive");

		if (number.equals(BigInteger.ONE) || number.equals(BigInteger.ZERO))
			return false;
		
		/*
		 *  if the number is even return FALSE, unless the number is "2".
		 */
		if (isEven(number)) {
			if (number.equals(TWO)) {
				return true;
			}
			return false;
		}

		BigInteger bLimit = sqrt(number);
		/*
		 * A loop for searching a divider. If no divider found - return "true".
		 */
		for (BigInteger i = THREE; bLimit.compareTo(i) >= 0; i = i.add(TWO)) {
			/*
			 *  If number % i == 0
			 */
			if (number.mod(i).equals(BigInteger.ZERO)) {
				// System.out.println("The first divider is " + i);
				return false;
			}
		}
		return true;
	}
	

	public static BigInteger sqrt(BigInteger N) {

		BigInteger result = N.divide(TWO);
		while (result.multiply(result).subtract(N).compareTo(BigInteger.ONE.divide(sqrtHelper)) > 0)
			result = result.add(N.divide(result)).divide(TWO);
		return result;
	}
	

	public static boolean isEven(BigInteger number) {
		return number.getLowestSetBit() != 0;
	}
}
