package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Part2 {

	public static void main(String[] args) throws FileNotFoundException {
		String[][] wires = wiresFromInput();
		HashMap<ArrayList<Integer>, Integer> wp0 = traceWire(wires[0]);
		HashMap<ArrayList<Integer>, Integer> wp1 = traceWire(wires[1]);
		ArrayList<ArrayList<Integer>> crossings = findCrossing(wp0, wp1);
		System.out.println(findClosestCrossing(crossings));
	}

	public static String[][] wiresFromInput() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("src/day3/input.txt"));

		String[][] wires = new String[2][];

		String[] w0 = sc.nextLine().split(",");
		String[] w1 = sc.nextLine().split(",");

		sc.close();

		wires[0] = w0;
		wires[1] = w1;

		return wires;
	}

	public static HashMap<ArrayList<Integer>, Integer> traceWire(String[] wire) {
		HashMap<ArrayList<Integer>, Integer> wirePath = new HashMap<ArrayList<Integer>, Integer>();

		int x = 0;
		int y = 0;
		Integer steps = 0;

		for (String ins : wire) {
			char dir = ins.charAt(0);
			int totSteps = Integer.parseInt(ins.substring(1, ins.length()));
			switch (dir) {
			case 'R':
				for (int i = 1; i <= totSteps; i++) {
					steps++;
					x++;
					ArrayList<Integer> cPos = new ArrayList<Integer>();
					cPos.add(x);
					cPos.add(y);
					wirePath.put(cPos, steps);
				}
				break;
			case 'D':
				for (int i = 1; i <= totSteps; i++) {
					steps++;
					y++;
					ArrayList<Integer> cPos = new ArrayList<Integer>();
					cPos.add(x);
					cPos.add(y);
					wirePath.put(cPos, steps);
				}
				break;

			case 'U':
				for (int i = 1; i <= totSteps; i++) {
					steps++;
					y--;
					ArrayList<Integer> cPos = new ArrayList<Integer>();
					cPos.add(x);
					cPos.add(y);
					wirePath.put(cPos, steps);
				}
				break;

			case 'L':
				for (int i = 1; i <= totSteps; i++) {
					steps++;
					x--;
					ArrayList<Integer> cPos = new ArrayList<Integer>();
					cPos.add(x);
					cPos.add(y);
					wirePath.put(cPos, steps);
				}
				break;
			}
		}

		return wirePath;
	}

	public static ArrayList<ArrayList<Integer>> findCrossing(HashMap<ArrayList<Integer>, Integer> wp0,
			HashMap<ArrayList<Integer>, Integer> wp1) {
		
		ArrayList<ArrayList<Integer>> crossings = new ArrayList<ArrayList<Integer>>();
		
		Set<Entry<ArrayList<Integer>, Integer>> wp0set = wp0.entrySet();
		
		for(Entry<ArrayList<Integer>, Integer> entry : wp0set) {
			if(wp1.containsKey(entry.getKey())) {
				int totSteps = entry.getValue() + wp1.get(entry.getKey());
				ArrayList<Integer> crossing = new ArrayList<Integer>();
				crossing.add(entry.getKey().get(0));
				crossing.add(entry.getKey().get(1));
				crossing.add(totSteps);
				crossings.add(crossing);
			}
		}
		return crossings;
	}

	public static int findClosestCrossing(ArrayList<ArrayList<Integer>> crossings) {

		int bestDist = Integer.MAX_VALUE;
		
		for(ArrayList<Integer> crossing : crossings) {
			if(bestDist > crossing.get(2)) {
				bestDist = crossing.get(2);
			}
		}
		
		return bestDist;
	}
}
