package aoc;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day9 {

    public static void main(String[] args) {
        List<String> input = utils.readFile("day9.txt");
        Pattern pattern = Pattern.compile("(\\d+) players; last marble is worth (\\d+) points");
        for(String s: input) {
            Matcher matcher = pattern.matcher(s);
            while(matcher.find()) {
                int elfs = Integer.valueOf(matcher.group(1));
                int marbles = Integer.valueOf(matcher.group(2));
                long[] scores = new long[elfs];

                List<Integer> circle = new ArrayList<>();

                int current = 0;
                int currentIndex = 0;
                circle.add(0);

                for(int marble = 1; marble <= marbles; marble++) {
                    if(marble % 100000 == 0) {
                        System.out.println(marble);
                    }
                    if(marble % 23 == 0) {
                        int newIndex = currentIndex-7;
                        if(currentIndex < 7) {
                            newIndex = circle.size()+newIndex;
//                            System.out.println(circle);
//                            System.out.println("Current index:" +currentIndex+" "+circle.get(currentIndex));
//                            System.out.println("Removed marble:" +newIndex+" "+circle.get(newIndex));
//                            System.out.println("Circle Size: "+circle.size());
//                            System.out.println(circle.get(circle.size()-1));
                        }
                        int removedMarble = circle.get(newIndex);
                        circle.remove(newIndex);
                        current = circle.get(newIndex);
                        currentIndex = newIndex;
                        scores[marble%elfs] += marble+removedMarble;
                    } else {
                        if(currentIndex+1 == circle.size()) {
                            circle.add(1, marble);
                            current = marble;
                            currentIndex = 1;
                        } else {
                            int newIndex = currentIndex+2;
                            if(newIndex < 0) {
                                System.out.println("error");
                            }
                            circle.add(newIndex, marble);
                            current = marble;
                            currentIndex += 2;
                        }
                    }
//                    System.out.println("["+(marble%elfs)+"] M "+marble+", i "+currentIndex+" "+circle);
//                    System.out.println("M "+marble+", i "+currentIndex);
                }
                long highscore = Arrays.stream(scores).max().orElse(0);
//                System.out.println(Arrays.toString(scores));
                System.out.println(elfs+" players; last marble is worth "+marbles+" points: high score is "+highscore);
            }
        }

    }

}
