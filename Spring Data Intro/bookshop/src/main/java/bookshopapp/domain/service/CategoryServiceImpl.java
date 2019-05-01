package bookshopapp.domain.service;

import bookshopapp.domain.entities.Author;
import bookshopapp.domain.entities.Category;
import bookshopapp.domain.repository.CategoryRepository;
import bookshopapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORY_FILE_PATH = "C:\\Users\\elena n\\Desktop\\isstr\\bookshop\\src\\main\\resources\\files\\categories.txt";
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    //for simplicity, either add all or none
    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        String[] categoryFileContent = this.fileUtil.getFileContent(CATEGORY_FILE_PATH);

        for (String line : categoryFileContent) {
            Category category= new Category();
            category.setName(line);

            this.categoryRepository.saveAndFlush(category);
        }
    }
}
