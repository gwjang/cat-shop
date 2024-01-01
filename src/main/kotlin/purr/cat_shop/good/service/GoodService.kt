package purr.cat_shop.good.service

import java.util.UUID
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import purr.cat_shop.category.repos.CategoryRepository
import purr.cat_shop.good.domain.Good
import purr.cat_shop.good.model.GoodDTO
import purr.cat_shop.good.repos.GoodRepository
import purr.cat_shop.order.repos.OrderRepository
import purr.cat_shop.util.NotFoundException


@Service
class GoodService(
    private val goodRepository: GoodRepository,
    private val categoryRepository: CategoryRepository,
    private val orderRepository: OrderRepository
) {

    fun findAll(): List<GoodDTO> {
        val goods = goodRepository.findAll(Sort.by("id"))
        return goods.stream()
                .map { good -> mapToDTO(good, GoodDTO()) }
                .toList()
    }

    fun `get`(id: UUID): GoodDTO = goodRepository.findById(id)
            .map { good -> mapToDTO(good, GoodDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(goodDTO: GoodDTO): UUID {
        val good = Good()
        mapToEntity(goodDTO, good)
        return goodRepository.save(good).id!!
    }

    fun update(id: UUID, goodDTO: GoodDTO) {
        val good = goodRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(goodDTO, good)
        goodRepository.save(good)
    }

    fun delete(id: UUID) {
        goodRepository.deleteById(id)
    }

    private fun mapToDTO(good: Good, goodDTO: GoodDTO): GoodDTO {
        goodDTO.id = good.id
        goodDTO.name = good.name
        goodDTO.price = good.price
        goodDTO.quantity = good.quantity
        goodDTO.category = good.category?.id
        goodDTO.order = good.order?.id
        return goodDTO
    }

    private fun mapToEntity(goodDTO: GoodDTO, good: Good): Good {
        good.name = goodDTO.name
        good.price = goodDTO.price
        good.quantity = goodDTO.quantity
        val category = if (goodDTO.category == null) null else
                categoryRepository.findById(goodDTO.category!!)
                .orElseThrow { NotFoundException("category not found") }
        good.category = category
        val order = if (goodDTO.order == null) null else orderRepository.findById(goodDTO.order!!)
                .orElseThrow { NotFoundException("order not found") }
        good.order = order
        return good
    }

}
