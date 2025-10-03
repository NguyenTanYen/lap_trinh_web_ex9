package ltw.vn.Entity;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private Long categoryId;

	@Column(name = "CategoryCode", columnDefinition = "nvarchar(255)")
	private String categorycode;

	@Column(name = "CategoryName", columnDefinition = "nvarchar(255)")
	private String categoryname;

	@Column(name = "Images")
	private String images;

	@Column(name = "Status")
	private Boolean status;
	@Column(name = "Icon")
	private String icon;


	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Video> videos;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Product> productes;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorycode() {
		return categorycode;
	}

	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	public Set<Product> getProductes() {
		return productes;
	}

	public void setProductes(Set<Product> productes) {
		this.productes = productes;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

}