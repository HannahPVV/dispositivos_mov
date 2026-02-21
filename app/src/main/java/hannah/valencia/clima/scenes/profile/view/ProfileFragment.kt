package hannah.valencia.clima.scenes.profile.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import hannah.valencia.clima.databinding.FragmentProfileBinding
import hannah.valencia.clima.scenes.help.HelpActivity
import java.io.File

class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentProfileBinding
    lateinit var pictureUri: Uri

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { isImageSaved ->
        if (isImageSaved)
            this.binding.ivProfile.setImageURI(this.pictureUri)//Enviamos la imagen a nuestro ImageView
    }

    private  val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {uri->
        this.binding.ivProfile.setImageURI(uri)
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentProfileBinding.inflate(inflater, container, false)

        this.binding.cvHelp.setOnClickListener {
            this.startActivity(
                Intent(activity, HelpActivity::class.java)
            )
        }

        this.binding.btnTakePhoto.setOnClickListener{
            val prefix = "photo-"
            val postfix = System.currentTimeMillis().toString()
            val directory = this.requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val photoFile = File.createTempFile(prefix, postfix, directory)

            this.pictureUri= FileProvider.getUriForFile(
                this.requireContext(),
                "hannah.valencia.hello_world.fileprovider",
                photoFile

            )
            this.takePicture.launch(this.pictureUri)
        }

        this.binding.btnChoosePhoto.setOnClickListener {
            this.galleryLauncher.launch("image/*")
        }


        return this.binding.root

    }








    }

