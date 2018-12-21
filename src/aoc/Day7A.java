package aoc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7A {

    public static void main(String[] args) {
        List<String> orders = utils.readFile("day7.txt");
        HashMap<String, Set<String>> prerequisites = new HashMap<>();
        Set<String> steps = new TreeSet<>();

        Pattern pattern = Pattern.compile("Step (\\D) must be finished before step (\\D) can begin.");

        for(String order: orders) {
            Matcher matcher = pattern.matcher(order);
            while (matcher.find()) {
                steps.add(matcher.group(1));
                steps.add(matcher.group(2));
                Set<String> vertices;
                if (prerequisites.containsKey(matcher.group(2))) {
                    vertices = prerequisites.get(matcher.group(2));
                } else {
                    vertices = new HashSet<>();
                }
                vertices.add(matcher.group(1));
                prerequisites.put(matcher.group(2), vertices);
            }
        }
        List<String> order = new LinkedList<>();
        Set<String> available = new TreeSet<>();
        for(String step : steps) {
            if(!prerequisites.containsKey(step)) {
                available.add(step);
            }
        }
        while(available.size() != 0) {
            String elem = ((TreeSet<String>) available).first();
            available.remove(elem);
            order.add(elem);
            Iterator<Map.Entry<String, Set<String>>> iter = prerequisites.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry<String, Set<String>> entry = iter.next();
                if(entry.getValue().contains(elem)) {
                    entry.getValue().remove(elem);
                    if(entry.getValue().size() == 0) {
                        available.add(entry.getKey());
                        iter.remove();
                    }
                }
            }
        }
        System.out.println("Order: "+order);
        for(String s : order) {
            System.out.print(s);
        }
    }
}
