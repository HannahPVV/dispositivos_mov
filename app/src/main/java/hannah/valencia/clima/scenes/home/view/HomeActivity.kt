package hannah.valencia.clima.scenes.home.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import hannah.valencia.clima.R
import hannah.valencia.clima.databinding.ActivityHomeBinding
import hannah.valencia.clima.scenes.base.BaseActivity
import hannah.valencia.clima.scenes.home.viewModel.HomeViewModel
import hannah.valencia.clima.scenes.list.view.ListFragment
import hannah.valencia.clima.scenes.profile.view.ProfileFragment


class HomeActivity : BaseActivity(){
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        this.configureActivity()
    }

    private fun configureActivity(){
        this.initActivityView()
        this.configureListener()
        this.replaceFragment(ListFragment())
    }

    private fun initActivityView(){
        this.binding = ActivityHomeBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.viewModel = HomeViewModel(this, this)
    }

    private fun configureListener(){
        this.binding.bnvHome.setOnItemSelectedListener { item->
            when (item.itemId){
                R.id.list_menu-> this.replaceFragment(ListFragment())
                R.id.profile_menu-> this.replaceFragment(ProfileFragment())
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction= this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_content,fragment).commit()
    }

}