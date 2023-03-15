package github.noargs.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity class is used to create database table and to work with it
@Entity(tableName="subscriber_data_table")
data class Subscriber (

  @PrimaryKey(autoGenerate=true)
  @ColumnInfo(name="subscriber_id")
  val id: Int,

  @ColumnInfo(name="subscriber_name")
  var name: String,

  @ColumnInfo(name="subscriber_email")
  val email: String
)