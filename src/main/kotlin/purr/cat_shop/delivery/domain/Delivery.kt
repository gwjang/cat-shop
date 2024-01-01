package purr.cat_shop.delivery.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import purr.cat_shop.base_aggregate.domain.BaseAggregate
import purr.cat_shop.order.domain.Order


@Entity
class Delivery : BaseAggregate() {

    @Column(length = 500)
    var address: String? = null

    @Column(length = 100)
    var status: String? = null

    @OneToOne(
        mappedBy = "delivery",
        fetch = FetchType.LAZY
    )
    var order: Order? = null

}
