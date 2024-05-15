package purr.catshop.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.time.OffsetDateTime
import java.util.Optional

@Configuration
@EntityScan("purr.catshop")
@EnableTransactionManagement
class DomainConfig {
    @Bean(name = ["auditingDateTimeProvider"])
    fun dateTimeProvider(): DateTimeProvider = DateTimeProvider { Optional.of(OffsetDateTime.now()) }
}
