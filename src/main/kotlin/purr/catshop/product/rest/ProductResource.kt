package purr.catshop.product.rest

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
import purr.catshop.product.model.ProductDTO
import purr.catshop.product.service.ProductService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.Void

@RestController
@RequestMapping(
    value = ["/api/product"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class ProductResource(
    private val productService: ProductService,
) {
    @GetMapping
    fun getAll(): ResponseEntity<Flux<ProductDTO>> = ResponseEntity.ok(productService.findAll().map { it.toDTO() })

    @GetMapping("/{id}")
    fun getOne(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<Mono<ProductDTO>> = ResponseEntity.ok(productService.findOne(id).map { it.toDTO() })

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun create(
        @RequestBody @Valid productDTO: ProductDTO,
    ): ResponseEntity<Mono<ProductDTO>> {
        val product = productService.create(productDTO)
        return ResponseEntity(product.map { it.toDTO() }, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable(name = "id") id: Long,
        @RequestBody @Valid
        productDTO: ProductDTO,
    ): ResponseEntity<Mono<ProductDTO>> {
        val product = productService.update(id, productDTO)
        return ResponseEntity.ok(product.map { it.toDTO() })
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun delete(
        @PathVariable(name = "id") id: Long,
    ): Mono<ResponseEntity<Void>> {
        return productService.delete(id)
            .then(Mono.fromCallable { ResponseEntity.noContent().build() })
    }
}
