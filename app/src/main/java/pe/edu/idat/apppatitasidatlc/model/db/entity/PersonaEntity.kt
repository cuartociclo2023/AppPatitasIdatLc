package pe.edu.idat.apppatitasidatlc.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persona")
 data class PersonaEntity(
  @PrimaryKey
  val id: Int,
  var nombres:String,
  var apellidos:String,
  var email:String,
  var celular:String,
  var esvoluntario:String,
  var password:String,
  var usuario:String
 )
