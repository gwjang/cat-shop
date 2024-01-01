package purr.cat_shop.category.service

import java.util.UUID
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import purr.cat_shop.category.domain.Category
import purr.cat_shop.category.model.CategoryDTO
import purr.cat_shop.category.repos.CategoryRepository
import purr.cat_shop.util.NotFoundException


@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun findAll(): List<CategoryDTO> {
        val categories = categoryRepository.findAll(Sort.by("id"))
        return categories.stream()
                .map { category -> mapToDTO(category, CategoryDTO()) }
                .toList()
    }

    fun `get`(id: UUID): CategoryDTO = categoryRepository.findById(id)
            .map { category -> mapToDTO(category, CategoryDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(categoryDTO: CategoryDTO): UUID {
        val category = Category()
        mapToEntity(categoryDTO, category)
        return categoryRepository.save(category).id!!
    }

    fun update(id: UUID, categoryDTO: CategoryDTO) {
        val category = categoryRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(categoryDTO, category)
        categoryRepository.save(category)
    }

    fun delete(id: UUID) {
        categoryRepository.deleteById(id)
    }

    private fun mapToDTO(category: Category, categoryDTO: CategoryDTO): CategoryDTO {
        categoryDTO.id = category.id
        categoryDTO.name = category.name
        return categoryDTO
    }

    private fun mapToEntity(categoryDTO: CategoryDTO, category: Category): Category {
        category.name = categoryDTO.name
        return category
    }

}
