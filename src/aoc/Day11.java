package aoc;

import java.util.List;

public class Day11 {

    public static void main(String[] args) {
        List<String> input = utils.readFile("day11.txt");

        int serial = Integer.valueOf(input.get(0));

        int[][] grid = new int[300][300];
        for(int y = 0; y < grid.length; y++) {
            int[] row = grid[y];
            for (int x = 0; x < row.length; x++) {
                grid[y][x] = calculateCellPowerLevel(x+1,y+1,serial);

            }
        }

        int top_x = 0;
        int top_y = 0;
        int maxPower = 0;
        int maxSize = 0;
        for(int size = 1; size <= grid.length; size++) {
            if(size % 10 == 0) {
                System.out.println("Size: "+size);
            }
            for(int row_y = 0; row_y < grid.length-(size-1); row_y++) {
                for(int col_x = 0; col_x < grid.length-(size-1); col_x++) {
                    int power = 0;
                    for(int y = row_y; y < row_y+size; y++) {
                        for(int x = col_x; x < col_x+size; x++) {
                            power += grid[y][x];
                        }
                    }
                    if(power > maxPower){
                        maxPower = power;
                        top_x = col_x+1;
                        top_y = row_y+1;
                        maxSize = size;
                    }
                }
            }
        }
        System.out.println("Largest fuel at ("+top_x+", "+top_y+"), size "+maxSize+" with power "+maxPower+" and serial "+serial);
        System.out.println(top_x+","+top_y+","+maxSize);


    }

    public static int calculateCellPowerLevel(int x, int y, int serial) {
        int rack_id = (x) + 10;
        int level = rack_id;
        level *= (y);
        level += serial;
        level *= rack_id;
        String level_str = String.valueOf(level);
        level = level > 99 ? level_str.charAt(level_str.length()-3)-48 : 0;
        level -= 5;
        return level;
    }

    public static void printGrid(int[][] grid) {
        for(int y = 0; y < grid.length; y++) {
            int[] row = grid[y];
            for(int x = 0; x < row.length; x++) {
                System.out.print(((row[x]>=0)?" ":"") + row[x]+" ");
            }
            System.out.println();
        }
    }
}
