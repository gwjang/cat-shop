package purr.catshop.admin.repos

import org.springframework.data.r2dbc.repository.R2dbcRepository
import purr.catshop.admin.domain.Admin

interface AdminRepository : R2dbcRepository<Admin, Long>
