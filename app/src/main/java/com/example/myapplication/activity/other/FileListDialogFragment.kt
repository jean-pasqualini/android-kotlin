package com.example.myapplication.activity.other

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R

class FileListDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(activity)

        var inflater = activity?.layoutInflater

        builder.setView(inflater?.inflate(R.layout.dialog_file_list, null))
            .setPositiveButton("Supprimer", null)
            .setNegativeButton("Annuller", null)
            .setTitle("Contenu supprimer : ")

        return builder.create()
    }
}