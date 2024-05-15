package purr.catshop.member.rest

import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import purr.catshop.member.model.MemberDTO
import purr.catshop.member.service.MemberService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.Void
import java.util.UUID

@RestController
@RequestMapping(
    value = ["/api/members"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class MemberResource(
    private val memberService: MemberService,
) {
    @GetMapping
    fun getAllMembers(): ResponseEntity<Flux<MemberDTO>> = ResponseEntity.ok(memberService.findAll().map { it.toDTO() })

    @GetMapping("/{id}")
    fun getMember(
        @PathVariable(name = "id") id: UUID,
    ): ResponseEntity<Mono<MemberDTO>> = ResponseEntity.ok(memberService.get(id).map { it.toDTO() })

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createMember(
        @RequestBody @Valid memberDTO: MemberDTO,
    ): ResponseEntity<Mono<MemberDTO>> {
        val createdId = memberService.create(memberDTO)
        return ResponseEntity(createdId.map { it.toDTO() }, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateMember(
        @PathVariable(name = "id") id: UUID,
        @RequestBody @Valid memberDTO: MemberDTO,
    ): ResponseEntity<Mono<MemberDTO>> {
        val member = memberService.update(id, memberDTO)
        return ResponseEntity.ok(member.map { it.toDTO() })
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteMember(
        @PathVariable(name = "id") id: UUID,
    ): Mono<ResponseEntity<Void>> {
        return memberService.delete(id)
            .then(Mono.fromCallable { ResponseEntity.noContent().build() })
    }
}
