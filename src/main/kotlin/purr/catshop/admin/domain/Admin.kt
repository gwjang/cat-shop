package purr.catshop.admin.domain

import jakarta.persistence.Table
import purr.catshop.baseaggregate.domain.BaseAggregate
import purr.catshop.baseaggregate.domain.BaseDTO
import purr.catshop.member.domain.Member

@Table(name = "admin")
class Admin(
    @Transient
    var member: Member,
) : BaseAggregate() {
    override fun toDTO(): BaseDTO {
        TODO("Not yet implemented")
    }
}
