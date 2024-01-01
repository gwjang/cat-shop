package purr.cat_shop.order.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID


class OrderDTO {

    var id: UUID? = null

    @NotNull
    @Size(max = 100)
    var status: String? = null

    var customer: Long? = null

    var delivery: UUID? = null

}
