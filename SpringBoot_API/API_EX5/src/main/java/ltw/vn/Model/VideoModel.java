package ltw.vn.Model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoModel {
	private Long videoId; // ✅ sửa thành int
	private Boolean active;
	private String description;
	private String poster;
	private String title;
	private Long views;
	private Long categoryId; // ✅ đồng bộ với Category PK
	private MultipartFile imageFile;
	private Boolean isEdit = false;
}
