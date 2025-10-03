package ltw.vn.Controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ltw.vn.Entity.Category;
import ltw.vn.Model.CategoryModel;
import ltw.vn.Service.CategoryService;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	private static final String CATEGORY_ID = "categoryId";
	@Autowired
	CategoryService categoryService;

	@GetMapping("")
	public String listcategories(ModelMap model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("categories", list);
		return "admin/categories/list";
	}

	@GetMapping("add")
	public String Add(ModelMap model) {
		CategoryModel cate = new CategoryModel();
		cate.setIsEdit(false);
		model.addAttribute("category", cate);
		return "admin/categories/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryModel cate,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/categories/addOrEdit");
		}
		Category entity = new Category();
		// Explicit field mapping
		entity.setCategoryId(cate.getCategoryId());
		entity.setCategorycode(cate.getCategorycode());
		entity.setCategoryname(cate.getCategoryname());
		entity.setStatus(cate.getStatus());
		// Handle image upload
		if (cate.getImageFile() != null && !cate.getImageFile().isEmpty()) {
			String filename = cate.getImageFile().getOriginalFilename();
			entity.setImages(filename);
			// TODO: Save file to storage (implement as needed)
		} else {
			entity.setImages(cate.getImages());
		}
		categoryService.save(entity);
		String message = cate.getIsEdit() ? "Category đã được cập nhật thành công" : "Category đã được thêm thành công";
		model.addAttribute("message", message);
		return new ModelAndView("redirect:/admin/categories/list", model);
	}

	@GetMapping("edit/{" + CATEGORY_ID + "}")
	public ModelAndView edit(ModelMap model, @PathVariable(CATEGORY_ID) Long categoryId) {
		Optional<Category> opt = categoryService.findById(categoryId);
		CategoryModel cate = new CategoryModel();
		if (opt.isPresent()) {
			Category entity = opt.get();
			cate.setCategoryId(entity.getCategoryId());
			cate.setCategorycode(entity.getCategorycode());
			cate.setCategoryname(entity.getCategoryname());
			cate.setImages(entity.getImages());
			cate.setStatus(entity.getStatus());
			cate.setIsEdit(true);
			model.addAttribute("category", cate);
			return new ModelAndView("admin/categories/addOrEdit", model);
		}
		model.addAttribute("message", "Category không tồn tại");
		return new ModelAndView("redirect:/admin/categories", model);
	}

	@GetMapping("delete/{" + CATEGORY_ID + "}")
	public ModelAndView delete(ModelMap model, @PathVariable(CATEGORY_ID) Long categoryId) {
		categoryService.deleteById(categoryId);
		model.addAttribute("message", "Category đã được xóa thành công");
		return new ModelAndView("redirect:/admin/categories", model);
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<Category> list = null;
		if (StringUtils.hasText(name)) {
			list = categoryService.findByCategoryNameContaining(name);
		} else {
			list = categoryService.findAll();
		}
		model.addAttribute("categories", list);
		return "admin/categories/search";
	}

	@RequestMapping("searchpagenated")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int count = (int) categoryService.count();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by(CATEGORY_ID));
		Page<Category> resultPage = null;
		if (StringUtils.hasText(name)) {
			resultPage = categoryService.findByCategoryNameContaining(name, pageable);
			model.addAttribute("name", name);

		} else {
			resultPage = categoryService.findAll(pageable);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > count) {
				if (end == totalPages)
					start = end - count;
			} else if (start == 1)
				end = start + count;

			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("categoryPage", resultPage);

		return "admin/categories/searchpagenated";

	}

}