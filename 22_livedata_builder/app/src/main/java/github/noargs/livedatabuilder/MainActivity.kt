package github.noargs.livedatabuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

  private lateinit var mainActivityViewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

    // liveData Builder will automatically emit the data when available
    // mainActivityViewModel.getUsers()
    mainActivityViewModel.users.observe(this, Observer {foundUsers ->
      foundUsers.forEach { Log.i("MyTag", "name is ${it.name}")}
    })

  }
}