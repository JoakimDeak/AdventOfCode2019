package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
	
	/*
	 * needs to implement support for immediate mode
	 */

	public static void main(String[] args) throws FileNotFoundException {
		int[] instructions = getInput();
		instructions = compute(instructions);
		for(int i : instructions) {
			System.out.println(i);
		}
	}

	public static int[] getInput() throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/day5/input.txt"));
		String input = sc.nextLine();
		sc.close();
		String[] sArr = input.split(",");
		int[] iArr = new int[sArr.length];
		for (int i = 0; i < sArr.length; i++) {
			iArr[i] = Integer.parseInt(sArr[i]);
		}

		return iArr;
	}

	public static int[] compute(int[] insList) {

		int i = 0;
		while (insList[i] != 99) {
			int param1 = insList[insList[i + 1]];
			int param2 = insList[insList[i + 2]];
			int param3 = insList[i + 3];
			int cIns = insList[i];
			switch (cIns) {
			case 1:
				insList[param3] = param1 + param2;
				i += 4;
				break;
			case 2:
				insList[param3] = param1 * param2;
				i += 4;
				break;
			case 3:
				Scanner sc = new Scanner(System.in);
				param1 = insList[i + 1];
				insList[param1] = sc.nextInt();
				sc.close();
				i += 2;
				break;
			case 4:
				param1 = insList[i + 1];
				System.out.println(insList[param1]);
				break;
			}
		}
		return insList;
	}
}
