package ru.netology.manager;

import ru.netology.comporator.TicketByTravelTimeAscComparator;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void saveTicket(Ticket ticket) {
        repository.save(ticket);
    }

//    public Ticket[] searchBy(String from, String to) {
//        Ticket[] result = new Ticket[0];
//        for (Ticket ticket : repository.findAll()) {
//            if (matches(ticket, from, to)) {
//                int length = result.length + 1;
//                Ticket[] tmp = new Ticket[length];
//                System.arraycopy(result, 0, tmp, 0, result.length);
//                int lastIndex = tmp.length - 1;
//                tmp[lastIndex] = ticket;
//                result = tmp;
//            }
//        }
//        return result;
//    }

    public Ticket[] searchBy(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }

    public boolean matches(Ticket ticket, String fromAirport, String toAirport ) {
        if (ticket.getFromAirport().contains(fromAirport) && ticket.getToAirport().contains(toAirport)){
            return true;
        } else {
            return false;
        }
    }

    public Ticket[] removeByIdTickets(int id) {
        return repository.removeById(id);
    }

}
