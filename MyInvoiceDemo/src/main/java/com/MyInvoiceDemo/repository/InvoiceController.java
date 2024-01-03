package com.MyInvoiceDemo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MyInvoiceDemo.entity.Invoice;
import com.MyInvoiceDemo.service.InvoiceService;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService service;

	@GetMapping("/")
	public String showHomePage() {

		return "homePage";
	}

	@GetMapping("showRegistration")
	public String showRegistration() {

		return "registrationPage";

	}

	@PostMapping("saveInvoice")
	public String saveInvoice(@ModelAttribute Invoice invoice, Model model) {

//	service.saveInvoice(invoice);

		Long id = service.saveInvoice(invoice).getId();
		String message = "Record with id : '" + id + "' is saved successfully !";
		model.addAttribute("message", message);

		return "registrationPage";
	}

	@GetMapping("getAllInvoice")
	public String getAllInvoice(@RequestParam(value = "message", required = false) String message, Model model) {

		List<Invoice> invoices = service.getAllInvoice();
		model.addAttribute("list", invoices);
		model.addAttribute("message", message);
		return "allInvoicesPage";

	}

	@GetMapping("/edit")
	public String getEditPage(Model model, RedirectAttributes attributes, @RequestParam Long id) {
		String page = null;
		try {
			Invoice invoice = service.getInvoiceById(id);
			model.addAttribute("invoice", invoice);
			page = "editInvoicePage";
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:getAllInvoice";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateInvoice(@ModelAttribute Invoice invoice, RedirectAttributes attributes) {
		service.updateInvoiceById(invoice);
		Long id = invoice.getId();
		attributes.addAttribute("message", "Invoice with id: '" + id + "' is updated successfully !");
		return "redirect:getAllInvoice";
	}

	@GetMapping("/delete")
	public String deleteInvoice(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deleteInvoiceById(id);
			attributes.addAttribute("message", "Invoice with Id : '" + id + "' is removed successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:getAllInvoice";
	}

}
