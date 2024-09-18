package com.example.dokyudashprototype

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Adjusting system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up the button to navigate to the AgencyActivity
        val agencyButton: Button = findViewById(R.id.agency_button)
        agencyButton.setOnClickListener {
            // Create an Intent to start the AgencyActivity
            val intent = Intent(this, AgencyActivity::class.java)
            startActivity(intent) // Start the AgencyActivity
        }
        val transactionButton: Button = findViewById(R.id.transaction_button)
        transactionButton.setOnClickListener {
            // Create an Intent to start the AgencyActivity
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent) // Start the AgencyActivity
        }
        val documentsButton: Button = findViewById(R.id.documents_button)
        documentsButton.setOnClickListener {
            // Create an Intent to start the AgencyActivity
            val intent = Intent(this, DocumentsActivity::class.java)
            startActivity(intent) // Start the AgencyActivity
        }
        val historyButton: Button = findViewById(R.id.history_button)
        historyButton.setOnClickListener {
            // Create an Intent to start the AgencyActivity
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent) // Start the AgencyActivity
        }

    }
}
