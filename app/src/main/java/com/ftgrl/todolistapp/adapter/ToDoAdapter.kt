import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ftgrl.todolistapp.databinding.RecyclerRowBinding
import com.ftgrl.todolistapp.model.ToDo


class ToDoAdapter(private var toDoList: List<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ToDoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
        val recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
        holder.bind(toDoList[position])
    }

    override fun getItemCount(): Int = toDoList.size

    class ToDoHolder(private val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(toDo: ToDo) {
            binding.nameEditText.setText(toDo.name)
            binding.dateEditText.setText(toDo.date)
            binding.hourEditText.setText(toDo.hour.toString())
        }
    }

    fun updateToDoList(newToDoList: List<ToDo>) {
        toDoList = newToDoList
        notifyDataSetChanged()
    }
}

