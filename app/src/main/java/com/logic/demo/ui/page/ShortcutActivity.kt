package com.logic.demo.ui.page

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.logic.demo.R
import com.logic.demo.const.shortcutList
import com.logic.demo.databinding.ActivityShortcutBinding

class ShortcutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShortcutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShortcutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDynamicShortcuts()
    }

    private fun initDynamicShortcuts() {
        binding.shortcutNotAdded.removeAllViews()
        binding.shortcutAdded.removeAllViews()
        initAddDynamicShortcuts()
        initNotAddedDynamicShortcuts()
    }

    private fun initNotAddedDynamicShortcuts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)
            shortcutList.forEach { shortcut ->
                val shortcutInfo = shortcutManager.dynamicShortcuts.find { added ->
                    added.id == shortcut.id
                }
                if (shortcutInfo == null) {
                    val view =
                        LayoutInflater.from(this)
                            .inflate(
                                R.layout.title_and_button_item,
                                binding.shortcutNotAdded,
                                false
                            )
                    val title = view.findViewById<TextView>(R.id.title)
                    val button = view.findViewById<TextView>(R.id.button)
                    title.text = shortcut.longLabel
                    button.text = "添加"
                    button.setOnClickListener {
                        shortcutManager.addDynamicShortcuts(
                            listOf(
                                ShortcutInfo.Builder(this, shortcut.id)
                                    .setShortLabel(shortcut.shortLabel)
                                    .setLongLabel(shortcut.longLabel)
                                    .setIcon(Icon.createWithResource(this, shortcut.icon))
                                    .setIntent(Intent(Intent.ACTION_VIEW, null, this, shortcut.clazz))
                                    .build()
                            )
                        )
                        initDynamicShortcuts()
                    }
                    binding.shortcutNotAdded.addView(view)
                }
            }
        }
    }

    private fun initAddDynamicShortcuts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)
            shortcutManager.dynamicShortcuts.forEach { it ->
                val view =
                    LayoutInflater.from(this)
                        .inflate(R.layout.title_and_button_item, binding.shortcutAdded, false)
                val title = view.findViewById<TextView>(R.id.title)
                val button = view.findViewById<TextView>(R.id.button)
                title.text = it.longLabel
                button.text = "移除"
                button.setOnClickListener { _ ->
                    shortcutManager.removeDynamicShortcuts(listOf(
                        it.id
                    ))
                    initDynamicShortcuts()
                }
                binding.shortcutAdded.addView(view)
            }
        }
    }
}