package com.example.navigationexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.navigationexample.databinding.ActivityMainBinding

private const val SCREEN_COUNT_ARGS = "SCREEN_COUNT_ARGS"

class MainActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context, count: Int): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(SCREEN_COUNT_ARGS, count)
            return intent
        }
    }

    private lateinit var binding: ActivityMainBinding
    private var screenCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        screenCount = intent.getIntExtra(SCREEN_COUNT_ARGS, 1)

        logLifecycle("OnCreate")
        binding.txtScreenCount.text = screenCount.toString()

        binding.btnNavigate.setOnClickListener {
            startActivity(newIntent(this, screenCount + 1))
        }
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("onRestart")
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("OnStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("OnResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("OnPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("OnDestroy")
    }

    private fun logLifecycle(lifecycle: String) {
        Log.d("Lifecycle", "Activity $screenCount: $lifecycle Called")
    }
}