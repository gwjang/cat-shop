package purr.cat_shop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class CatShopApplication

fun main(args: Array<String>) {
    runApplication<CatShopApplication>(*args)
}
