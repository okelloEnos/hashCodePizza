package com.okelloSoftwarez;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static ArrayList<Integer> teamDelivery;

    public static void main(String[] args) {

        // write your code here
        try {
//            Reading file
            File exampleFile = new File("/home/okelloenos/IdeaProjects/hashCodePractice/src/com/okelloSoftwarez/a_example");
            Scanner myReader = new Scanner(exampleFile);

            int pizza_number, two_team_number, three_team_number, four_team_number;
            pizza_number = myReader.nextInt();
            two_team_number = myReader.nextInt();
            three_team_number = myReader.nextInt();
            four_team_number = myReader.nextInt();

            ArrayList<Pizza> pizzas = new ArrayList<>();

            int i = 0;

//            Read each line of the file
            while (myReader.hasNextLine()) {
//                check if there is a next character
                if (myReader.hasNext()) {
//                    read next character
                    int value = myReader.nextInt();
//                    int ingrNum = value;
                    ArrayList<String> ingrNames = new ArrayList<>();
                    while (value > 0) {
                        ingrNames.add(myReader.next());
                        value--;
                    }

                    pizzas.add(new Pizza(Integer.toString(i), ingrNames));
                    i++;
                } else {
//                    end of a line
                    break;
                }
            }

            int[] types = {2, 3, 4};
            int typesSize = types.length;

            int delivery = hasArrayTwoCandidates(types, typesSize, 5);
            if (delivery > 0) {
                distributeDelivery(pizzas);
            } else {
                System.out.println("No deliveries");
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private static void distributeDelivery(ArrayList<Pizza> pizzas) {
        int p = 0, h = 0, rep = 0;
        String type = "";
        checker(pizzas, p, h, rep, type);

    }

    private static void checker(ArrayList<Pizza> pizza, int p, int h, int rep, String type) {
        if (p < teamDelivery.size()) {
            type = type + teamDelivery.get(p).toString();
            rep = rep + teamDelivery.get(p);
            while (h < rep) {
                Pizza retrievedPizza = pizza.get(h);
                type = type + " " + retrievedPizza.getPizzaType();
                h++;

            }
            type = type + "\n";

            savingToFile(teamDelivery.size(), type);

            p++;
            checker(pizza, p, h, rep, type);

        }
    }

    private static void savingToFile(int deliveries, String type) {
        try {
            FileWriter fileSave = new FileWriter("/home/okelloenos/IdeaProjects/hashCodePractice/src/com/okelloSoftwarez/pizzeria Delivery.txt");
            fileSave.write(deliveries + "\n");
            fileSave.write(type);
            fileSave.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int hasArrayTwoCandidates(int A[], int arr_size, int sum) {
        int l, r;

        /* Sort the elements */
        Arrays.sort(A);

		/* Now look for the two candidates
		in the sorted array*/
        l = 0;
        r = arr_size - 1;
        while (l < r) {
            if (A[l] + A[r] == sum) {

                teamDelivery = new ArrayList<Integer>();
                int count = 0;
                if (A[l] == 2 || A[r] == 2) {
                    System.out.println("Delivery to team of 2");
                    teamDelivery.add(2);
                    count++;
                }

                if (A[l] == 3 || A[r] == 3) {
                    System.out.println("Delivery to team of 3");
                    teamDelivery.add(3);
                    count++;
                }

                if (A[l] == 4 || A[r] == 4) {
                    System.out.println("Delivery to team of 4");
                    teamDelivery.add(4);
                    count++;
                }

                System.out.println("Numbers are : " + A[l] + " and : " + A[r]);

                return count;
            } else if (A[l] + A[r] < sum)
                l++;
            else // A[i] + A[j] > sum
                r--;
        }
        return 0;
    }
}
