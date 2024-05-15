package purr.catshop.customer.domain

import jakarta.persistence.Table
import purr.catshop.baseaggregate.domain.BaseAggregate
import purr.catshop.baseaggregate.domain.BaseDTO
import purr.catshop.member.domain.Member

@Table(name = "customer")
class Customer(
    @Transient
    var member: Member? = null,
) : BaseAggregate() {
    override fun toDTO(): BaseDTO {
        TODO("Not yet implemented")
    }
}
