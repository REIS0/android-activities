package com.example.avaliacao3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_settings.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "SettingsFragment"
private const val SHARED_PREFERENCE_NAME = "weather_app_preferences"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        loadSettings(view)

        view.save_button.setOnClickListener {
            saveSettings(view)
        }

        Log.i(TAG, "onCrateView() - fragment loaded")
        return view
    }

    private fun saveSettings(view: View) {
        val isCelsius = view.radio_button_celsius.isChecked
        val isEnglish = view.radio_button_english.isChecked

        // * celsius = metric, fahrenheit = imperial
        val temp = if (isCelsius) {
            "metric"
        } else {
            "imperial"
        }

        // * english = en, portuguese = pt_br
        val lang = if (isEnglish) {
            "en"
        } else {
            "pt_br"
        }

        val prefs = activity?.getSharedPreferences(SHARED_PREFERENCE_NAME, 0)
        val editor = prefs?.edit()

        editor?.apply {
            putString("Temperature", temp)
            putString("Language", lang)
            apply()
            Log.i(TAG, "saveSettings() - preferences saved")
        }
    }

    private fun loadSettings(view: View) {
        val prefs = activity?.getSharedPreferences(SHARED_PREFERENCE_NAME, 0)

        if (prefs != null) {
            val temp = prefs.getString("Temperature", "metric")
            val lang = prefs.getString("Language", "en")
            if (temp == "metric") {
                view.radio_button_celsius.isChecked = true
                view.radio_button_fahrenheit.isChecked = false
            } else {
                view.radio_button_celsius.isChecked = false
                view.radio_button_fahrenheit.isChecked = true
            }

            if (lang == "en") {
                view.radio_button_english.isChecked = true
                view.radio_button_portugues.isChecked = false
            } else {
                view.radio_button_english.isChecked = false
                view.radio_button_portugues.isChecked = true
            }

            Log.i(TAG, "loadSettings() - settings loaded")
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}