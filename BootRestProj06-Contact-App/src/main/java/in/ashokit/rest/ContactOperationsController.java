package in.ashokit.rest;

import java.util.List;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.ContactForm;
import in.ashokit.service.ContactMgmtService;


@RestController
@RequestMapping("/contact-api")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactOperationsController {
	@Autowired
	private ContactMgmtService contactService;
	
	
	@PostMapping("/save")
	public ResponseEntity<String> saveContactInfo(@RequestBody ContactForm form){
		//use Service
		String resultMsg=contactService.saveContact(form);
		return new ResponseEntity<>(resultMsg, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/contacts")
	public ResponseEntity<?> showAllContacts(){
		try {
			//use service
			List<ContactForm> allContact=contactService.fetchAllContact();
			return new ResponseEntity<>(allContact, HttpStatus.OK);
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/edit/{contactId}")
	public ResponseEntity<?> editContact(@PathVariable("contactId") Integer id ) {
		
		try {
			//use service
			ContactForm form=contactService.editContact(id);
			return new ResponseEntity<>(form, HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new  ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/delete/{contactId}")
	public ResponseEntity<?> deleteContact(@PathVariable("contactId") Integer id){
		try {
			//use service
			List<ContactForm> list=contactService.removeContact(id);
			return new ResponseEntity<>(list, HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
