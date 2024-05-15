package purr.catshop.member.service

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import purr.catshop.member.domain.Member
import purr.catshop.member.model.MemberDTO
import purr.catshop.member.repos.MemberRepository
import purr.catshop.util.NotFoundException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun findAll(): Flux<Member> {
        return memberRepository.findAll(Sort.by("id"))
    }

    fun findOne(id: Long): Mono<Member> = memberRepository.findById(id).switchIfEmpty(Mono.error(NotFoundException()))

    fun create(memberDTO: MemberDTO): Mono<Member> {
        val member =
            Member.create(
                name = memberDTO.name,
                email = memberDTO.email,
                password = memberDTO.password,
            )
        return memberRepository.save(member)
    }

    fun update(
        id: Long,
        memberDTO: MemberDTO,
    ): Mono<Member> {
        return memberRepository.findById(id)
            .switchIfEmpty(Mono.error(NotFoundException("Member not found")))
            .flatMap { member ->
                member.name = memberDTO.name
                member.email = memberDTO.email
                member.password = memberDTO.password
                memberRepository.save(member)
            }
    }

    fun delete(id: Long): Mono<Void> {
        return memberRepository.deleteById(id)
    }

    fun emailExists(email: String?): Mono<Boolean> = memberRepository.existsByEmailIgnoreCase(email)
}
