package aoc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7B {

    public static void main(String[] args) {
        int STEP_COST = 60;
        int WORKERS_COUNT = 5;
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

        int[] workers = new int[WORKERS_COUNT];
        String[] workersStep = new String[WORKERS_COUNT];
        int time = 0;
        boolean finished = false;

        while(!finished) {
            Set<String> newAvailable = new TreeSet<>();
            finished = true;
            for(int i = 0; i < workers.length; i++) {
                if(workersStep[i] == null) {
                    workersStep[i] = "";
                }
                workers[i]--;
                if(workers[i] <= 0) {
                    if(available.size() > 0 && workersStep[i].isEmpty()) {
                        finished = false;
                        String elem = ((TreeSet<String>) available).first();
                        available.remove(elem);
                        workersStep[i] = elem;
                        System.out.println("Worker #"+i+" starts "+elem+"(c="+((elem.toCharArray()[0]-64)-1)+", t="+time+")");
                        workers[i] = STEP_COST+(elem.toCharArray()[0]-64)-1;
                    }
                    if(!workersStep[i].isEmpty() && workers[i] <= 0) {
                        finished = false;
                        String elem = workersStep[i];
                        workersStep[i] = "";
                        order.add(elem);
                        System.out.println("Worker #"+i+" finished "+elem+" ("+time+")");
                        Iterator<Map.Entry<String, Set<String>>> iter = prerequisites.entrySet().iterator();
                        while(iter.hasNext()) {
                            Map.Entry<String, Set<String>> entry = iter.next();
                            if(entry.getValue().contains(elem)) {
                                entry.getValue().remove(elem);
                                if(entry.getValue().size() == 0) {
                                    newAvailable.add(entry.getKey());
                                    iter.remove();
                                }
                            }
                        }
                    }
                } else {
                    finished = false;
                }
            }
            available.addAll(newAvailable);
            newAvailable.clear();
            time++;
        }
        time--;
        System.out.println("Order:"+order);
        System.out.println("Time: "+time);
    }
}
