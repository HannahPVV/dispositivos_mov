package hannah.valencia.clima.scenes.help

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import hannah.valencia.clima.databinding.ActivityHelpBinding
import hannah.valencia.clima.scenes.base.BaseActivity

class HelpActivity : BaseActivity() {
    private lateinit var binding: ActivityHelpBinding
    private var prayer: ExoPlayer?= null
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        this.configureActivity()
    }

    override fun onStart() {
        super.onStart()
        this.initializePlayer()
    }

    override fun onStop() {
        //Liberar recursos de reproductor
        super.onStop()
        this.prayer?.release()
        this.prayer=null
    }



    private fun configureActivity() {
        this.binding = ActivityHelpBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.binding.icBack.setOnClickListener { this.finish() } //FINALIZA LA ACTIVIDAD

    }

    private fun initializePlayer(){
        //1. Creando la instancia del reproductor
        this.prayer= ExoPlayer.Builder(this).build().also { exoPlayer ->

            //2. Vinculando
            this.binding.playerView.player=exoPlayer


            //3. Crear un mediaitem
            //cargar multimedia ( Puedes ser uURL o un archivo local raw/assets)
            val uri = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            val mediaItem = MediaItem.Builder()
                .setUri(uri)
                .build()

            exoPlayer.setMediaItem(mediaItem)

            //4. comenzar la reprod
            exoPlayer.prepare()
            exoPlayer.play()



        }
    }

}

