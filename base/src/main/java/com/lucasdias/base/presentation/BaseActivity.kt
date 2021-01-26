package com.lucasdias.base.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.lucasdias.base.R
import com.lucasdias.extensions.getIntOrThrow

open class BaseActivity : AppCompatActivity() {

    protected open val navigationGraphId: Int by lazy { intent.getIntOrThrow(GRAPH_ID_KEY) }
    protected open val layoutIdRes: Int by lazy { R.layout.activity_base }
    protected open val fragmentContainerIdRes: Int by lazy { R.id.fragment_container_base_activity }
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutIdRes)
        setupNavigation()
        setupToolbar()
    }

    private fun setupNavigation() {
        supportFragmentManager.findFragmentById(fragmentContainerIdRes)?.let { fragment ->
            navigationController = fragment.findNavController().apply {
                val graph = navInflater.inflate(navigationGraphId)
                setGraph(graph, getInitialArguments())
            }
        }
    }

    private fun setupToolbar() {
        supportActionBar?.hide()
    }

    private fun getInitialArguments(): Bundle? = intent.extras

    companion object {

        private const val GRAPH_ID_KEY = "GRAPH_ID_KEY"

        fun launch(
            context: Context,
            @NavigationRes graphResId: Int,
            block: (() -> Bundle)? = null
        ) {
            val bundle = (block?.invoke() ?: Bundle()).apply {
                putInt(GRAPH_ID_KEY, graphResId)
            }

            val intent = Intent(context, BaseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}
