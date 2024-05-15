package purr.catshop.delivery.domain

import jakarta.persistence.Column
import jakarta.persistence.Table
import purr.catshop.baseaggregate.domain.BaseAggregate
import purr.catshop.baseaggregate.domain.BaseDTO

@Table(name = "delivery")
class Delivery(
    @Column(length = 500)
    var address: String? = null,
    @Column(length = 100)
    var status: String? = null,
) : BaseAggregate() {
    companion object {
        fun create(
            address: String,
            status: String,
        ): Delivery = Delivery(address = address, status = status)
    }

    override fun toDTO(): BaseDTO {
        TODO("Not yet implemented")
    }
}
