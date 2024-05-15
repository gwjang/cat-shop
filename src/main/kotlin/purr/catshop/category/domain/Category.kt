package purr.catshop.category.domain

import jakarta.persistence.Column
import jakarta.persistence.Table
import purr.catshop.baseaggregate.domain.BaseAggregate
import purr.catshop.category.model.CategoryDTO

@Table(name = "category")
class Category(
    @Column(
        nullable = false,
        length = 500,
    )
    var name: String,
) : BaseAggregate() {
    companion object {
        fun create(name: String): Category = Category(name = name)
    }

    override fun toDTO(): CategoryDTO =
        CategoryDTO(
            id = id,
            name = name,
        )
}
