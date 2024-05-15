package purr.catshop.baseaggregate.domain

import jakarta.persistence.Column
import jakarta.persistence.Id
import org.hibernate.envers.Audited
import org.hibernate.envers.NotAudited
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Audited
abstract class BaseAggregate(
    @Id
    @Column(
        nullable = false,
        updatable = false,
    )
    @NotAudited
    var id: Long = 0,
    @CreatedDate
    @Column(
        nullable = false,
        updatable = false,
    )
    @NotAudited
    var createdDate: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column(nullable = false)
    var updatedDate: LocalDateTime = LocalDateTime.now(),
) {
    abstract fun toDTO(): BaseDTO
}
