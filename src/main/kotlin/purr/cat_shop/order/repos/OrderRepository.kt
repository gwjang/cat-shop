package purr.cat_shop.order.repos

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import purr.cat_shop.order.domain.Order


interface OrderRepository : JpaRepository<Order, UUID>
