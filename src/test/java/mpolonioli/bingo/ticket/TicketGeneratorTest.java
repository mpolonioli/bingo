package mpolonioli.bingo.ticket;


import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketGeneratorTest {

    private static final List<List<Integer>> sequences = List.of(
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
            List.of(10, 11, 12, 13, 14, 15, 16, 17, 18, 19),
            List.of(20, 21, 22, 23, 24, 25, 26, 27, 28, 29),
            List.of(30, 31, 32, 33, 34, 35, 36, 37, 38, 39),
            List.of(40, 41, 42, 43, 44, 45, 46, 47, 48, 49),
            List.of(50, 51, 52, 53, 54, 55, 56, 57, 58, 59),
            List.of(60, 61, 62, 63, 64, 65, 66, 67, 68, 69),
            List.of(70, 71, 72, 73, 74, 75, 76, 77, 78, 79),
            List.of(80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90));

    @Test
    public void generateTicketTest() {

        // Initialize the ticket generator
        TicketGenerator ticketGenerator = new TicketGenerator();

        // Generate a random ticket
        Set<Integer> ticket = ticketGenerator.generateTicket();

        // Check if ticket is well-formed
        checkTicket(ticket);
    }

    @Test
    public void generateTicketStripTest() {

        // Initialize the ticket generator
        TicketGenerator ticketGenerator = new TicketGenerator();

        // Generate a random ticket
        Set<Set<Integer>> ticketStrip = ticketGenerator.generateTicketStrip();

        // Check if ticket is well-formed
        checkTicketStrip(ticketStrip);
    }

    @Test
    public void consistencyTest() {

        // Run the well-formed ticket generation tests a lot of times
        for (int i = 0; i <= 1000000; i++) {
            generateTicketTest();
            generateTicketStripTest();
        }

    }

    /**
     * Checks that the given ticket is well-formed
     */
    private void checkTicket(Set<Integer> ticket) {

        // Check ticket size
        assertEquals(15, ticket.size());

        // Check that ticket has at least 1 and at most 3 numbers from each sequence
        // and that contains only numbers from 1 to 90
        for (List<Integer> sequence: sequences) {
            int count = 0;
            for (Integer value: sequence) {
                assertTrue(value >= 1 && value <= 90);
                if (ticket.contains(value)) {
                    count++;
                }
            }
            assertTrue(count >= 1 && count <= 3);
        }
    }

    /**
     * Checks that the given ticket strips is well-formed
     */
    private void checkTicketStrip(Set<Set<Integer>> ticketStrip) {

        // Check number of tickets in strip
        assertEquals(6, ticketStrip.size());

        // Check that all the numbers from 1 to 90 are used in strip
        // and that all the tickets are well-formed
        Set<Integer> numberUsedInStrip = new HashSet<>();
        ticketStrip.forEach(ticket -> {
            checkTicket(ticket);
            numberUsedInStrip.addAll(ticket);
        });
        assertEquals(90, numberUsedInStrip.size());
        for (List<Integer> sequence :sequences) {
            assertTrue(numberUsedInStrip.containsAll(sequence));
        }
    }

}
