package aoc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day5A {

    public static void main(String[] args) {
        char[] polymer = utils.readFile("day5.txt").get(0).toCharArray();
        Stack<Character> stack = new Stack<>();

        for(char c: polymer) { //int i = 0; i < polymer.length; i++) {
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
        System.out.println(Arrays.toString(stack.toArray()));
        System.out.println("Size: "+stack.size());
    }


}
