package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.myapplication.activity.country.CountryListActivity
import com.example.myapplication.activity.notepad.NoteListActivity
import com.example.myapplication.activity.other.ConfirmDeleteDialogFragment
import com.example.myapplication.activity.other.Details
import com.example.myapplication.activity.other.FileListDialogFragment
import com.example.myapplication.activity.test_fragment.TestFragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch_calcul_button.setOnClickListener {
            val intent = Intent(this, Details::class.java)
            this.startActivity(intent)
        }

        show_dialog.setOnClickListener {
            createDialog()
        }

        launch_list.setOnClickListener {
            val intent = Intent(this, CountryListActivity::class.java)
            startActivity(intent)
        }

        launch_notepad_button.setOnClickListener {
            var intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
        }

        launch_test_fragment.setOnClickListener {
            startActivity(
                Intent(this, TestFragmentActivity::class.java)
            )
        }

    }

    private fun createDialog() {
        var fragment = ConfirmDeleteDialogFragment()

        fragment.listener = object : ConfirmDeleteDialogFragment.ConfirmDeleteListener {
            override fun onDialogPositiveClick() {
                Log.i("Main activity", "oui")

                var fileListFragment = FileListDialogFragment()

                fileListFragment.show(supportFragmentManager, "fileList")
            }

            override fun onDialogNegativeClick() {
                Log.i("Main activity", "non")
            }

        }

        fragment.show(supportFragmentManager, "Confirm Delete")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = MenuInflater(this)
        menuInflater.inflate(R.menu.action_bar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_lol -> {
                Toast.makeText(this, "Vous êtes en mode lol", Toast.LENGTH_SHORT).show()

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
