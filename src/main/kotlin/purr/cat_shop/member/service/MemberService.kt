package purr.cat_shop.member.service

import java.util.UUID
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import purr.cat_shop.member.domain.Member
import purr.cat_shop.member.model.MemberDTO
import purr.cat_shop.member.repos.MemberRepository
import purr.cat_shop.util.NotFoundException


@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun findAll(): List<MemberDTO> {
        val members = memberRepository.findAll(Sort.by("id"))
        return members.stream()
                .map { member -> mapToDTO(member, MemberDTO()) }
                .toList()
    }

    fun `get`(id: UUID): MemberDTO = memberRepository.findById(id)
            .map { member -> mapToDTO(member, MemberDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(memberDTO: MemberDTO): UUID {
        val member = Member()
        mapToEntity(memberDTO, member)
        return memberRepository.save(member).id!!
    }

    fun update(id: UUID, memberDTO: MemberDTO) {
        val member = memberRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(memberDTO, member)
        memberRepository.save(member)
    }

    fun delete(id: UUID) {
        memberRepository.deleteById(id)
    }

    private fun mapToDTO(member: Member, memberDTO: MemberDTO): MemberDTO {
        memberDTO.id = member.id
        memberDTO.name = member.name
        memberDTO.email = member.email
        memberDTO.password = member.password
        return memberDTO
    }

    private fun mapToEntity(memberDTO: MemberDTO, member: Member): Member {
        member.name = memberDTO.name
        member.email = memberDTO.email
        member.password = memberDTO.password
        return member
    }

    fun emailExists(email: String?): Boolean = memberRepository.existsByEmailIgnoreCase(email)

}
