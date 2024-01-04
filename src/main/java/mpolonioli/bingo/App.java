package mpolonioli.bingo;

import mpolonioli.bingo.ticket.TicketGenerator;

import java.util.*;

public class App {
    public static void main(String[] args) {

        // Generate ticket strip
        TicketGenerator ticketGenerator = new TicketGenerator();
        Set<Set<Integer>> ticketStripNumbers = ticketGenerator.generateTicketStrip();

        // Print the tickets
        System.out.println(ticketStripNumbers);
    }
}