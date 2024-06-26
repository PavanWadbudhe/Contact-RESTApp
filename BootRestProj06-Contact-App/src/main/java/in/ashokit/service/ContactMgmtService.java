package in.ashokit.service;

import java.util.List;

import in.ashokit.bindings.ContactForm;

public interface ContactMgmtService {
	
	public String saveContact(ContactForm form);
	public List<ContactForm> fetchAllContact() throws IllegalArgumentException;
	public ContactForm editContact(Integer id) throws IllegalArgumentException;
	public List<ContactForm> removeContact(Integer id) throws IllegalArgumentException;

}
