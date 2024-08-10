package com.ftgrl.todolistapp

import ToDoAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.ftgrl.todolistapp.databinding.FragmentToDoListBinding
import com.ftgrl.todolistapp.roomdb.ToDoDao
import com.ftgrl.todolistapp.roomdb.ToDoDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ToDoListFragment : Fragment() {

    private lateinit var binding: FragmentToDoListBinding
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var toDoDao: ToDoDao
    private val mDisposable = CompositeDisposable()
    private lateinit var db: ToDoDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(inflater, container, false)
        val view = binding.root

        db = Room.databaseBuilder(
            requireContext(),
            ToDoDatabase::class.java, "ToDoDatabase"
        ).build()

        toDoDao = db.toDoDao()

        mDisposable.add(
            toDoDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ toDoList ->
                    toDoAdapter = ToDoAdapter(toDoList)
                    binding.recyclerView.adapter = toDoAdapter
                }, {
                    Log.e("ToDoListFragment", "Veriler alınırken hata oluştu", it)
                })
        )

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.clear()
    }
}
