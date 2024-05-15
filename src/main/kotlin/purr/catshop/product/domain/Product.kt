package purr.catshop.product.domain

import jakarta.persistence.Table
import purr.catshop.baseaggregate.domain.BaseAggregate
import purr.catshop.product.model.ProductDTO

@Table(name = "product")
class Product() : BaseAggregate() {
    companion object {
        fun create(): Product = Product()
    }

    override fun toDTO(): ProductDTO =
        ProductDTO(
            id = id,
        )
}
