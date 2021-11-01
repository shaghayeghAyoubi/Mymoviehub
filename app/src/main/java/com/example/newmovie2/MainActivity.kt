package com.example.newmovie2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.newmovie2.databinding.ActivityMainBinding
import com.example.newmovie2.viewmodels.MainActivityVM

class MainActivity : AppCompatActivity() {

    val mainActivityVM: MainActivityVM by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun setIcon(menuItem: MenuItem?) {
        menuItem?.icon =
            if (mainActivityVM.isLinearLayoutManager.value == true) {
                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
            } else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.layout_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_switch_layout -> {
                if (mainActivityVM.isLinearLayoutManager.value == false) {
                    mainActivityVM.setLayout(true)
                } else
                    mainActivityVM.setLayout(false)
                setIcon(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}