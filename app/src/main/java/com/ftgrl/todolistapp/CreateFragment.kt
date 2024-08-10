package com.ftgrl.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ftgrl.todolistapp.databinding.FragmentCreateBinding
import com.ftgrl.todolistapp.model.ToDo
import com.ftgrl.todolistapp.roomdb.ToDoDao
import com.ftgrl.todolistapp.roomdb.ToDoDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import androidx.room.Room

class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding
    private val mDisposable = CompositeDisposable()
    private lateinit var toDoDao: ToDoDao
    private lateinit var db: ToDoDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        val view = binding.root

        db = Room.databaseBuilder(
            requireContext(),
            ToDoDatabase::class.java, "ToDoDatabase"
        ).build()

        toDoDao = db.toDoDao()

        binding.saveButton.setOnClickListener {
            val name = binding.nameText.text.toString()
            val date = binding.dateText.text.toString()
            val hour = binding.hourText.text.toString().toDouble()

            val toDo = ToDo(name, date, hour)

            mDisposable.add(
                toDoDao.insert(toDo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        // Kaydedildiğinde yapılacak işlemler
                        println("Kaydedildi")
                    }, {
                        // Hata oluştuğunda yapılacak işlemler
                        println("kayıt basarisiz")
                    })
            )
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.clear()
    }
}
