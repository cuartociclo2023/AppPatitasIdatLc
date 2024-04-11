package pe.edu.idat.apppatitasidatlc.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.idat.apppatitasidatlc.model.db.entity.PersonaEntity

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(vararg persona:PersonaEntity)
    @Update
    fun actualizar(vararg persona: PersonaEntity)
    @Query("DELETE FROM persona")
    fun eliminarTodo()
    @Query("SELECT * FROM persona LIMIT 1")
    fun obtener():LiveData<PersonaEntity>
}