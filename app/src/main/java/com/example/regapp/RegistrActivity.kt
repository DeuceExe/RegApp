package com.example.regapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.regapp.Constance.Constance
import com.example.regapp.databinding.ActivityRegistrBinding


class RegistrActivity : AppCompatActivity() {
    private var launcher: ActivityResultLauncher<Intent>? = null
    private lateinit var binding: ActivityRegistrBinding
    private var signState = "Empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signState = intent.getStringExtra(Constance.SIGN_STATE)!!

        if (signState == Constance.SIGN_IN) {
            binding.imSelectAv.visibility = View.GONE
            binding.edNName.visibility = View.GONE
        }
    }

    fun onClickDone(view: View) {
        if (signState == Constance.SIGN_UP) {
            val intent = Intent()
            intent.putExtra(Constance.LOGIN, binding.edLogin.text.toString())
            intent.putExtra(Constance.PASS, binding.edPass.text.toString())
            intent.putExtra(Constance.USERNAME, binding.edNName.text.toString())
            if (binding.imSelectAv.isVisible) intent.putExtra(
                Constance.AVATAR_ID,
                R.drawable.avatar
            )
            setResult(Constance.REQUEST_CODE_UP, intent)
            finish()
        } else if (signState == Constance.SIGN_IN) {
            intent.putExtra(Constance.LOGIN, binding.edLogin.text.toString())
            intent.putExtra(Constance.PASS, binding.edPass.text.toString())
            setResult(Constance.REQUEST_CODE_IN, intent)
            finish()
        }
    }

    fun onClickAvatar(view: View) {
        binding.imSelectAv.visibility = View.VISIBLE
    }
}