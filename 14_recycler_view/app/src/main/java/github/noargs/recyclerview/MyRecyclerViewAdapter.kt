package github.noargs.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(
  private val fruitsList: List<Fruit>,
  private val clickListener: (Fruit) -> Unit
  ) : RecyclerView.Adapter<MyViewHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
    return MyViewHolder(listItem)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    // holder.textView.text = fruitsList[position].name
    holder.bind(fruitsList[position], clickListener)
  }

  override fun getItemCount(): Int {
    return fruitsList.size
  }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {
//  val textView = view.findViewById<TextView>(R.id.name_text_view)
  fun bind(fruit: Fruit, clickListener: (Fruit) -> Unit) {
    val textView = view.findViewById<TextView>(R.id.name_text_view)
    textView.text = fruit.name

    view.setOnClickListener {
  //    Toast.makeText(
  //      view.context,
  //      "Selected Fruit is: ${fruit.name}",
  //      Toast.LENGTH_LONG
  //    ).show()
      clickListener(fruit)
    }
  }
}