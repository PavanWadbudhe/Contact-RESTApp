package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.ContactForm;
import in.ashokit.entity.Contact;
import in.ashokit.repository.ContactDetailsRepo;

@Service("contactService")
public class ContactMgmtServiceImpl implements ContactMgmtService {
	@Autowired
	private ContactDetailsRepo contactRepo;
	
	private String msg="Contacts not available";

	@Override
	public String saveContact(ContactForm form) {
		/*here we get the data submitted by user as ContactForm
	 	but save methos can expect the entity class obj
		so we need to copy the ContactForm data to Contact class obj  */
		Contact contact=new  Contact();
		//copy the data to Contact class obj
		BeanUtils.copyProperties(form, contact);
		contact.setActiveSW("true");
		
		//use repo to save
		int id=contactRepo.save(contact).getContactId();
		if(contact.getContactId() != null)
			return "Contact saved successfully with id value ::"+id;
		else 
			return "Failed to save the contact";
	}

	@Override
	public List<ContactForm> fetchAllContact() throws IllegalArgumentException{
		
		List<ContactForm> formList=new ArrayList<>();
		//use repo
		List<Contact> contactList=contactRepo.findAll();
		
		if(!(contactList.isEmpty())){
			for(Contact con : contactList) {
				ContactForm form=new  ContactForm();
				//copy the data using BeansUtils class
				BeanUtils.copyProperties(con, form);
				//add the contact to formList
				formList.add(form);
			}
			return formList;
		}
		throw new IllegalArgumentException(msg);
		
	}

	@Override
	public ContactForm editContact(Integer id) throws IllegalArgumentException {
		//get data base on the id
		Optional<Contact> opt=contactRepo.findById(id);
		if(opt.isPresent()) {
			Contact entity=opt.get();
			//create ContactForm obj
			ContactForm form=new ContactForm();
			//copy the data
			BeanUtils.copyProperties(entity, form);
			return form;
		}
		throw new IllegalArgumentException(msg);
		
	}

	@Override
	public List<ContactForm> removeContact(Integer id) throws IllegalArgumentException {
		Optional<Contact> opt=contactRepo.findById(id);
		if(opt.isPresent()) {
			//delete the record
			contactRepo.deleteById(id);
			return fetchAllContact();   //use same method here use for display all contact
		}
		throw new IllegalArgumentException(msg);
	}

}
