package org.mustard.fitnessmonitor.models;

import java.sql.SQLException;
import java.util.Scanner;

public class MustardMain {

	public static void main(String[] args) throws SQLException {
		double totalCalories = 0;
		Physical[] physicals = new Physical[3];
		MustardSupport support = new MustardSupport();

		try {
			physicals[0] = new Running(34, 23, 3, 0);
			physicals[1] = new Cycling(56, 23, 2, 3);
			physicals[2] = new Swimming(56, 34);
			/*
			 * for (Physical ps : physicals) { totalCalories +=
			 * ps.calculateCalories(); System.out.println(ps.activityType() +
			 * " " + ps.calculateCalories()); }
			 */
		} catch (NegativeValueException e) {
			System.err.println(e.getMessage());
		}

		String res = null;
		do {
			System.out.println("Input activity" + "\nRunning A" + "\nCycling  B" + "\nSwimming C");
			Scanner in = new Scanner(System.in);
			String activity = in.nextLine();
			if (activity.equalsIgnoreCase("a")) {
				System.out.println("Distance or Time");
				Scanner sc = new Scanner(System.in);
				if (sc.nextLine().equalsIgnoreCase("a")) {
					System.out.println("Enter Distance");
					physicals[0].setDistance(in.nextDouble());
				} else {
					System.out.println("Enter Time in minutes");
					((Running) physicals[0]).setTime(in.nextInt());
				}
				totalCalories += physicals[0].calculateCalories();
			} else if (activity.equalsIgnoreCase("b")) {
				System.out.println("Enter Distance");
				physicals[1].setDistance(in.nextDouble());
				totalCalories += physicals[1].calculateCalories();
			} else if (activity.equalsIgnoreCase("c")) {
				System.out.println("Enter Distance");
				physicals[2].setDistance(in.nextDouble());
				totalCalories += physicals[2].calculateCalories();

			} else {
				System.out.println("Invalid choice not and option!");
			}
			System.out.println("Choose activity again? Y/N");
			Scanner ans = new Scanner(System.in);
			res = ans.nextLine();
		} while (res.equalsIgnoreCase("y"));

		support.storeData(totalCalories);
		support.showData();
	}

}
