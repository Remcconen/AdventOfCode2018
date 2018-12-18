package aoc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day4 {

    public static void main(String[] args) {
        List<String> records = utils.readFile("day4.txt");
        Collections.sort(records);

        // SORT RECORDS
        HashMap<Integer, int[]> guards = new HashMap<>();

        Pattern pattern = Pattern.compile("\\[\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:(\\d\\d)] (.*)");
        Pattern pattern2 = Pattern.compile("Guard #(\\d+) .*");

        int guard = 0;
        int asleep = 0;
        for(String record: records) {
            Matcher matcher = pattern.matcher(record);
            while(matcher.find()) {
                String line = matcher.group(2);
                if(line.equals("falls asleep")) {
                    asleep = Integer.valueOf(matcher.group(1));
                } else if(line.equals("wakes up")) {
                    int[] sleepRecord = guards.get(guard);
                    for(int i = asleep; i < Integer.valueOf(matcher.group(1)); i++) {
                        sleepRecord[i]++;
                    }
                    guards.put(guard, sleepRecord);
                }
                if(line.startsWith("Guard")) {
                    Matcher matcher2 = pattern2.matcher(line);
                    while(matcher2.find()) {
                        guard = Integer.valueOf(matcher2.group(1));
                        if(!guards.containsKey(guard)) {
                            guards.put(guard, new int[60]);
                        }
                    }
                }
            }
        }

        int mostSleepOverall = 0;
        int mostSleepGuardOverall = 0;
        int mostSleepMax = 0;
        int mostSleepGuardMax = 0;

        for(Map.Entry<Integer, int[]> entry : guards.entrySet()) {
            int sleepOverall = IntStream.of(entry.getValue()).sum();
            int sleepMax = IntStream.of(entry.getValue()).max().orElse(-1);
            if(sleepOverall > mostSleepOverall) {
                mostSleepOverall = sleepOverall;
                mostSleepGuardOverall = entry.getKey();
            }
            if(sleepMax > mostSleepMax) {
                mostSleepMax = sleepMax;
                mostSleepGuardMax = entry.getKey();
            }
            System.out.println("Guard #"+entry.getKey()+": Sum: "+ IntStream.of(entry.getValue()).sum()
                    +", Max: "+ IntStream.of(entry.getValue()).max().orElse(0)
                    +", "+Arrays.toString(entry.getValue()));

        }

        int maxAtOverall = 0;
        int[] sleepsOverall = guards.get(mostSleepGuardOverall);

        int maxAtMax = 0;
        int[] sleepsMax = guards.get(mostSleepGuardMax);

        for(int i = 0; i < 60; i++) {
            maxAtOverall = sleepsOverall[i] > sleepsOverall[maxAtOverall] ? i : maxAtOverall;
            maxAtMax = sleepsMax[i] > sleepsMax[maxAtMax] ? i : maxAtMax;
        }

        System.out.println("Sleeps most (Part A): "+mostSleepGuardOverall + " x "+maxAtOverall+ " = "+mostSleepGuardOverall*maxAtOverall);
        System.out.println("Sleeps max (Part B): "+mostSleepGuardMax + " x "+maxAtMax+ " = "+mostSleepGuardMax*maxAtMax);

    }


}
