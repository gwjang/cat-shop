package purr.catshop.category.service

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import purr.catshop.category.domain.Category
import purr.catshop.category.model.CategoryDTO
import purr.catshop.category.repos.CategoryRepository
import purr.catshop.util.NotFoundException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
) {
    fun findAll(): Flux<Category> {
        return categoryRepository.findAll(Sort.by("id"))
    }

    fun `get`(id: UUID): Mono<Category> = categoryRepository.findById(id).switchIfEmpty(Mono.error(NotFoundException("Category not found")))

    fun create(categoryDTO: CategoryDTO): Mono<Category> {
        val category = Category.create(categoryDTO.name)
        return categoryRepository.save(category)
    }

    fun update(
        id: UUID,
        categoryDTO: CategoryDTO,
    ): Mono<Category> {
        return categoryRepository.findById(id)
            .switchIfEmpty(Mono.error(NotFoundException("Category not found")))
            .flatMap { category ->
                category.name = categoryDTO.name
                categoryRepository.save(category)
            }
    }

    fun delete(id: UUID): Mono<Void> {
        return categoryRepository.deleteById(id)
    }
}
