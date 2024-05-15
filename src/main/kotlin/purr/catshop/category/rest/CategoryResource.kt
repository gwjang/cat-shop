package purr.catshop.category.rest

import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import purr.catshop.category.model.CategoryDTO
import purr.catshop.category.service.CategoryService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.Void
import java.util.UUID

@RestController
@RequestMapping(
    value = ["/api/categories"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class CategoryResource(
    private val categoryService: CategoryService,
) {
    @GetMapping
    fun getAllCategories(): ResponseEntity<Flux<CategoryDTO>> = ResponseEntity.ok(categoryService.findAll().map { it.toDTO() })

    @GetMapping("/{id}")
    fun getCategory(
        @PathVariable(name = "id") id: UUID,
    ): ResponseEntity<Mono<CategoryDTO>> = ResponseEntity.ok(categoryService.findOne(id).map { it.toDTO() })

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createCategory(
        @RequestBody @Valid categoryDTO: CategoryDTO,
    ): ResponseEntity<Mono<CategoryDTO>> {
        val category = categoryService.create(categoryDTO)
        return ResponseEntity(category.map { it.toDTO() }, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable(name = "id") id: UUID,
        @RequestBody @Valid
        categoryDTO: CategoryDTO,
    ): ResponseEntity<Mono<CategoryDTO>> {
        val category = categoryService.update(id, categoryDTO)
        return ResponseEntity.ok(category.map { it.toDTO() })
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteCategory(
        @PathVariable(name = "id") id: UUID,
    ): Mono<ResponseEntity<Void>> {
        return categoryService.delete(id)
            .then(Mono.fromCallable { ResponseEntity.noContent().build() })
    }
}
