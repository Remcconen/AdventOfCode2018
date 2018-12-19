package aoc;

import java.util.*;
import java.util.stream.IntStream;

public class Day6A {

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

        HashMap<Integer, Integer> sizes = new HashMap<>();

        for(int x = min_x; x <= max_x; x++) {
            for(int y = min_y; y <= max_y; y++) {
                int closest = closest(x, y);
                if(closest > -1) {
                    if(x == min_x | x == max_x | y == min_y | y == max_y) {
                        sizes.put(closest, -1);
                    } else {
                        if(sizes.containsKey(closest)) {
                            if(sizes.get(closest) > -1) {
                                sizes.put(closest, sizes.get(closest)+1);
                            }
                        } else {
                            sizes.put(closest, 1);
                        }
                    }
                }
            }
        }

        System.out.println(Collections.max(sizes.values()));

    }

    public static int closest(int x, int y) {
        int distance = 9999;
        int closest = -1;
        boolean singleArea = true;
        for(int i = 0; i < x_coords.length; i++) {
            int d = Math.abs(x-x_coords[i])+Math.abs(y-y_coords[i]);
            if(d < distance) {
                distance = d;
                closest = i;
                singleArea = true;
            } else if (d == distance) {
                singleArea = false;
            }
        }
        if(singleArea) {
            return closest;
        } else {
            return -1;
        }
    }
}
