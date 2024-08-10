import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ftgrl.todolistapp.CreateFragment
import com.ftgrl.todolistapp.ToDoListFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ToDoListFragment()
            1 -> CreateFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
