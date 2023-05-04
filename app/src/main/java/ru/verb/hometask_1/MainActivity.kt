package ru.verb.hometask_1

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import ru.verb.hometask_1.databinding.ActivityMainBinding
import ru.verb.hometask_1.databinding.CustomDialogLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        with(binding) {
            chipGroup.setOnCheckedStateChangeListener { _, _ ->
                run {
                    when (chipGroup.checkedChipId) {
                        chipAngry.id -> {
                            showToast(Angry.messageToTheWorld)
                            true
                        }
                        chipSad.id -> {
                            showToast(Sad.messageToTheWorld)
                            true
                        }
                        chipHappy.id -> {
                            showToast(Happy.messageToTheWorld)
                            true
                        }
                        chipCustomMood.id -> {
                            showCustomMoodDialog()
                            true
                        }
                        else -> false
                    }
                }.let {
                    if (!it) {
                        showToast(getString(R.string.something_goes_wrong))
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showCustomMoodDialog() {
        val customDialogBinding = CustomDialogLayoutBinding.inflate(layoutInflater)
        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when (which) {
                    Dialog.BUTTON_POSITIVE -> {
                        MyCustomMood(customDialogBinding.dialogEditText.text.toString()).apply {
                            if (this.myOwnMood.isBlank()) {
                                showToast(getString(R.string.default_message_to_the_world))
                            } else {
                                showToast(this.myOwnMood)
                            }
                        }
                    }
                    Dialog.BUTTON_NEGATIVE -> showToast(getString(R.string.default_message_to_the_world))
                }
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(getString(R.string.dialog_title))
            .setView(customDialogBinding.root)
            .setPositiveButton(R.string.positive_button_text, listener)
            .setNegativeButton(R.string.negative_button_text, listener)
            .create()
        dialog.show()
    }
}