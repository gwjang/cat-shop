package purr.cat_shop.order.rest

import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import java.util.UUID
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
import purr.cat_shop.order.model.OrderDTO
import purr.cat_shop.order.service.OrderService


@RestController
@RequestMapping(
    value = ["/api/orders"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class OrderResource(
    private val orderService: OrderService
) {

    @GetMapping
    fun getAllOrders(): ResponseEntity<List<OrderDTO>> = ResponseEntity.ok(orderService.findAll())

    @GetMapping("/{id}")
    fun getOrder(@PathVariable(name = "id") id: UUID): ResponseEntity<OrderDTO> =
            ResponseEntity.ok(orderService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createOrder(@RequestBody @Valid orderDTO: OrderDTO): ResponseEntity<UUID> {
        val createdId = orderService.create(orderDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateOrder(@PathVariable(name = "id") id: UUID, @RequestBody @Valid orderDTO: OrderDTO):
            ResponseEntity<UUID> {
        orderService.update(id, orderDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteOrder(@PathVariable(name = "id") id: UUID): ResponseEntity<Void> {
        orderService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
