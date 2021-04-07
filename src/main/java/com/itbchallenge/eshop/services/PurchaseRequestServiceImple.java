package com.itbchallenge.eshop.services;

import com.itbchallenge.eshop.dtos.*;
import com.itbchallenge.eshop.exceptions.InvalidItemException;
import com.itbchallenge.eshop.repositories.ProductRepository;
import com.itbchallenge.eshop.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PurchaseRequestServiceImple implements PurchaseRequestService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ProductRepository productRepository;

    private AtomicInteger ticketId = new AtomicInteger();

    @Override
    public TicketDTO generateTicket(ArticlesDTO articles) throws InvalidItemException {
        TicketDTO ticket = new TicketDTO();
        ArrayList<TicketProductDTO> art = articles.getArticles();
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO();
        ArrayList<TicketProductDTO> newArts = new ArrayList<>();

        float totalPrice = 0;
        for (TicketProductDTO prod : art) {
            ProductDTO tmp = productRepository.getProductById(prod.getProductId());
            if (tmp != null) {
                totalPrice += tmp.getPrice() * tmp.getQuantity();
                newArts.add(prod);
            } else {
                throw new InvalidItemException();
            }
        }
        ticket.setTotal(totalPrice);
        ticket.setArticles(newArts);
        ticket.setId(this.ticketId.incrementAndGet());
        return new TicketDTO(this.ticketId.incrementAndGet(),newArts,totalPrice);


    }

    public PurchaseRequestDTO generatePurchaseRequest(TicketDTO ticket){
        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO();
        if (ticket.getId()==0){
            statusCodeDTO.setCode(HttpStatus.NOT_FOUND);
            statusCodeDTO.setMessage("Could not find some of the items in the request.");
        }else
            purchaseRequestDTO.setTicket(ticket);
            statusCodeDTO.setCode(HttpStatus.OK);
            statusCodeDTO.setMessage("La solicitud de compra se completo con exito.");

        return purchaseRequestDTO;
    }

    public PurchaseRequestDTO addPurchaseRequest(ArticlesDTO articles) throws InvalidItemException {
        TicketDTO ticketDTO = generateTicket(articles);
        return generatePurchaseRequest(ticketDTO);

    }

}
