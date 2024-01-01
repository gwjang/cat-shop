package purr.cat_shop.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import purr.cat_shop.admin.domain.Admin
import purr.cat_shop.base_aggregate.domain.BaseAggregate
import purr.cat_shop.customer.domain.Customer


@Entity
class Member : BaseAggregate() {

    @Column(
        nullable = false,
        length = 30
    )
    var name: String? = null

    @Column(
        nullable = false,
        unique = true
    )
    var email: String? = null

    @Column(length = 200)
    var password: String? = null

    @OneToOne(
        mappedBy = "member",
        fetch = FetchType.LAZY
    )
    var admin: Admin? = null

    @OneToOne(
        mappedBy = "member",
        fetch = FetchType.LAZY
    )
    var customer: Customer? = null

}
