package purr.catshop.member.repos

import org.springframework.data.r2dbc.repository.R2dbcRepository
import purr.catshop.member.domain.Member
import reactor.core.publisher.Mono
import java.util.UUID

interface MemberRepository : R2dbcRepository<Member, UUID> {
    fun existsByEmailIgnoreCase(email: String?): Mono<Boolean>
}
