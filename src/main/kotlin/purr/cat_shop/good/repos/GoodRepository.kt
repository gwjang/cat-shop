package purr.cat_shop.good.repos

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import purr.cat_shop.good.domain.Good


interface GoodRepository : JpaRepository<Good, UUID>
