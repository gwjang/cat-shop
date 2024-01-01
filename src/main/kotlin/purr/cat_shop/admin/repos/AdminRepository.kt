package purr.cat_shop.admin.repos

import org.springframework.data.jpa.repository.JpaRepository
import purr.cat_shop.admin.domain.Admin


interface AdminRepository : JpaRepository<Admin, Long>
