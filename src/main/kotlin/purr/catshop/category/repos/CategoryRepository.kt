package purr.catshop.category.repos

import org.springframework.data.r2dbc.repository.R2dbcRepository
import purr.catshop.category.domain.Category

interface CategoryRepository : R2dbcRepository<Category, Long>
