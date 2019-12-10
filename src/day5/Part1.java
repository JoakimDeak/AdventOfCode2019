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
		for (int i : instructions) {
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
		while (insList[i] != 99 || i < insList.length) {
			int cIns = insList[i];

			int modeParam1 = 0;
			int modeParam2 = 0;
			String cInsString = String.valueOf(cIns);
			if (cInsString.length() > 1) {
				if (cInsString.length() == 4) {
					modeParam1 = Character.getNumericValue(cInsString.charAt(1));
					modeParam2 = Character.getNumericValue(cInsString.charAt(0));
				} else if (cInsString.length() == 3) {
					modeParam1 = Character.getNumericValue(cInsString.charAt(0));
				}
				cIns = cIns % 10;
			}

			System.out.println(modeParam1);
			System.out.println(modeParam2);

			int param1 = 0;
			int param2 = 0;
			int param3 = 0;

			if (modeParam1 == 0) {
				param1 = insList[insList[i + 1]];
			} else if (modeParam1 == 1) {
				param1 = insList[i + 1];
			}
			for (int j = 0; j < 20; j++) {
				System.out.print(insList[j]);
				if (j == i) {
					System.out.print("!");
				}
				System.out.print(", ");
			}
			System.out.println();
			if (cIns != 3 && cIns != 4) {
				if (modeParam2 == 0) {
					param2 = insList[insList[i + 2]];
				} else if (modeParam2 == 1) {
					param2 = insList[i + 2];
				}
				param3 = insList[i + 3];
			}

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
				insList[param1] = 1; // sc.nextInt();
				sc.close();
				i += 2;
				break;
			case 4:
				param1 = insList[i + 1];
				System.out.println(insList[param1]);
				i += 2;
				break;
			}
		}
		return insList;
	}
}
