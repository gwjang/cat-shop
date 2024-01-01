package purr.cat_shop.category.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import purr.cat_shop.base_aggregate.domain.BaseAggregate
import purr.cat_shop.good.domain.Good


@Entity
class Category : BaseAggregate() {

    @Column(
        nullable = false,
        length = 500
    )
    var name: String? = null

    @OneToMany(mappedBy = "category")
    var goods: MutableSet<Good>? = null

}
