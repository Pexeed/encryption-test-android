package com.pexe.encryption

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.pexe.encryption.databinding.ActivityMainBinding
import com.pexe.encryption.model.CarBodywork
import com.pexe.encryption.model.User
import com.pexe.encryption.repository.db.AppDatabase
import com.pexe.encryption.repository.service.ServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {

    lateinit var db: AppDatabase
    private lateinit var binding: ActivityMainBinding
    var users: MutableLiveData<List<User>> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val service = ServiceFactory.getCarsService()

        db = AppDatabase.getDatabase(this)
        binding.btnTest.setOnClickListener {
            val userTeste = User(binding.inputUsername.text.toString(), binding.inputPassword.text.toString())
            try {
                AsyncTask.execute {
                    db.userDAO().putUser(userTeste)
                }
            } catch ( e: Exception) {
                Toast.makeText(this, "User já existente", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnListUsers.setOnClickListener {
            AsyncTask.execute {
                users.postValue( db.userDAO().getUsers() )
            }
        }

        binding.btnCertificado.setOnClickListener {
            service.getCarBodyworks().enqueue( object: Callback<ArrayList<CarBodywork>?> {
                override fun onResponse(call: Call<ArrayList<CarBodywork>?>?,
                                        response: Response<ArrayList<CarBodywork>?>?) {
                    response?.body()?.let {
                        Toast.makeText(applicationContext, "Listou", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ArrayList<CarBodywork>?>?,
                                       t: Throwable?) {
                    Toast.makeText(applicationContext, "ERROOOOUUU", Toast.LENGTH_LONG).show()
                }
            } )
        }

        users.observe(this, Observer {
            var string = ""
            users.value?.forEach {
                string = " - userName:" + it.userName + "\n - password:" + it.password + "\n"
            }
            binding.tvList.text = string
        })
    }
}