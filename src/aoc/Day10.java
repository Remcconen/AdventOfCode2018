package aoc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {

    static int min_x = 0;
    static int min_y = 0;
    static int max_x = 0;
    static int max_y = 0;

    static int cur_min_x = 0;
    static int cur_min_y = 0;
    static int cur_max_x = 0;
    static int cur_max_y = 0;

    public static void main(String[] args) {
        List<String> input = utils.readFile("day10.txt");

        Pattern pattern = Pattern.compile("position=< *(-?\\d+), *(-?\\d+)> velocity=< *(-?\\d+), *(-?\\d+)>");
        List<Point> points = new LinkedList<>();
        for(String s : input) {
            Matcher matcher = pattern.matcher(s);
            while(matcher.find()) {
                int x = Integer.valueOf(matcher.group(1));
                int y = Integer.valueOf(matcher.group(2));
                int xv = Integer.valueOf(matcher.group(3));
                int yv = Integer.valueOf(matcher.group(4));
                min_x = x < min_x ? x : min_x;
                max_x = x > max_x ? x : max_x;
                min_y = y < min_y ? y : min_y;
                max_y = y > max_y ? y : max_y;
                Point point = new Point(x, y, xv, yv);
                points.add(point);
            }
        }

        System.out.println(min_x + " - "+max_x);
        System.out.println(min_y + " - "+max_y);

        int time = 0;
        Scanner scanner = new Scanner(System.in);
        boolean cont = true;
//        while(scanner.next().equals("y")) {
        while(cont) {
            System.out.println(time);
            cur_min_x = 0;
            cur_min_y = 0;
            cur_max_x = 0;
            cur_max_y = 0;

//            System.out.println("Time = "+time);
            TreeMap<Integer, TreeMap<Integer, Character>> grid = new TreeMap<>();
//            for(int y = min_y; y <= max_y; y++) {
//                System.out.println(y);
//                TreeMap<Integer, Character> row = new TreeMap<>();
//                for(int x = min_x; x <= max_x; x++) {
//                    row.put(x, '.');
//                }
//                grid.put(y, row);
//            }
//            System.out.println("Grid initiated");
//            char[][] grid = new char[(max_y-min_y+1)][(max_x-min_x+1)];
//            for(char[] x : grid) {
//                Arrays.fill(x, '.');
//            }
            for(Point p : points) {
//            Point p = points.get(0);
                int[] pos = p.move(time);
                TreeMap<Integer, Character> row = grid.getOrDefault(pos[1], new TreeMap<>());
//                System.out.println(Arrays.toString(pos));
//                System.out.println(row);
                row.put(pos[0], '#');
                grid.put(pos[1], row);

                cur_min_x = pos[0] < cur_min_x ? pos[0] : cur_min_x;
                cur_max_x = pos[0] > cur_max_x ? pos[0] : cur_max_x;
                cur_min_y = pos[1] < cur_min_y ? pos[1] : cur_min_y;
                cur_max_y = pos[1] > cur_max_y ? pos[1] : cur_max_y;

//                printGrid(grid);

//                grid[pos[1]-min_y][pos[0]-min_x] = '#';
            }

            if(cur_max_x - cur_min_x < 300 && cur_max_y - cur_min_y < 300) {
                printGrid(grid);
                cont = false;
            }
//            for(String[] x : grid) {
//                System.out.println(Arrays.toString(x));
//            }
//            System.out.println(cur_min_x + " - "+cur_max_x + " -- "+ (cur_max_x - cur_min_x));
//            System.out.println(cur_min_y + " - "+cur_max_y + " -- " + (cur_max_y - cur_min_y));
//            cont = false;
            if(!cont) {
                System.out.println("Continue?");
                if(scanner.next().equals("y")) {
                    cont = true;
                }
            }
            time++;
        }

//        for(Point p:points) {
//            System.out.println(p);
//        }
        System.out.println(min_x + " - "+max_x);
        System.out.println(min_y + " - "+max_y);

    }

    public static void printGrid(TreeMap<Integer, TreeMap<Integer, Character>> grid) {
        for(TreeMap<Integer, Character> y : grid.values()) {
            for(int x = cur_min_x; x <= cur_max_x; x++) {
                System.out.print(y.getOrDefault(x, '.'));
            }
//            for(Character c : x.values()) {
//                System.out.print(c);
//            }
            System.out.println();
        }
    }

    public static class Point {
        int pos_x;
        int pos_y;
        int speed_x;
        int speed_y;

        public Point(int x, int y, int x_v, int y_v) {
            pos_x = x;
            pos_y = y;
            speed_x = x_v;
            speed_y = y_v;
        }

        public int[] move(int time) {
            int[] result = {pos_x+(speed_x*time), pos_y+(speed_y*time)};
            return result;
        }

        public String toString() {
            return "("+pos_x+", "+pos_y+") ("+speed_x+", "+speed_y+")";
        }
    }

}
