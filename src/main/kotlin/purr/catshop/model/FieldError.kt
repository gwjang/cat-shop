package purr.catshop.model

data class FieldError(
    var `field`: String? = null,
    var errorCode: String? = null,
)
