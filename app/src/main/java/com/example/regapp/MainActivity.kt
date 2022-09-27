package com.example.regapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.regapp.Constance.Constance
import com.example.regapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null
    private var login: String = "Empty"
    private var pass: String = "Empty"
    private var name: String = "Empty"
    private var avatarId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result: ActivityResult ->
                if (result.resultCode == Constance.REQUEST_CODE_IN) {
                    val l = result.data?.getStringExtra(Constance.LOGIN)
                    val p = result.data?.getStringExtra(Constance.PASS)

                    if (login == l && pass == p) {
                        binding.imAvatar.setImageResource(avatarId)
                        binding.imAvatar.visibility = View.VISIBLE
                        binding.tvInfo.text = name
                        binding.btUp.visibility = View.GONE
                        binding.btIn.text = "Exit"

                    } else {
                        binding.imAvatar.visibility = View.INVISIBLE
                        binding.tvInfo.text = "Incorrect data"
                    }

                } else if (result.resultCode == Constance.REQUEST_CODE_UP) {
                    binding.imAvatar.visibility = View.VISIBLE
                    login = result.data?.getStringExtra(Constance.LOGIN)!!
                    pass = result.data?.getStringExtra(Constance.PASS)!!
                    name = result.data?.getStringExtra(Constance.USERNAME)!!
                    avatarId = result.data?.getIntExtra(Constance.AVATAR_ID, 0)!!
                    binding.imAvatar.setImageResource(avatarId)
                    binding.tvInfo.text = name
                    binding.btUp.visibility = View.GONE
                    binding.btIn.text = "Exit"
                }
            }
    }

    fun onClickSignUp(view: View) {
        val intent = Intent(this, RegistrActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP)
        launcher?.launch(intent)
    }

    fun onClickSignIn(view: View) {
        val intent = Intent(this, RegistrActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN)
        launcher?.launch(intent)
    }
}