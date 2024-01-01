package purr.cat_shop.order.service

import java.util.UUID
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import purr.cat_shop.customer.repos.CustomerRepository
import purr.cat_shop.delivery.repos.DeliveryRepository
import purr.cat_shop.order.domain.Order
import purr.cat_shop.order.model.OrderDTO
import purr.cat_shop.order.repos.OrderRepository
import purr.cat_shop.util.NotFoundException


@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val customerRepository: CustomerRepository,
    private val deliveryRepository: DeliveryRepository
) {

    fun findAll(): List<OrderDTO> {
        val orders = orderRepository.findAll(Sort.by("id"))
        return orders.stream()
                .map { order -> mapToDTO(order, OrderDTO()) }
                .toList()
    }

    fun `get`(id: UUID): OrderDTO = orderRepository.findById(id)
            .map { order -> mapToDTO(order, OrderDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(orderDTO: OrderDTO): UUID {
        val order = Order()
        mapToEntity(orderDTO, order)
        return orderRepository.save(order).id!!
    }

    fun update(id: UUID, orderDTO: OrderDTO) {
        val order = orderRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(orderDTO, order)
        orderRepository.save(order)
    }

    fun delete(id: UUID) {
        orderRepository.deleteById(id)
    }

    private fun mapToDTO(order: Order, orderDTO: OrderDTO): OrderDTO {
        orderDTO.id = order.id
        orderDTO.status = order.status
        orderDTO.customer = order.customer?.id
        orderDTO.delivery = order.delivery?.id
        return orderDTO
    }

    private fun mapToEntity(orderDTO: OrderDTO, order: Order): Order {
        order.status = orderDTO.status
        val customer = if (orderDTO.customer == null) null else
                customerRepository.findById(orderDTO.customer!!)
                .orElseThrow { NotFoundException("customer not found") }
        order.customer = customer
        val delivery = if (orderDTO.delivery == null) null else
                deliveryRepository.findById(orderDTO.delivery!!)
                .orElseThrow { NotFoundException("delivery not found") }
        order.delivery = delivery
        return order
    }

}
