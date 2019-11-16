package com.example.myapplication.activity.notepad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.NoteAdapter
import com.example.myapplication.model.Note
import com.example.myapplication.utils.loadNotes
import com.example.myapplication.utils.persistNote
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.activity_note_list.notes_recycler_view as recycler_view
import kotlinx.android.synthetic.main.activity_note_list.toolbar as toolbar_custom

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes : MutableList<Note>
    lateinit var adapter : NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        notes = loadNotes(this)

        adapter = NoteAdapter(notes, this)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        create_note_fab.setOnClickListener(this)

        setSupportActionBar(toolbar_custom)
    }

    override fun onClick(view: View) {
        if (view.tag != null) {
            Log.i("NoteListAdapter", "on click sur une note de la liste")
            this.showNoteDetail(view.tag as Int)
        } else {
            when(view.id) {
                R.id.create_note_fab -> createNewNote()
            }
        }
    }

    private fun createNewNote() {
        showNoteDetail(-1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK != resultCode || data == null) {
            return;
        }

        when (requestCode) {
            NoteDetailActivity.REQUEST_EDIT_NOTE -> processEditNoteResult(data)
        }
    }

    private fun processEditNoteResult(data: Intent) {
        val noteIndex = data.getIntExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, -1)

        when (data.action) {
            NoteDetailActivity.ACTION_SAVE_NOTE -> {
                val note = data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE)
                this.saveNote(note, noteIndex)
            }

            NoteDetailActivity.ACTION_DELETE_NOTE -> {
                this.deleteNote(noteIndex)
            }
        }

    }

    fun saveNote(note: Note, noteIndex: Int) {
        persistNote(this, note)
        if (noteIndex < 0) {
            notes.add(0, note)
        } else {
            this.notes[noteIndex] = note
        }

        this.adapter.notifyDataSetChanged()
    }

    private fun deleteNote(noteIndex: Int) {

        if (noteIndex < 0) {
            return
        }

        val note = notes.removeAt(noteIndex)

        com.example.myapplication.utils.deleteNote(this, note)

        this.adapter.notifyDataSetChanged()

        Snackbar.make(this.coordinator_layout, "${note.title} supprimé", Snackbar.LENGTH_SHORT)
            .show()
    }

    fun showNoteDetail(noteIndex: Int) {
        val note = if (noteIndex < 0) Note() else notes[noteIndex]

        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note as Parcelable)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, noteIndex)

        startActivityForResult(intent, NoteDetailActivity.REQUEST_EDIT_NOTE)
    }
}
