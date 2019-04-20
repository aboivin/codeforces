package D1141;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Boots {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] numbersAsString = input.lines()
                                        .skip(1)
                                        .toArray(String[]::new);
        compute(numbersAsString);
    }

    static void compute(String[] numbersAsString) {
        String[] first = numbersAsString[0].split("");
        String[] second = numbersAsString[1].split("");
        PriorityQueue<CharIndex> q1 = new PriorityQueue<>();
        PriorityQueue<CharIndex> q2 = new PriorityQueue<>();
        for (int i = 0; i < first.length; i++) {
            q1.add(new CharIndex(first[i], i));
            q2.add(new CharIndex(second[i], i));
        }

        Stack<CharIndex> wildcard1 = new Stack<>();
        Stack<CharIndex> wildcard2 = new Stack<>();
        Stack<CharIndex> temp1 = new Stack<>();
        Stack<CharIndex> temp2 = new Stack<>();
        List<Pair> result = new ArrayList<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            CharIndex peek1 = q1.peek();
            CharIndex peek2 = q2.peek();

            if(peek1 == null) {
                if (peek2.character.equals("?")) {
                    wildcard2.add(q2.poll());
                } else {
                    temp2.add(q2.poll());
                }
                continue;

            }
            if(peek2 == null) {
                if (peek1.character.equals("?")) {
                    wildcard1.add(q1.poll());
                } else {
                    temp1.add(q1.poll());
                }
                continue;
            }

            if (peek1.character.equals("?")) {
                wildcard1.add(q1.poll());
                continue;
            }
            if (peek2.character.equals("?")) {
                wildcard2.add(q2.poll());
                continue;
            }

            int compare = peek1.compareTo(peek2);
            if (compare > 0) {
                temp2.add(q2.poll());
            } else if (compare < 0) {
                temp1.add(q1.poll());
            } else {
                result.add(new Pair(q1.poll().index, q2.poll().index));
            }
        }

        while(!wildcard1.isEmpty()) {
            if(temp2.isEmpty()) {
                break;
            }
            result.add(new Pair(wildcard1.pop().index, temp2.pop().index));
        }
        while(!wildcard2.isEmpty()) {
            if(temp1.isEmpty()) {
                break;
            }
            result.add(new Pair(temp1.pop().index, wildcard2.pop().index));
        }
        while(!wildcard1.isEmpty()) {
            if(wildcard2.isEmpty()) {
                break;
            }
            result.add(new Pair(wildcard1.pop().index, wildcard2.pop().index));
        }

        System.out.println(result.size());
        result.forEach(System.out::println);
    }

    private static class CharIndex implements Comparable<CharIndex> {
        String character;
        int index;

        public CharIndex(String character, int index) {
            this.character = character;
            this.index = index;
        }

        @Override
        public int compareTo(CharIndex that) {
            if (this.character.equals("?")) {
                return -1;
            }
            if (that.character.equals("?")) {
                return 1;
            }
            return Integer.compare(this.character.charAt(0), that.character.charAt(0));
        }
    }

    private static class Pair {
        int index1;
        int index2;

        public Pair(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public String toString() {
            return  (index1 + 1)+ " " + (index2 + 1);
        }
    }
}
