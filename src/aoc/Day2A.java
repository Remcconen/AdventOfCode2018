package aoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2A {

    public static void main(String[] args) {
        List<String> ids = utils.readFile("day2.txt");
        int two = 0;
        int three = 0;
        for(String id: ids) {
            Map<Character, Integer> chars = new HashMap<>();
            boolean foundTwo = false;
            boolean foundThree = false;
            for(char ch: id.toCharArray()) {
                if(chars.containsKey(ch)) {
                    chars.put(ch, chars.get(ch)+1);
                } else {
                    chars.put(ch, 1);
                }
            }
            for(Map.Entry<Character, Integer> entry : chars.entrySet()) {
                if(entry.getValue() == 2 && !foundTwo) {
                    two++;
                    foundTwo = true;
                }
                if(entry.getValue() == 3 && !foundThree) {
                    three++;
                    foundThree = true;
                }
            }
        }
        System.out.println(two*three);
    }
}
