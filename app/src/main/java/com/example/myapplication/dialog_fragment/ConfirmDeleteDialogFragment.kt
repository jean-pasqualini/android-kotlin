package com.example.myapplication.dialog_fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmDeleteDialogFragment(val noteTitle: String) : DialogFragment() {

    interface ConfirmDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    var listener: ConfirmDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(activity)

        builder.setMessage("Etes vous sur de supprimer la note '$noteTitle' ?")
            .setPositiveButton("Supprimer", { dialog, id  -> listener?.onDialogPositiveClick() })
            .setNegativeButton("Annuler", { dialog, id  -> listener?.onDialogNegativeClick() })

        return builder.create()
    }

}