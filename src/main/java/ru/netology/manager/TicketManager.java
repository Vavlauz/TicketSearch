package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void saveTicket(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] findAllTickets(String fromAirport, String toAirport) {
        return repository.findAll(fromAirport, toAirport);
    }

    public Ticket[] removeByIdTickets(int id) {
        return repository.removeById(id);
    }

}
