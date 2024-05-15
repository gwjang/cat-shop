package purr.catshop.order.domain

import jakarta.persistence.Column
import jakarta.persistence.Table
import purr.catshop.baseaggregate.domain.BaseAggregate
import purr.catshop.customer.domain.Customer
import purr.catshop.delivery.domain.Delivery
import purr.catshop.order.model.OrderDTO
import purr.catshop.product.domain.Product

@Table(name = "order")
class Order(
    @Column(
        nullable = false,
        length = 100,
    )
    var status: String,
    @Transient
    var customer: Customer,
    @Transient
    var products: MutableSet<Product>? = null,
    @Transient
    var delivery: Delivery? = null,
) : BaseAggregate() {
    companion object {
        fun create(status: String): Order = Order(status = status, customer = Customer())
    }

    override fun toDTO(): OrderDTO {
        return OrderDTO(
            id = id,
            status = status,
        )
    }
}
