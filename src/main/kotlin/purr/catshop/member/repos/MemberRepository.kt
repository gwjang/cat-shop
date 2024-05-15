package purr.catshop.member.repos

import org.springframework.data.r2dbc.repository.R2dbcRepository
import purr.catshop.member.domain.Member
import reactor.core.publisher.Mono

interface MemberRepository : R2dbcRepository<Member, Long> {
    fun existsByEmailIgnoreCase(email: String?): Mono<Boolean>
}
