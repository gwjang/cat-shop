package purr.cat_shop.member.repos

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import purr.cat_shop.member.domain.Member


interface MemberRepository : JpaRepository<Member, UUID> {

    fun existsByEmailIgnoreCase(email: String?): Boolean

}
