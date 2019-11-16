package com.example.myapplication.activity.notepad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.NoteAdapter
import com.example.myapplication.model.Note
import kotlinx.android.synthetic.main.activity_note_list.notes_recycler_view as recycler_view
import kotlinx.android.synthetic.main.activity_note_list.toolbar as toolbar_custom

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes : MutableList<Note>
    lateinit var adapter : NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        notes = mutableListOf<Note>()

        notes.add(Note("Note 1", "Blalala"))
        notes.add(Note("Note 2", "Blalala"))
        notes.add(Note("Note 3", "Blalala"))
        notes.add(Note("Note 4", "Blalala"))

        adapter = NoteAdapter(notes, this)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        setSupportActionBar(toolbar_custom)
    }

    override fun onClick(view: View) {
        if (view.tag != null) {
            Log.i("NoteListAdapter", "on click sur une note de la liste")
            this.showNoteDetail(view.tag as Int)
        }
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
        val note = data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE)
        val noteIndex = data.getIntExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, -1)

        this.saveNote(note, noteIndex)
    }

    fun saveNote(note: Note, noteIndex: Int) {
        this.notes[noteIndex] = note

        this.adapter.notifyDataSetChanged()
    }

    fun showNoteDetail(noteIndex: Int) {
        val note = notes[noteIndex]

        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, noteIndex)

        startActivityForResult(intent, NoteDetailActivity.REQUEST_EDIT_NOTE)
    }
}
