package david.app.proyectokotlin.Entity

import java.io.Serializable

data class Superhero(
    var orden : Int? = null,
    var superhero: String? = null,
    var publisher: String? = null,
    var realName: String? = null,
    var photo: String? = null
) : Serializable