package github.noargs.livedatabuilder.model

import kotlinx.coroutines.delay

class UserRepo {

  suspend fun getUsers(): List<User> {
    delay(8000)
    val users: List<User> = listOf(
      User(1, "Ibn"),
      User(2, "Jam"),
      User(3, "Faz"),
      User(4, "Tam")

    )
    return users
  }
}