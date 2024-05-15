package purr.catshop.order.rest

import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import purr.catshop.order.model.OrderDTO
import purr.catshop.order.service.OrderService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.Void

@RestController
@RequestMapping(
    value = ["/api/orders"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class OrderResource(
    private val orderService: OrderService,
) {
    @GetMapping
    fun getAllOrders(): ResponseEntity<Flux<OrderDTO>> = ResponseEntity.ok(orderService.findAll().map { it.toDTO() })

    @GetMapping("/{id}")
    fun getOrder(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<Mono<OrderDTO>> = ResponseEntity.ok(orderService.findOne(id).map { it.toDTO() })

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createOrder(
        @RequestBody @Valid orderDTO: OrderDTO,
    ): ResponseEntity<Mono<OrderDTO>> {
        val order = orderService.create(orderDTO)
        return ResponseEntity(order.map { it.toDTO() }, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateOrder(
        @PathVariable(name = "id") id: Long,
        @RequestBody @Valid orderDTO: OrderDTO,
    ): ResponseEntity<Mono<OrderDTO>> {
        val order = orderService.update(id, orderDTO)
        return ResponseEntity.ok(order.map { it.toDTO() })
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteOrder(
        @PathVariable(name = "id") id: Long,
    ): Mono<ResponseEntity<Void>> {
        return orderService.delete(id)
            .then(Mono.fromCallable { ResponseEntity.noContent().build() })
    }
}
