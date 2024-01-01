package purr.cat_shop.category.repos

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import purr.cat_shop.category.domain.Category


interface CategoryRepository : JpaRepository<Category, UUID>
