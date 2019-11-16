package com.example.myapplication.activity.notepad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.R
import com.example.myapplication.dialog_fragment.ConfirmDeleteDialogFragment
import com.example.myapplication.model.Note
import kotlinx.android.synthetic.main.activity_note_detail.title as titleView
import kotlinx.android.synthetic.main.activity_note_detail.text as textView
import kotlinx.android.synthetic.main.activity_note_detail.toolbar as custom_toolbar

class NoteDetailActivity : AppCompatActivity() {

    companion object {
        val REQUEST_EDIT_NOTE = 1
        val EXTRA_NOTE = "note"
        val EXTRA_NOTE_INDEX = "noteIndex"

        val ACTION_SAVE_NOTE = "com.example.myapplication.actions.ACTION_SAVE_NOTE"
        val ACTION_DELETE_NOTE = "com.example.myapplication.actions.ACTION_DELETE_NOTE"
    }

    lateinit var note: Note
    var noteIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        note = intent.getParcelableExtra<Note>(EXTRA_NOTE)
        noteIndex = intent.getIntExtra(EXTRA_NOTE_INDEX, -1)

        titleView.setText(note.title)
        textView.setText(note.text)

        setSupportActionBar(custom_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_note_detail, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                this.saveNote()
                return true
            }
            R.id.action_delete -> {
                this.showConfirmDeleteNoteDialog()
                return true
            }
            else -> return false
        }
    }

    private fun showConfirmDeleteNoteDialog() {
        var confirmFragment = ConfirmDeleteDialogFragment(note.title)

        confirmFragment.listener = object: ConfirmDeleteDialogFragment.ConfirmDialogListener {
            override fun onDialogNegativeClick() {
                // Nothing
            }

            override fun onDialogPositiveClick() {
                deleteNote()
            }
        }

        confirmFragment.show(supportFragmentManager, "confirmDeleteDialog")
    }

    fun saveNote() {
        note.title = titleView.text.toString()
        note.text = textView.text.toString()

        intent = Intent(ACTION_SAVE_NOTE)
        intent.putExtra(EXTRA_NOTE, note as Parcelable)
        intent.putExtra(EXTRA_NOTE_INDEX, noteIndex)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun deleteNote() {
        intent = Intent(ACTION_DELETE_NOTE)

        intent.putExtra(EXTRA_NOTE_INDEX, this.noteIndex)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
