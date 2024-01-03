package mpolonioli.bingo;

import java.util.*;

public class App {
    public static void main(String[] args) {

        // Prepare the initial sequences of values from 1 to 90
        List<List<Integer>> sequences = new ArrayList<>(List.of(
                new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)),
                new ArrayList<>(List.of(10, 11, 12, 13, 14, 15, 16, 17, 18, 19)),
                new ArrayList<>(List.of(20, 21, 22, 23, 24, 25, 26, 27, 28, 29)),
                new ArrayList<>(List.of(30, 31, 32, 33, 34, 35, 36, 37, 38, 39)),
                new ArrayList<>(List.of(40, 41, 42, 43, 44, 45, 46, 47, 48, 49)),
                new ArrayList<>(List.of(50, 51, 52, 53, 54, 55, 56, 57, 58, 59)),
                new ArrayList<>(List.of(60, 61, 62, 63, 64, 65, 66, 67, 68, 69)),
                new ArrayList<>(List.of(70, 71, 72, 73, 74, 75, 76, 77, 78, 79)),
                new ArrayList<>(List.of(80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90))
        ));

        // Prepare the tickets variable
        Set<Set<Integer>> tickets = new HashSet<>();

        for (int i = 1; i <= 6; i++) {

            // Prepare the ticket variable
            Set<Integer> ticket = new HashSet<>();

            // Add one random number from each sequence to the ticket (9)
            for (List<Integer> sequence: sequences) {
                ticket.add(sequence.remove(new Random().nextInt(sequence.size())));
            }

            // Consider only the sequences that contains fewer numbers than the remaining tickets to generate
            int finalI = i;
            List<List<Integer>> availableSequences = new ArrayList<>(sequences.stream()
                    .filter(s -> s.size() > 6 - finalI)
                    .toList());

            // Add the remaining 6 (15 - 9) numbers to the ticket
            for (int j = 0; j < 6; j++) {

                // Select the largest sequence from the available ones
                // This ensures to not exhaust the available sequences during this for loop
                int sequenceIndex = 0;
                for (int k = 1; k < availableSequences.size(); k++) {
                   if (availableSequences.get(k).size() > availableSequences.get(sequenceIndex).size()) {
                       sequenceIndex = k;
                   }
                }
                List<Integer> sequence = availableSequences.get(sequenceIndex);

                // Select and remove a random value from the selected sequence
                Integer value = sequence.remove(new Random().nextInt(sequence.size()));

                // Add the selected value to the ticket
                ticket.add(value);

                // Remove the selected value also from sequences
                sequences.forEach(s -> s.remove(value));

                // Count how many values of the same original sequence is present in ticket
                int valuesInSequence = (int) ticket.stream()
                        .filter(n -> n / 10 == (value == 90 ? value - 1 : value) / 10)
                        .count();

                // Remove the sequence from the available ones if:
                // - the ticket contains at least 3 values from that sequence
                // OR
                // - the sequence contains fewer numbers than the remaining tickets to generate
                if (valuesInSequence >= 3 || sequence.size() <= 6 - i) {
                    availableSequences.remove(sequenceIndex);
                }
            }

            // Add the generated ticket in tickets
            tickets.add(ticket);
        }

        // Print the tickets
        System.out.println(tickets);
    }
}