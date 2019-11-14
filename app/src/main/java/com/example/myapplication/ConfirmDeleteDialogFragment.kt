package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class ConfirmDeleteDialogFragment : DialogFragment() {

    interface ConfirmDeleteListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    var listener: ConfirmDeleteListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(activity)

        builder.setMessage("Tout supprimer ?")
            .setPositiveButton("Yesss", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Log.println(Log.INFO, "ConfirmDeleteDialog  ", "Youpi on va tout caser ")
                    listener?.onDialogPositiveClick()
                }
            })
            .setNegativeButton("Euh non...", DialogInterface.OnClickListener { dialog, id ->
                Log.println(Log.ERROR, "ConfirmDeleteDialog", "Tant pis ce sera pour la prochaijne")
                listener?.onDialogNegativeClick()
            })


        return builder.create()
    }
}