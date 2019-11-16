package com.example.myapplication.utils

import android.content.Context
import android.text.TextUtils
import com.example.myapplication.model.Note
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

private val TAG = "storage"

fun persistNote(context: Context, note: Note) {
    if (TextUtils.isEmpty(note.filename)) {
        note.filename = UUID.randomUUID().toString() + ".note"
    }

    var fileOutput = context.openFileOutput(note.filename, Context.MODE_PRIVATE)

    val outputStream = ObjectOutputStream(fileOutput)

    outputStream.writeObject(note)

    outputStream.close()
}

private fun loadNote(context: Context, filename: String): Note {
    var fileInput = context.openFileInput(filename)

    var inputStream = ObjectInputStream(fileInput)

    val note = inputStream.readObject() as Note

    inputStream.close()

    return note
}

fun loadNotes(context: Context): MutableList<Note> {
    var notes = mutableListOf<Note>()

    val notesDir = context.filesDir

    for (filename in notesDir.list()) {
        notes.add(loadNote(context, filename))
    }

    return notes
}

fun deleteNote(context: Context, note: Note) {
    context.deleteFile(note.filename)
}