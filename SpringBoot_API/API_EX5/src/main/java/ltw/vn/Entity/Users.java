package ltw.vn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users") 
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "username", columnDefinition = "nvarchar(255)")
	private String userName;

	@Column(name = "email", columnDefinition = "nvarchar(255)")
	private String email;

	@Column(name = "password", columnDefinition = "nvarchar(255)")
	private String password;

	@Column(name = "fullname", columnDefinition = "nvarchar(255)")
	private String fullName;

	@Column(name = "phone", columnDefinition = "nvarchar(255)")
	private String phone;

	@Column(name = "images")
	private String images;

	@Column(name = "active")
	private Boolean active;
}
