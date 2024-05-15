package purr.catshop.category.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import purr.catshop.baseaggregate.domain.BaseDTO

data class CategoryDTO(
    var id: Long,
    @NotNull
    @Size(max = 500)
    var name: String,
) : BaseDTO()
