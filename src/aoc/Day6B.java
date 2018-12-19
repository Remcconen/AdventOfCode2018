package aoc;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Day6B {

    static int[] x_coords;
    static int[] y_coords ;

    public static void main(String[] args) {
        List<String> read = utils.readFile("day6.txt");
        x_coords = new int[read.size()];
        y_coords = new int[read.size()];
        for(int i = 0; i < read.size(); i++) {
            x_coords[i] = (Integer.valueOf(read.get(i).split(", ")[0]));
            y_coords[i] = (Integer.valueOf(read.get(i).split(", ")[1]));
        }

        int min_x = IntStream.of(x_coords).min().orElse(0);
        int min_y = IntStream.of(y_coords).min().orElse(0);
        int max_x = IntStream.of(x_coords).max().orElse(0);
        int max_y = IntStream.of(y_coords).max().orElse(0);

        System.out.println("X: ["+min_x+", "+max_x+"]");
        System.out.println("Y: ["+min_y+", "+max_y+"]");

        int regionSize = 0;

        for(int x = min_x; x <= max_x; x++) {
            for(int y = min_y; y <= max_y; y++) {
                if(isInRegion(x, y)) {
                    regionSize++;
                }
            }
        }
        System.out.println(regionSize);
    }

    public static boolean isInRegion(int x, int y) {
        int distance = 0;
        for(int i = 0; i < x_coords.length; i++) {
            int d = Math.abs(x-x_coords[i])+Math.abs(y-y_coords[i]);
            distance += d;
            if(distance >= 10000) {
                return false;
            }
        }
        return true;
    }
}
