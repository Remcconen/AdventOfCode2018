package aoc;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;
import java.util.stream.IntStream;

public class Day5B {

    public static void main(String[] args) {
        char[] polymer = utils.readFile("day5.txt").get(0).toCharArray();
        HashSet<Integer> sizes = new HashSet<>();

        for(char c = 'a'; c <= 'z'; c++) {
            char[] result = Arrays.copyOf(polymer, polymer.length);
            result = removeChar(c, result);
            sizes.add(reactPolymer(result));
        }

        System.out.println(Collections.min(sizes));
    }

    private static int reactPolymer(char[] poly) {
        Stack<Character> stack = new Stack<>();
        for(char c: poly) {
            if(stack.size() == 0) {
                stack.push(c);
            } else if(Character.toUpperCase(stack.peek()) == Character.toUpperCase(c) && (
                    Character.isUpperCase(stack.peek()) && Character.isLowerCase(c) ||
                            Character.isLowerCase(stack.peek()) && Character.isUpperCase(c))) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
//        System.out.println(Arrays.toString(stack.toArray()));
        return stack.size();
    }

    private static char[] removeChar(char c, char[] poly) {
        String result = new String(poly);
        String character = Character.toString(c);
        result = result.replace(character, "");
        result = result.replace(character.toUpperCase(), "");
//        System.out.println("Char "+c+": "+result);
        return result.toCharArray();
    }


}
