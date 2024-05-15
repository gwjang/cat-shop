package purr.catshop.product.repos

import org.springframework.data.r2dbc.repository.R2dbcRepository
import purr.catshop.product.domain.Product

interface ProductRepository : R2dbcRepository<Product, Long>
