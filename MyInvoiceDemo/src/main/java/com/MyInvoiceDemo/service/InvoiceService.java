package com.MyInvoiceDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyInvoiceDemo.entity.Invoice;
import com.MyInvoiceDemo.repository.InvoiceRepo;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepo repo; 
	
	
public Invoice saveInvoice(Invoice invoice) {
		
		return 	 repo.save(invoice);
			
			}

	public List<Invoice> getAllInvoice() {
		
		return repo.findAll();
	}

	public Invoice getInvoiceById(Long id) {
		
		Optional<Invoice> op  =	repo.findById(id);  
		
		if (op.isPresent()) {
			
			return op.get();
			}
		else throw new RuntimeException ("Invoice with Id : "+id+" Not Found");
		}

	
	public void deleteInvoiceById(Long id) {
		repo.delete(getInvoiceById(id)); 
		}

	public void updateInvoiceById(Invoice invoice) {
		
		repo.save(invoice);
	}
	

}
