package pe.edu.idat.apppatitasidatlc.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.idat.apppatitasidatlc.model.db.dao.PersonaDao
import pe.edu.idat.apppatitasidatlc.model.db.entity.PersonaEntity

@Database(entities = [PersonaEntity::class], version = 1)
public abstract class PatitasRoomDatabase: RoomDatabase() {

    abstract fun personaDao(): PersonaDao
    companion object{
        @Volatile
        private var INSTANCE: PatitasRoomDatabase? = null

        fun getDatabase(context: Context): PatitasRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PatitasRoomDatabase::class.java,
                    "patitasdb"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }

    }

}