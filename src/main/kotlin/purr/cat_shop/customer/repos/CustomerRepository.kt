package purr.cat_shop.customer.repos

import org.springframework.data.jpa.repository.JpaRepository
import purr.cat_shop.customer.domain.Customer


interface CustomerRepository : JpaRepository<Customer, Long>
