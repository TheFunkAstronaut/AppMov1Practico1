package com.example.trabajopractico1.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajopractico1.R
import com.example.trabajopractico1.models.Notebook
import com.example.trabajopractico1.ui.adapters.NotebookAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NotebookAdapter.OnContactClickListener {
    private val datalist = arrayListOf(
        Notebook("TEST1","Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum"),
        Notebook("TEST2","Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum"),
        Notebook("TEST3","Lorem Ipsum"),
        Notebook("TEST4","Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum,Lorem Ipsum"),
        Notebook("TEST5","Lorem Ipsum")
    )
    private lateinit var rvNotebook: RecyclerView
    private lateinit var fabAddNotebook: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fabAddNotebook = findViewById(R.id.fabAddNotebook)
        rvNotebook = findViewById(R.id.rvNotebookLIst)
        setupRecyclerView()
        setupEventListeners()
    }
    private fun setupEventListeners(){
        fabAddNotebook.setOnClickListener{
            buildAlertDialog()
        }
    }

    private fun buildAlertDialog(notebook: Notebook? = null){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Formulario de Insercion de Notebook")

        val viewInflated: View = LayoutInflater.from(this)
            .inflate(R.layout.formulario_layaout, null, false)

        val txtNewNotebookTitle: EditText = viewInflated.findViewById(R.id.txtNewNotebookTitle)
        val txtNewNotebookText: EditText = viewInflated.findViewById(R.id.txtNewNotebookText)
        txtNewNotebookTitle.setText(notebook?.title)
        txtNewNotebookText.setText(notebook?.content)
        builder.setView(viewInflated)

        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
            val title = txtNewNotebookTitle.text.toString()
            val content = txtNewNotebookText.text.toString()

            if (notebook != null) {
                notebook.title = title
                notebook.content = content
                editContactFromList(notebook)
            } else {
                addContactToList(title, content)
            }
        }
        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
    private fun showColorPickerDialog(notebook: Notebook) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccione un color para el notebook")


        val viewInflated: View = LayoutInflater.from(this)
            .inflate(R.layout.color_choosing_layaout, null, false)

        builder.setView(viewInflated)


        val btnRed: Button = viewInflated.findViewById(R.id.btnRed)
        val btnYellow: Button = viewInflated.findViewById(R.id.btnYellow)
        val btnGreen: Button = viewInflated.findViewById(R.id.btnGreen)
        val btnLightGreen: Button = viewInflated.findViewById(R.id.btnLightGreen)
        val btnBlue: Button = viewInflated.findViewById(R.id.btnBlue)
        val btnCyan: Button = viewInflated.findViewById(R.id.btnCyan)
        val btnBlack: Button = viewInflated.findViewById(R.id.btnBlack)
        val btnOrange: Button = viewInflated.findViewById(R.id.btnOrange)
        val btnPurple: Button = viewInflated.findViewById(R.id.btnPurple)
        val btnPink: Button = viewInflated.findViewById(R.id.btnPink)

        btnRed.setOnClickListener {
            changeNotebookColor(notebook, R.color.red)
        }
        btnYellow.setOnClickListener {
            changeNotebookColor(notebook, R.color.yellow)
        }
        btnGreen.setOnClickListener {
            changeNotebookColor(notebook, R.color.green)
        }
        btnLightGreen.setOnClickListener {
            changeNotebookColor(notebook,R.color.light_green)
        }
        btnBlue.setOnClickListener {
            changeNotebookColor(notebook, R.color.blue)
        }
        btnCyan .setOnClickListener {
            changeNotebookColor(notebook,R.color.cyan)
        }
        btnBlack.setOnClickListener {
            changeNotebookColor(notebook,R.color.black)
        }
        btnOrange.setOnClickListener {
            changeNotebookColor(notebook,R.color.orange)
        }
        btnPurple.setOnClickListener {
            changeNotebookColor(notebook, R.color.purple)
        }
        btnPink.setOnClickListener {
            changeNotebookColor(notebook, R.color.pink)
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun changeNotebookColor(notebook: Notebook, colorResId: Int) {
        notebook.color = colorResId

        val adapter = rvNotebook.adapter as NotebookAdapter
        adapter.itemUpdated(notebook)
    }

    private fun editContactFromList(notebook: Notebook) {
        val adapter = rvNotebook.adapter as NotebookAdapter
        adapter.itemUpdated(notebook)
    }

    private fun addContactToList(title: String, content: String) {
        val notebook = Notebook(title, content)
        val adapter = rvNotebook.adapter as NotebookAdapter
        adapter.itemAdded(notebook)
    }

    private fun setupRecyclerView(){
        rvNotebook.adapter = NotebookAdapter(datalist,this)
        rvNotebook.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }

    override fun onContactEditClickListener(notebook: Notebook) {
        buildAlertDialog(notebook)
    }

    override fun onContactDeleteClickListener(notebook: Notebook) {
        val adapter = rvNotebook.adapter as  NotebookAdapter
        adapter.itemDeleted(notebook)
    }

    override fun onContactEditColorListener(notebook: Notebook) {
        showColorPickerDialog(notebook)
    }
}