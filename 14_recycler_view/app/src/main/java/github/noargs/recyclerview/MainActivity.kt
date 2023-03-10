package github.noargs.recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 1. grab recyclerView by ID
// 2. set recyclerView layout manager
// 3. next we will set the Adapter instance for recyclerView (Adapter class should be `subclass` of  RecyclerView.Adapter class
// 4. for the type of RecyclerView.Adapter`<>` we should add a `ViewHolder` class
//    we will create separate class MyViewHolder class and make subclass of `RecyclerView.ViewHolder()`
// 5. Adapter class has 3 functions which needs to be implemented `onCreateViewHolder`, `onBindViewHolder`, and `getItemCount`

class MainActivity : AppCompatActivity() {

  // in most scenerios we send the List of objects (i.e. Data class list) instead of just the List
//  val fruitsList = listOf<String>("Apple", "Avocado", "Raspberries", "Lemon", "Pears", "Orange", "Guava")

  val fruitsList = listOf<Fruit>(
    Fruit("Apple", "Ibn"),
    Fruit("Avocado", "Jammy"),
    Fruit("Raspberries", "Jay"),
    Fruit("Lemon", "Zack"),
    Fruit("Pears", "Aly"),
    Fruit("Orange", "Frank")

  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    recyclerView.setBackgroundColor(Color.GRAY)

    // there are 3 layout manager to use with RecyclerView
    // Linearlayout manager, GridLayout manager, StaggeredGridLayout manager
    // we can create our own custom layout manager as well
    recyclerView.layoutManager = LinearLayoutManager(this)

    recyclerView.adapter = MyRecyclerViewAdapter(fruitsList) { selectedItem: Fruit ->
      listItemClicked(selectedItem)
    }
  }

  private fun listItemClicked(fruit: Fruit) {
    Toast.makeText(
      this@MainActivity,
      "Selected Fruit is: ${fruit.name}",
      Toast.LENGTH_LONG
    ).show()
  }
}