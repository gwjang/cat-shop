package purr.catshop.order.repos

import org.springframework.data.r2dbc.repository.R2dbcRepository
import purr.catshop.order.domain.Order

interface OrderRepository : R2dbcRepository<Order, Long>
