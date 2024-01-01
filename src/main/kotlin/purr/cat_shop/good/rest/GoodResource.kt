package purr.cat_shop.good.rest

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
import purr.cat_shop.good.model.GoodDTO
import purr.cat_shop.good.service.GoodService


@RestController
@RequestMapping(
    value = ["/api/goods"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class GoodResource(
    private val goodService: GoodService
) {

    @GetMapping
    fun getAllGoods(): ResponseEntity<List<GoodDTO>> = ResponseEntity.ok(goodService.findAll())

    @GetMapping("/{id}")
    fun getGood(@PathVariable(name = "id") id: UUID): ResponseEntity<GoodDTO> =
            ResponseEntity.ok(goodService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createGood(@RequestBody @Valid goodDTO: GoodDTO): ResponseEntity<UUID> {
        val createdId = goodService.create(goodDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateGood(@PathVariable(name = "id") id: UUID, @RequestBody @Valid goodDTO: GoodDTO):
            ResponseEntity<UUID> {
        goodService.update(id, goodDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteGood(@PathVariable(name = "id") id: UUID): ResponseEntity<Void> {
        goodService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
