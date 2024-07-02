package com.task.bottomnavigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.task.bottomnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    private var navController:NavController?=null
    var infoList = arrayListOf<Info>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navController = findNavController(R.id.navController)
        navController?.addOnDestinationChangedListener{_,destination,_->
            when(destination.id){
                R.id.firstFragment-> {
                    supportActionBar?.title = "Items"
                    binding?.bottomNav?.menu?.get(0)?.setChecked(true)
                }
                R.id.secondFragment-> {
                    supportActionBar?.title = "Select Item"
                    binding?.bottomNav?.menu?.get(1)?.setChecked(true)
                }
            }
        }
        binding?.bottomNav?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fragmentOne-> navController?.navigate(R.id.firstFragment)
                R.id.fragmentTwo-> navController?.navigate(R.id.secondFragment)
            }
            return@setOnItemSelectedListener true
        }
    }
}