package david.app.proyectokotlin.Entity

import java.io.Serializable

data class Books (
    var orden : Int? = null,
    var titulo: String? = null,
    var contenido: String? = null,
    var photo: String? = null
) : Serializable