package pe.edu.idat.apppatitasidatlc.retrofit.request

data class RegistroRequest (
    var nombres:String,
    var apellidos:String,
    var email:String,
    var celular:String,
    var password:String,
    var usuario:String
)