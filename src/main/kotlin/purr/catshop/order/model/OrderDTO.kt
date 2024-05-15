package purr.catshop.order.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import purr.catshop.baseaggregate.domain.BaseDTO
import java.util.UUID

data class OrderDTO(
    var id: Long? = null,
    @NotNull
    @Size(max = 100)
    var status: String,
    var customer: Long? = null,
    var delivery: UUID? = null,
) : BaseDTO()
