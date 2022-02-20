package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Ticket;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketRepository {
    private Ticket[] items = new Ticket[0];

    public void save(Ticket item) {
        int length = items.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Ticket[] findAll(String fromAirport, String toAirport) {
        for (Ticket item : items) {
            if (item.getFromAirport().contains(fromAirport) && item.getToAirport().contains(toAirport)) {
                return new Ticket[]{item};
            }
        }
        return null;
    }

    public Ticket[] removeById(int id) {
        int length = items.length - 1;
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
        return tmp;
    }

    public Ticket findById(int id) {
        for (Ticket item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
