package mpolonioli.bingo;

import mpolonioli.bingo.ticket.TicketGenerator;

import java.util.*;

public class App {
    public static void main(String[] args) {

        // Generate ticket series
        TicketGenerator ticketGenerator = new TicketGenerator();
        Set<Set<Integer>> ticketSeries = ticketGenerator.generateTicketSeries();

        // Print the tickets
        System.out.println(ticketSeries);
    }
}