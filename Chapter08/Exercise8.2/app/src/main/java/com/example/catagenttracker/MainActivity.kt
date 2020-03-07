package com.example.catagenttracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.catagenttracker.RouteTrackingService.Companion.EXTRA_SECRET_CAT_AGENT_ID
import com.example.catagenttracker.worker.CatFurGroomingWorker
import com.example.catagenttracker.worker.CatLitterBoxSittingWorker
import com.example.catagenttracker.worker.CatStretchingWorker
import com.example.catagenttracker.worker.CatSuitUpWorker

class MainActivity : AppCompatActivity() {
    private val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkConstraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val catAgentId = "CatAgent1"
        val catStretchingRequest = OneTimeWorkRequest.Builder(CatStretchingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(
                getCatAgentIdInputData(CatStretchingWorker.INPUT_DATA_CAT_AGENT_ID, catAgentId)
            ).build()
        val catFurGroomingRequest = OneTimeWorkRequest.Builder(CatFurGroomingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(
                getCatAgentIdInputData(CatFurGroomingWorker.INPUT_DATA_CAT_AGENT_ID, catAgentId)
            ).build()
        val catLitterBoxSittingRequest =
            OneTimeWorkRequest.Builder(CatLitterBoxSittingWorker::class.java)
                .setConstraints(networkConstraints)
                .setInputData(
                    getCatAgentIdInputData(
                        CatLitterBoxSittingWorker.INPUT_DATA_CAT_AGENT_ID,
                        catAgentId
                    )
                ).build()
        val catSuitUpRequest = OneTimeWorkRequest.Builder(CatSuitUpWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(
                getCatAgentIdInputData(CatSuitUpWorker.INPUT_DATA_CAT_AGENT_ID, catAgentId)
            ).build()

        workManager.getWorkInfoByIdLiveData(catStretchingRequest.id)
            .observe(this, Observer { info ->
                if (info.state.isFinished) {
                    showResult("Agent done stretching")
                }
            })

        workManager.getWorkInfoByIdLiveData(catFurGroomingRequest.id)
            .observe(this, Observer { info ->
                if (info.state.isFinished) {
                    showResult("Agent done grooming its fur")
                }
            })

        workManager.getWorkInfoByIdLiveData(catLitterBoxSittingRequest.id)
            .observe(this, Observer { info ->
                if (info.state.isFinished) {
                    showResult("Agent done sitting in litter box")
                }
            })

        workManager.getWorkInfoByIdLiveData(catSuitUpRequest.id)
            .observe(this, Observer { info ->
                if (info.state.isFinished) {
                    showResult("Agent done suiting up. Ready to go!")
                }
            })

        workManager.beginWith(catStretchingRequest)
            .then(catFurGroomingRequest)
            .then(catLitterBoxSittingRequest)
            .then(catSuitUpRequest)
            .enqueue()
    }

    private fun launchTrackingService() {
        RouteTrackingService.trackingCompletion.observe(this, Observer { agentId ->
            showResult("Agent $agentId arrived!")
        })
        val serviceIntent = Intent(this, RouteTrackingService::class.java).apply {
            putExtra(EXTRA_SECRET_CAT_AGENT_ID, "007")
        }
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    private fun showResult(message: String) {
        Toast.makeText(this, message, LENGTH_SHORT).show()
    }

    private fun getCatAgentIdInputData(catAgentIdKey: String, catAgentIdValue: String) =
        Data.Builder().putString(catAgentIdKey, catAgentIdValue)
            .build()
}
