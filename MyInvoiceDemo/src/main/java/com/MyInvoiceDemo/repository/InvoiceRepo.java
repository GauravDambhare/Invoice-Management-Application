package com.MyInvoiceDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MyInvoiceDemo.entity.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice, Long>{

}
