package aoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2B {

    public static void main(String[] args) {
        List<String> ids = utils.readFile("day2.txt");
        char[] result1 = null;
        char[] result2 = null;

        for(String id : ids) {
            char[] id_1 = id.toCharArray();
            for(String id2 : ids) {

                int diff = 0;
                char[] id_2 = id2.toCharArray();
                for(int i = 0; i < id_1.length; i++) {
                    if(id_1[i] != id_2[i]) {
                        diff++;
                    }
                    if(diff > 1) {
                        break;
                    }
                }
                if(diff == 1) {
                    result2 = id_2;
                    break;
                }
            }
            if(result2 != null) {
                result1 = id_1;
                break;
            }
        }
        System.out.println(result1);
        System.out.println(result2);
        for(int i = 0; i < result1.length; i++ ) {
            if(result1[i] == result2[i]) {
                System.out.print(result1[i]);
            }
        }
    }
}
