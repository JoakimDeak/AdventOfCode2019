package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

	public static void main(String[] args) throws FileNotFoundException {
		int[] result = testAllTheThings();
		for(int num : result) {
			System.out.println(num);
		}
	}
	
	public static int[] testAllTheThings() throws FileNotFoundException {
		
		for(int noun = 0; noun < 100; noun++) {
			for(int verb = 0; verb < 100; verb++) {
				int result = doTheThing(noun, verb);
				if(result == 19690720) {
					return new int[] {noun, verb};
				}
			}
		}
		
		return null;
	}
	
	public static int doTheThing(int noun, int verb) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("src/day2/input.txt"));
		String input = sc.nextLine();
		sc.close();
		int[] inputArray = stringToArray(input);
		inputArray[1] = noun;
		inputArray[2] = verb;
		int[] result = compute(inputArray);
		
		return result[0];
	}

	public static int[] stringToArray(String input) {

		String[] sArr = input.split(",");
		int[] iArr = new int[sArr.length];
		for (int i = 0; i < sArr.length; i++) {
			iArr[i] = Integer.parseInt(sArr[i]);
		}

		return iArr;
	}

	public static int[] compute(int[] array) {

		int currentIndex = 0;

		while (array[currentIndex] != 99) {
			if (array[currentIndex] != 1 && array[currentIndex] != 2) {
				System.out.println("Oopsie Woopsie! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!");
				return null;
			}
			
			int firstNum = array[array[currentIndex + 1]];
			int secondNum = array[array[currentIndex + 2]];
			int res;
			if(array[currentIndex] == 1) {
				res = firstNum + secondNum;
			} else {
				res = firstNum * secondNum;
			}
			array[array[currentIndex + 3]] = res;
			
			currentIndex += 4;
		}

		return array;
	}
}
