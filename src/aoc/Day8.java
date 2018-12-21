package aoc;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Day8 {

    public static void main(String[] args) {
        List<String> input = utils.readFile("day8.txt");
        int[] input_int = Stream.of(input.get(0).split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> numbers = new ArrayList<>();
        for(int i : input_int) {
            numbers.add(i);
        }
        Pair<Node, List<Integer>> result = createNode(numbers);
        result.getKey().printNode(0);
        System.out.println("Sum metadata: "+result.getKey().sumMetadata());
        System.out.println("Sum metadata childs: "+result.getKey().sumMetadataChilds());
    }

    public static Pair<Node, List<Integer>> createNode(List<Integer> input) {
        Node node = new Node(input.get(0), input.get(1));
        input.remove(1);
        input.remove(0);

        for(int i = 0; i < node.nr_child_nodes; i++) {
            Pair<Node, List<Integer>> pair = createNode(input);
            input = pair.getValue();
            node.addChild(pair.getKey());
        }
        for(int i = 0; i < node.nr_metadata_entries; i++) {
            node.addMetadata(input.get(0));
            input.remove(0);
        }
        return new Pair<>(node, input);
    }

    public static class Node {
        int nr_child_nodes;
        int nr_metadata_entries;
        List<Node> child_nodes;
        List<Integer> metadata;

        public Node(int _nr_child_nodes, int _nr_metadata_entries) {
            nr_child_nodes = _nr_child_nodes;
            nr_metadata_entries = _nr_metadata_entries;
            child_nodes = new LinkedList<>();
            metadata = new LinkedList<>();
        }

        public void addChild(Node node) {
            child_nodes.add(node);
        }

        public void addMetadata(int _metadata) {
            metadata.add(_metadata);
        }

        public void printNode(int depth) {
            for(int i = 0; i < depth; i++) {
                System.out.print(" ");
            }
            System.out.println("Node! Childs: "+nr_child_nodes+" Metadata: "+metadata);
            int i = depth+1;
            for(Node child: child_nodes) {
                child.printNode(i);
            }
        }

        public int sumMetadata() {
            int sum = 0;
            sum += metadata.stream().mapToInt(Integer::intValue).sum();
            for(Node child : child_nodes) {
                sum += child.sumMetadata();
            }
            return sum;
        }

        public int sumMetadataChilds() {
            this.printNode(0);
            int sum = 0;
            if(nr_child_nodes == 0) {
                sum = metadata.stream().mapToInt(Integer::intValue).sum();
            } else {
                for (int data : metadata) {
                    if (data > 0 && data <= nr_child_nodes) {
                        sum += child_nodes.get(data - 1).sumMetadataChilds();
                    }
                }
            }
            return sum;
        }
    }
}
