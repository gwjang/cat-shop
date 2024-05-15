package purr.catshop.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Table
import purr.catshop.baseaggregate.domain.BaseAggregate
import purr.catshop.customer.domain.Customer
import purr.catshop.member.model.MemberDTO

@Table(name = "member")
class Member(
    @Column(
        nullable = false,
        length = 30,
    )
    var name: String,
    @Column(
        nullable = false,
        unique = true,
    )
    var email: String,
    @Column(length = 200)
    var password: String,
    @Transient
    var customer: Customer? = null,
) : BaseAggregate() {
    companion object {
        fun create(
            name: String,
            email: String,
            password: String,
        ): Member = Member(name = name, email = email, password = password)
    }

    override fun toDTO(): MemberDTO =
        MemberDTO(
            id = id,
            name = name,
            email = email,
            password = password,
        )
}
