package aoc;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3A {

    public static void main(String[] args) {
        List<String> claims = utils.readFile("day3.txt");
        HashMap<Integer, HashMap<Integer, Boolean>> matrix = new HashMap<>();
        Pattern pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
        for (String claim : claims) {
            Matcher matcher = pattern.matcher(claim);
            while(matcher.find()) {
                int x_1 = Integer.valueOf(matcher.group(2));
                int x_2 = x_1 + Integer.valueOf(matcher.group(4));
                int y_1 = Integer.valueOf(matcher.group(3));
                int y_2 = y_1 + Integer.valueOf(matcher.group(5));

                for(int y = y_1; y < y_2; y++) {
                    if(!matrix.containsKey(y)) {
                        matrix.put(y, new HashMap<>());
                    }
                    HashMap<Integer, Boolean> row = matrix.get(y);
                    for(int x = x_1; x < x_2; x++) {
                        if(row.containsKey(x)) {
                            row.put(x, true);
                        } else {
                            row.put(x, false);
                        }
                    }
                }
            }
        }

        int count = 0;
        for(HashMap<Integer, Boolean> row : matrix.values()) {
            for(Boolean cell: row.values()) {
                if(cell) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

}
