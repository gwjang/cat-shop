package purr.catshop.delivery.repos

import org.springframework.data.r2dbc.repository.R2dbcRepository
import purr.catshop.delivery.domain.Delivery
import java.util.UUID

interface DeliveryRepository : R2dbcRepository<Delivery, UUID>
