package cz.vse.golemiokotlinlib.data

data class Response(
    val responseData: Any? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)
