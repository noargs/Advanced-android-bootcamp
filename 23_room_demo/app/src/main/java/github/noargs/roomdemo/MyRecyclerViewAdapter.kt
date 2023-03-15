package github.noargs.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import github.noargs.roomdemo.databinding.ListItemBinding
import github.noargs.roomdemo.db.Subscriber

class MyRecyclerViewAdapter(private val subscriberList: List<Subscriber>, private val clickedListener:(Subscriber) -> Unit):
  RecyclerView.Adapter<MyViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
    return MyViewHolder(binding)
  }

  override fun getItemCount(): Int {
    return subscriberList.size
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.bind(subscriberList[position], clickedListener)
  }
}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

  fun bind(subscriber: Subscriber, clickedListener: (Subscriber) -> Unit) {
    binding.nameTextView.text = subscriber.name
    binding.emailTextView.text = subscriber.email

    binding.listItemLayout.setOnClickListener {
      clickedListener(subscriber)
    }
  }

}