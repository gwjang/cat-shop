package purr.catshop.order.service

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import purr.catshop.order.domain.Order
import purr.catshop.order.model.OrderDTO
import purr.catshop.order.repos.OrderRepository
import purr.catshop.util.NotFoundException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    fun findAll(): Flux<Order> {
        return orderRepository.findAll(Sort.by("id"))
    }

    fun findOne(id: Long): Mono<Order> = orderRepository.findById(id).switchIfEmpty(Mono.error(NotFoundException()))

    fun create(orderDTO: OrderDTO): Mono<Order> {
        val order =
            Order.create(
                status = "order",
            )
        return orderRepository.save(order)
    }

    fun update(
        id: Long,
        orderDTO: OrderDTO,
    ): Mono<Order> {
        return orderRepository.findById(id)
            .switchIfEmpty(Mono.error(NotFoundException("Order not found")))
            .flatMap { order ->
                order.status = orderDTO.status
                orderRepository.save(order)
            }
    }

    fun delete(id: Long): Mono<Void> {
        return orderRepository.deleteById(id)
    }
}
