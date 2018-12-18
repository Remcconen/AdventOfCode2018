package aoc;

import java.util.List;

public class Day1A {

    public static void main(String[] args) {
        String input = "day1.txt";
        List<String> numbers = utils.readFile(input);
        int result = 0;
        for(String s : numbers) {
            result += Integer.valueOf(s);
        }
        System.out.println(result);
    }
}
