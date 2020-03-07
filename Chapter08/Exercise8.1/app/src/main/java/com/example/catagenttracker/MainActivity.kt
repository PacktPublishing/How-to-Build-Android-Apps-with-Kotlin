package com.example.catagenttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import com.example.catagenttracker.worker.CatFurGroomingWorker
import com.example.catagenttracker.worker.CatLitterBoxSittingWorker
import com.example.catagenttracker.worker.CatStretchingWorker
import com.example.catagenttracker.worker.CatSuitUpWorker

class MainActivity : AppCompatActivity() {

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
    }

    private fun getCatAgentIdInputData(catAgentIdKey: String, catAgentIdValue: String) =
        Data.Builder().putString(catAgentIdKey, catAgentIdValue)
            .build()
}
