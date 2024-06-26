package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Contact;

public interface ContactDetailsRepo extends JpaRepository<Contact, Serializable> {

}
