package purr.cat_shop.category.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID


class CategoryDTO {

    var id: UUID? = null

    @NotNull
    @Size(max = 500)
    var name: String? = null

}
