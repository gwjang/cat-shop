package purr.catshop.member.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import purr.catshop.baseaggregate.domain.BaseDTO

data class MemberDTO(
    var id: Long,
    @NotNull
    @Size(max = 30)
    var name: String,
    @NotNull
    @Size(max = 255)
    var email: String,
    @Size(max = 200)
    var password: String,
) : BaseDTO()
