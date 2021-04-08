package com.itbchallenge.eshop.services;

import com.itbchallenge.eshop.dtos.ArticlesDTO;
import com.itbchallenge.eshop.dtos.PurchaseRequestDTO;
import com.itbchallenge.eshop.dtos.TicketDTO;
import com.itbchallenge.eshop.exceptions.InvalidItemException;
import com.itbchallenge.eshop.exceptions.NotEnoughStockException;

public interface PurchaseRequestService {
    public TicketDTO generateTicket(ArticlesDTO articles) throws InvalidItemException, NotEnoughStockException;
    public PurchaseRequestDTO generatePurchaseRequest(TicketDTO ticket);
    public PurchaseRequestDTO addPurchaseRequest(ArticlesDTO articles) throws InvalidItemException, NotEnoughStockException;

    }
