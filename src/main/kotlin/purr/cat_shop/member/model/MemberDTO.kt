package purr.cat_shop.member.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID


class MemberDTO {

    var id: UUID? = null

    @NotNull
    @Size(max = 30)
    var name: String? = null

    @NotNull
    @Size(max = 255)
    var email: String? = null

    @Size(max = 200)
    var password: String? = null

}
