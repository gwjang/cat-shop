package purr.catshop.product.service

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import purr.catshop.product.domain.Product
import purr.catshop.product.model.ProductDTO
import purr.catshop.product.repos.ProductRepository
import purr.catshop.util.NotFoundException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun findAll(): Flux<Product> {
        return productRepository.findAll(Sort.by("id"))
    }

    fun findOne(id: Long): Mono<Product> = productRepository.findById(id).switchIfEmpty(Mono.error(NotFoundException("Product not found")))

    fun create(productDTO: ProductDTO): Mono<Product> {
        val product = Product.create()
        return productRepository.save(product)
    }

    fun update(
        id: Long,
        productDTO: ProductDTO,
    ): Mono<Product> {
        return productRepository.findById(id)
            .switchIfEmpty(Mono.error(NotFoundException("Product not found")))
            .flatMap { product ->
                productRepository.save(product)
            }
    }

    fun delete(id: Long): Mono<Void> {
        return productRepository.deleteById(id)
    }
}
