package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CONTACT_DTLS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
	@Id
	@SequenceGenerator(name = "gen1",sequenceName = "CONTACTID_GEN01", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@Column(name="CONTACT_ID")
	private Integer contactId;
	@Column(name="CONTACT_NAME")
	private String contactName;
	@Column(name="CONTACT_EMAIL")
	private String contactEmail;
	@Column(name="CONTACT_NO")
	private Long contactNumber;
	@Column(name="ACTIVE_SW")
	private String activeSW;
	@Column(name="CREATED_DATE")
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name="UPDATED_DATE")
	@UpdateTimestamp
	private LocalDate updatedDate;

}
