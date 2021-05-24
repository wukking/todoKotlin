package com.wuyson.sample

import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*

class SettingsActivity : AppCompatActivity(),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, SettingsFragment())
                    .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val signature = sp.getString("signature", "")
    }

    /**
     * OnPreferenceChangeListener: 1.fragment implement 2.preference setListener
     */
    class SettingsFragment : PreferenceFragmentCompat(),Preference.OnPreferenceChangeListener {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            //获取Preference
            val signaturePreference: EditTextPreference? = findPreference("signature")
            //获取摘要
            val listSummaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
            val editSummaryProvider = EditTextPreference.SimpleSummaryProvider.getInstance()

            //自定义保存摘要信息
            val customPreference = findPreference<EditTextPreference>("counting")
            customPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> { preference ->
                val text = preference.text
                if (TextUtils.isEmpty(text)){
                    "not set"
                }else{
                    "长度为：${text.length}"
                }
            }

            //限制为纯数字
            val onlyNumPreference = findPreference<EditTextPreference>("onlyNum")
            onlyNumPreference?.setOnBindEditTextListener {
                it.inputType = InputType.TYPE_CLASS_NUMBER
            }
            onlyNumPreference?.setOnPreferenceClickListener {
                Toast.makeText(context, "haha", Toast.LENGTH_SHORT).show()
                true
            }

            onlyNumPreference?.onPreferenceChangeListener = this
        }

        override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
            Log.e("preference", "${preference?.title}  newValue is: $newValue")
            return true
        }
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference?
    ): Boolean {
        val args = pref?.extras
        val fragment = pref?.fragment?.let {
            supportFragmentManager.fragmentFactory.instantiate(
                classLoader,
                it
            )
        }
        fragment?.arguments = args
        fragment?.setTargetFragment(caller, 0)
        // Replace the existing Fragment with the new Fragment
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.settings, fragment)
                .addToBackStack(null)
                .commit()
        }
        return true
    }


}