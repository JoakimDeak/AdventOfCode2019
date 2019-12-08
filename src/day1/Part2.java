package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(getFuel());
	}

	public static int getFuel() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("src/day1/input.txt"));
		int fuelSum = 0;
		while (sc.hasNextInt()) {
			int mass = sc.nextInt();
			int fuel = calcFuel(mass);
			fuelSum += fuel;
			fuelSum += getExtraFuel(fuel);
		}
		sc.close();
		return fuelSum;
	}

	public static int calcFuel(int mass) {
		int reqFuel = mass;

		reqFuel /= 3;
		reqFuel -= 2;

		return reqFuel;
	}

	public static int getExtraFuel(int prevFuel) {

		int totExtra = 0;
		
		int extraFuel = calcFuel(prevFuel);
		while(extraFuel > 0) {
			totExtra += extraFuel;
			extraFuel = calcFuel(extraFuel);
		}
		
		return totExtra;
	}

}
