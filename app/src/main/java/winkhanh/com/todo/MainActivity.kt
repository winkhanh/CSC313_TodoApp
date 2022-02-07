package winkhanh.com.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private val listOfTasks = mutableListOf<String>()
    lateinit var adapter : ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val itemList = findViewById<RecyclerView>(R.id.list) as RecyclerView
        val inputField = findViewById<EditText>(R.id.inputField)
        loadItems()
        val onLongClickListener = object : ItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                listOfTasks.removeAt(position)
                adapter.notifyDataSetChanged()
                saveItems()
            }

        }

        adapter = ItemAdapter(listOfTasks,onLongClickListener)
        itemList.adapter = adapter
        itemList.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener {
            val input = inputField.text.toString()

            listOfTasks.add(input)
            adapter.notifyItemInserted(listOfTasks.size-1)

            inputField.text.clear()
            saveItems()
        }
    }


    fun getDataFile() : File {
        return File(filesDir, "data.txt")
    }
    fun loadItems(){
        listOfTasks.clear()
        try{
            listOfTasks.addAll(FileUtils.readLines(getDataFile(), Charset.defaultCharset()))
        }catch (ioException : IOException){
            ioException.printStackTrace()
        }
    }
    fun saveItems(){
        try{
            FileUtils.writeLines(getDataFile(), listOfTasks)
        }catch (ioException : IOException){
            ioException.printStackTrace()
        }

    }
}