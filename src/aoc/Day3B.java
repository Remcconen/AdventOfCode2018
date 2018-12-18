package aoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3B {

    public static void main(String[] args) {
        List<String> claims = utils.readFile("day3.txt");
        HashMap<Integer, Boolean> validClaims = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Integer>> matrix = new HashMap<>();
        Pattern pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
        for (String claim : claims) {
            Matcher matcher = pattern.matcher(claim);
            while(matcher.find()) {
                int claim_id = Integer.valueOf(matcher.group(1));
                validClaims.put(claim_id, true);
                int x_1 = Integer.valueOf(matcher.group(2));
                int x_2 = x_1 + Integer.valueOf(matcher.group(4));
                int y_1 = Integer.valueOf(matcher.group(3));
                int y_2 = y_1 + Integer.valueOf(matcher.group(5));

                for(int y = y_1; y < y_2; y++) {
                    if(!matrix.containsKey(y)) {
                        matrix.put(y, new HashMap<>());
                    }
                    HashMap<Integer, Integer> row = matrix.get(y);
                    for(int x = x_1; x < x_2; x++) {
                        if(row.containsKey(x)) {
                            if(row.get(x) != -1) {
                                validClaims.put(row.get(x), false);
                                row.put(x, -1);
                            }
                            validClaims.put(claim_id, false);
                        } else {
                            row.put(x, claim_id);
                        }
                    }
                }
            }
        }

        for(Map.Entry<Integer, Boolean> claim : validClaims.entrySet()) {
            if(claim.getValue()) {
                System.out.println(claim.getKey());
            }
        }
    }

}
