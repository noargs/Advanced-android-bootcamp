package github.noargs.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter: RecyclerView.Adapter<MyViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    TODO("Not yet implemented")
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    TODO("Not yet implemented")
  }

  override fun getItemCount(): Int {
    return 5
  }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}