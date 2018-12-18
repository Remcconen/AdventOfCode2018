package aoc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1B {

    public static void main(String[] args) {
        String input = "day1.txt";
        List<String> numbers = utils.readFile(input);
        int freq = 0;
        boolean found = false;

        Set<Integer> encountered = new HashSet<>();
        while(!found) {
            for (String s : numbers) {
                encountered.add(freq);

                freq += Integer.valueOf(s);
                if (encountered.contains(freq)) {
                    found = true;
                    break;
                }
            }
        }
        System.out.println(freq);
    }
}
