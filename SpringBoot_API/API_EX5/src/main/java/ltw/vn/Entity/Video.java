package ltw.vn.Entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Videos")
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "VideoId")
	private Long videoId;

	@Column(name = "Active")
	private Boolean active;

	@Column(name = "Description", columnDefinition = "nvarchar(MAX)")
	private String description;

	@Column(name = "Poster", columnDefinition = "nvarchar(255)")
	private String poster;

	@Column(name = "Title", nullable = false, columnDefinition = "nvarchar(255)")
	private String title;

	@Column(name = "Views")
	private Long views;

	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;
	
		
}
