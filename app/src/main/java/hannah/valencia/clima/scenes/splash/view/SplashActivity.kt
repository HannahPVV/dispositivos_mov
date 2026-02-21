package hannah.valencia.clima.scenes.splash.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import hannah.valencia.clima.databinding.ActivitySplashBinding
import hannah.valencia.clima.scenes.base.BaseActivity
import hannah.valencia.clima.scenes.splash.viewModel.SplashViewModel

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel
    private val TAG = SplashActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        this.configureActivity()
        }

    private fun configureActivity(){
        this.initActivityView()
        this.supportActionBar?.hide()
    }

    private fun initActivityView(){
        this.binding= ActivitySplashBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.viewModel= SplashViewModel(this, this)
    }

}