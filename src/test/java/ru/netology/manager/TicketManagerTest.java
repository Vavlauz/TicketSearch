package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.comporator.TicketByTravelTimeAscComparator;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketManager manager = new TicketManager(new TicketRepository());
    private TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();
    private final Ticket first = new Ticket(1, 1000, "LED", "GOJ", 4);
    private final Ticket second = new Ticket(2, 6000, "KUF", "OGZ", 10);
    private final Ticket third = new Ticket(3, 3000, "AGP", "AKL", 12);
    private final Ticket fourth = new Ticket(4, 4000, "KUF", "OGZ", 4);
    private final Ticket fifth = new Ticket(5, 4000, "KUF", "OGZ", 4);

    @Test
    void shouldSort() {
        Ticket[] expected = new Ticket[]{first, third, fourth, fifth, second};
        Ticket[] actual = new Ticket[]{third, first, second, fourth, fifth};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSortAndComparable() {
        Ticket[] expected = new Ticket[]{first, fourth, fifth, second, third};
        Ticket[] actual = new Ticket[]{third, first, second, fourth, fifth};

        Arrays.sort(actual, comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndNoCoincidences() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);
        manager.saveTicket(fifth);


        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("KUF", "AKL", comparator);

        Arrays.sort(actual, comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindCoincidences() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);
        manager.saveTicket(fifth);


        Ticket[] expected = new Ticket[]{fourth, second};
        Ticket[] actual = manager.searchBy("KUF", "OGZ", comparator);

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindCoincidencesWithComparator() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);
        manager.saveTicket(fifth);


        Ticket[] expected = new Ticket[]{fourth, fifth, second};
        Ticket[] actual = manager.searchBy("KUF", "OGZ", comparator);

        Arrays.sort(actual, comparator);

        assertArrayEquals(expected, actual);
    }


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
