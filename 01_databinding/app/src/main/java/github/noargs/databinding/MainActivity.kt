package github.noargs.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
import androidx.databinding.DataBindingUtil
import github.noargs.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//    val button = findViewById<Button>(R.id.submit_button)
    binding.submitButton.setOnClickListener {
      displayGreeting()
    }
  }

  private fun displayGreeting() {
//    val messageView = findViewById<TextView>(R.id.greeting_text_view)
//    val nameText = findViewById<EditText>(R.id.name_edit_text)

    binding.greetingTextView.text = "Hello! "+ binding.nameEditText.text

    // use the kotlin `apply` scoping function to reduce the repetition of
    // name of the databinding function
//    binding.apply {
//      greetingTextView.text = "Hello! " + nameEditText.text
//    }
  }
}