package com.itbchallenge.eshop.repositories;

import com.itbchallenge.eshop.dtos.TicketDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TicketRepositoryImple implements TicketRepository{

    private HashMap<Integer, TicketDTO> ticketRepo = new HashMap<>();
    private AtomicInteger ticketId = new AtomicInteger();


    @Override
    public void addTicket(TicketDTO ticket) {
        ticketRepo.put(ticketId.incrementAndGet(),ticket);
    }


}
