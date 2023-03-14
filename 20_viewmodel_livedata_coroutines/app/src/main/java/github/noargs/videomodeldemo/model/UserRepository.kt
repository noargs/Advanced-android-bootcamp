package github.noargs.videomodeldemo.model

import kotlinx.coroutines.delay

// UserRepository simulate API or Database call in real world situation
class UserRepository {

  suspend fun getUsers(): List<User> {
    delay(8000)
    val users: List<User> = listOf(
      User(1, "Ibn"),
      User(2, "Jay"),
      User(3, "Jam"),
      User(4, "Faz")
    )
    return users
  }

}