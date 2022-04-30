package Internship.Growth_In_2_Dimensons;

import java.util.List;

public class StackOveflow
{
    public static void main(String[] args) {
        List<String> upRight = List.of("1 4", "2 3", "4 7");
        System.out.println(countMax(upRight));
    }

    public static long countMax(List<String> upRight) {
        int countR = 0;
        int r = 1;

        //iterate through pairs of coordinates
        for (int i = 0; i <= upRight.size() - 1; i++) {
            //count number of times r is equal or smaller than first coordinate of each pair
            if (r < Integer.parseInt(upRight.get(i).substring(0, 1))) {
                countR++;
            }
        }

        //to check if all pairs have been iterated through
        if (countR % upRight.size() == 0) {
            r++;
        }

        int countC = 0;
        int c = 1;

        //iterate through pairs of coordinates again
        for (int j = 0; j <= upRight.size() - 1; j++) {
            //count number of times c is equal or smaller than second coordinate of each pair
            if (c < Integer.parseInt(upRight.get(j).substring(2, 3))) {
                countC++;
            }
        }

        //to check if all pairs have been iterated through
        if (countC % upRight.size() == 0) {
            c++;
        }
        //reasoning is that overlapping grid is square
        return (long)r * c;
    }
}
