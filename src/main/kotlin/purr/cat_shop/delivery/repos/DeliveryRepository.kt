package purr.cat_shop.delivery.repos

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import purr.cat_shop.delivery.domain.Delivery


interface DeliveryRepository : JpaRepository<Delivery, UUID>
