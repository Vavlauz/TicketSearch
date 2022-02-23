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
    private final Ticket fourth = new Ticket(4, 4000, "KUF", "OGZ", 5);

    @Test
    void shouldSort() {
        Ticket[] expected = new Ticket[]{first, second, third};
        Ticket[] actual = new Ticket[]{third, first, second};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndNoCoincidences() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("KUF", "AKL");
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindCoincidences() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{second, fourth};
        Ticket[] actual = manager.searchBy("KUF", "OGZ");
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

//    @Test
//    void shouldFindAndNoCoincidences() {
//        manager.saveTicket(first);
//        manager.saveTicket(second);
//        manager.saveTicket(third);
//        manager.saveTicket(fourth);
//
//
//        Ticket[] expected = new Ticket[]{};
//        Ticket[] actual = manager.searchBy("KUF", "AKL",);
//        Arrays.sort(actual, TicketByPriceAscComparator);
//
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    void shouldFindCoincidences() {
//        manager.saveTicket(first);
//        manager.saveTicket(second);
//        manager.saveTicket(third);
//        manager.saveTicket(fourth);
//
//
//        Ticket[] expected = new Ticket[]{second, fourth};
//        Ticket[] actual = manager.searchBy("KUF", "OGZ");
//
//
//        assertArrayEquals(expected, actual);
//    }

    @Test
    void shouldRemove() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{first, third, fourth};
        Ticket[] actual = manager.removeByIdTickets(2);


        assertArrayEquals(expected, actual);
    }
}
