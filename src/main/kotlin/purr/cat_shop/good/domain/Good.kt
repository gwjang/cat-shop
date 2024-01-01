package purr.cat_shop.good.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import purr.cat_shop.base_aggregate.domain.BaseAggregate
import purr.cat_shop.category.domain.Category
import purr.cat_shop.order.domain.Order


@Entity
class Good : BaseAggregate() {

    @Column(length = 500)
    var name: String? = null

    @Column
    var price: Long? = null

    @Column
    var quantity: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "category_id",
        nullable = false
    )
    var category: Category? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "order_id",
        nullable = false
    )
    var order: Order? = null

}
