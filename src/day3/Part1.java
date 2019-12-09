package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Part1 {

	public static void main(String[] args) throws FileNotFoundException {
		String[][] wires = wiresFromInput();
		HashSet<ArrayList<Integer>> wp0 = traceWire(wires[0]);
		HashSet<ArrayList<Integer>> wp1 = traceWire(wires[1]);
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

	public static HashSet<ArrayList<Integer>> traceWire(String[] wire) {
		HashSet<ArrayList<Integer>> wirePath = new HashSet<ArrayList<Integer>>();

		int x = 0;
		int y = 0;

		for (String ins : wire) {
			char dir = ins.charAt(0);
			int steps = Integer.parseInt(ins.substring(1, ins.length()));
			switch (dir) {
			case 'R':
				for (int i = 1; i <= steps; i++) {
					x++;
					ArrayList<Integer> cPos = new ArrayList<Integer>();
					cPos.add(x);
					cPos.add(y);
					wirePath.add(cPos);
				}
				break;
			case 'D':
				for (int i = 1; i <= steps; i++) {
					y++;
					ArrayList<Integer> cPos = new ArrayList<Integer>();
					cPos.add(x);
					cPos.add(y);
					wirePath.add(cPos);
				}
				break;

			case 'U':
				for (int i = 1; i <= steps; i++) {
					y--;
					ArrayList<Integer> cPos = new ArrayList<Integer>();
					cPos.add(x);
					cPos.add(y);
					wirePath.add(cPos);
				}
				break;

			case 'L':
				for (int i = 1; i <= steps; i++) {
					x--;
					ArrayList<Integer> cPos = new ArrayList<Integer>();
					cPos.add(x);
					cPos.add(y);
					wirePath.add(cPos);
				}
				break;
			}
		}

		return wirePath;
	}

	public static ArrayList<ArrayList<Integer>> findCrossing(HashSet<ArrayList<Integer>> wp0,
			HashSet<ArrayList<Integer>> wp1) {
		ArrayList<ArrayList<Integer>> crossings = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> pos : wp0) {
			if (wp1.contains(pos)) {
				crossings.add(pos);
			}
		}
		return crossings;
	}

	public static int findClosestCrossing(ArrayList<ArrayList<Integer>> crossings) {

		int bestDist = Integer.MAX_VALUE;

		for (ArrayList<Integer> crossing : crossings) {
			int dist = Math.abs(crossing.get(0)) + Math.abs(crossing.get(1));
			if (dist < bestDist) {
				bestDist = dist;
			}
		}

		return bestDist;
	}
}
