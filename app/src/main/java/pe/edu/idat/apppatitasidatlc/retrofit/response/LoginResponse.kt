package pe.edu.idat.apppatitasidatlc.retrofit.response

data class LoginResponse (
    var rpta: Boolean,
    var idpersona:String,
    var nombres:String,
    var apellidos:String,
    var email:String,
    var celular:String,
    var esvoluntario:String,
    var mensaje:String,
    var password:String,
    var usuario:String
)