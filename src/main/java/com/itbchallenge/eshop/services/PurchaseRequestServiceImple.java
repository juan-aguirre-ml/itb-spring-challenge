package com.itbchallenge.eshop.services;

import com.itbchallenge.eshop.dtos.*;
import com.itbchallenge.eshop.exceptions.InvalidItemException;
import com.itbchallenge.eshop.exceptions.NotEnoughStockException;
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

    private final AtomicInteger ticketId = new AtomicInteger();

    @Override
    public TicketDTO generateTicket(ArticlesDTO articles) throws InvalidItemException, NotEnoughStockException {
        TicketDTO ticket = new TicketDTO();
        ArrayList<TicketProductDTO> art = articles.getArticles();
        ArrayList<TicketProductDTO> newArts = new ArrayList<>();

        float totalPrice = 0;
        for (TicketProductDTO prod : art) {
            ProductDTO tmp = productRepository.getProductById(prod.getProductId());
            if (tmp != null) { //If product exists
                if (tmp.getQuantity() >= prod.getQuantity()) { //Check Stock
                    totalPrice += tmp.getPrice() * prod.getQuantity();
                    newArts.add(prod);
                }else
                    throw new NotEnoughStockException();
            } else {
                throw new InvalidItemException();
            }
        }
        return new TicketDTO(this.ticketId.incrementAndGet(),newArts,totalPrice);


    }

    public PurchaseRequestDTO generatePurchaseRequest(TicketDTO ticket){
        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO();
        if (ticket.getId()==0){
            statusCodeDTO.setCode(HttpStatus.NOT_FOUND.value());
            statusCodeDTO.setMessage("Could not find some of the items in the request.");
        }else
            purchaseRequestDTO.setTicket(ticket);
            statusCodeDTO.setCode(HttpStatus.OK.value());
            statusCodeDTO.setMessage("La solicitud de compra se completo con exito.");

            purchaseRequestDTO.setStatusCode(statusCodeDTO);
        return purchaseRequestDTO;
    }

    public PurchaseRequestDTO addPurchaseRequest(ArticlesDTO articles) throws InvalidItemException, NotEnoughStockException {
        TicketDTO ticketDTO = generateTicket(articles);
        return generatePurchaseRequest(ticketDTO);

    }

}
