package purr.cat_shop.good.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID


class GoodDTO {

    var id: UUID? = null

    @Size(max = 500)
    var name: String? = null

    var price: Long? = null

    var quantity: Int? = null

    @NotNull
    var category: UUID? = null

    @NotNull
    var order: UUID? = null

}
