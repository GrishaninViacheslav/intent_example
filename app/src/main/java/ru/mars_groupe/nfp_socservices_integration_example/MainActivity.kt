package ru.mars_groupe.nfp_socservices_integration_example

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson
import ru.mars_groupe.nfp_socservices_integration_example.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            startProcess1.setOnClickListener {
                lunchNfp(noControlCertificateId)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun lunchNfp(nfpSocservicesRequestEntity: NfpSocservicesRequestEntity) {
        val nfpSocservicesRequestEntityJsonValue = Gson().toJson(
            nfpSocservicesRequestEntity
        )
        val intent =
            Intent("ru.mars_groupe.app_android_socservices.presentation.activities.NfpExportActivity")
        intent.component = ComponentName(
            "ru.mars_groupe.app_android_socservices",
            "ru.mars_groupe.app_android_socservices.presentation.activities.NfpExportActivity"
        )
        intent.putExtra(
            "reqJSON",
            nfpSocservicesRequestEntityJsonValue
        )
        binding.message.setText("REQUEST:Intent:\n$intent\nnfpSocservicesRequestEntity:\n$nfpSocservicesRequestEntity")
        try {
            startForResult.launch(intent)
        } catch (e: Exception) {
            binding.message.setText("${binding.message.text}\n\nException:\n$e")
        }
    }

    @SuppressLint("SetTextI18n")
    private val startForResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            when (result.resultCode) {
                0 -> result.data?.getStringExtra("responseJSON")?.let { responseJSON ->
                    binding.message.setText("${binding.message.text}\n\nRESPONCE:\nresult.resultCode: ${result.resultCode}\nresponseJSON:\n$responseJSON")
                }

                400, 401, 403, 404 -> {
                    binding.message.setText("${binding.message.text}\n\nRESPONCE:\nresult.resultCode: ${result.resultCode}")
                }

                else -> {
                    binding.message.setText("${binding.message.text}\n\nRESPONCE:\nUndefined result.resultCode: ${result.resultCode}")
                }
            }
        }

    private val noControlCertificateId = NfpSocservicesRequestEntity(
        "22",
        "22",
        false,
        null,
        null,
        listOf()
    )
}