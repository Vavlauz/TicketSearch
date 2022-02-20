package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketManager manager = new TicketManager(new TicketRepository());
    private final Ticket first = new Ticket(1, 1000, "LED", "GOJ", 4);
    private final Ticket second = new Ticket(2, 2000, "KUF", "OGZ", 10);
    private final Ticket third = new Ticket(3, 3000, "AGP", "AKL", 12);
    private final Ticket fourth = new Ticket(4, 4000, "KUF", "OGZ", 4);

    @Test
    void shouldSort() {
        Ticket[] expected = new Ticket[]{first, second, third};
        Ticket[] actual = new Ticket[]{third, first, second};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFind() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{second,fourth};
        Ticket[] actual = manager.findAllTickets("KUF", "OGZ");


        assertArrayEquals(expected, actual);
    }
}
