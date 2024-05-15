package purr.catshop.customer.repos

import org.springframework.data.r2dbc.repository.R2dbcRepository
import purr.catshop.customer.domain.Customer

interface CustomerRepository : R2dbcRepository<Customer, Long>
