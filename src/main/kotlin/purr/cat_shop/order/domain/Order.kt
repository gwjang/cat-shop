package purr.cat_shop.order.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import purr.cat_shop.base_aggregate.domain.BaseAggregate
import purr.cat_shop.customer.domain.Customer
import purr.cat_shop.delivery.domain.Delivery
import purr.cat_shop.good.domain.Good


@Entity
@Table(name = "\"Order\"")
class Order : BaseAggregate() {

    @Column(
        nullable = false,
        length = 100
    )
    var status: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null

    @OneToMany(mappedBy = "order")
    var goods: MutableSet<Good>? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "delivery_id",
        unique = true
    )
    var delivery: Delivery? = null

}
