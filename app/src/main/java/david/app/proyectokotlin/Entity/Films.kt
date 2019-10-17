package david.app.proyectokotlin.Entity

import java.io.Serializable

data class Films(
    var orden: Int?,
    var titulo: String?,
    var contenido: String?,
    var photo: String
) : Serializable