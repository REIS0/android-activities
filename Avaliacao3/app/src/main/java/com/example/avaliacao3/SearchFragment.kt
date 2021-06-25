package com.example.avaliacao3

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avaliacao3.model.City
import com.example.avaliacao3.model.WeatherList
import com.example.avaliacao3.utils.ApiRequest
import kotlinx.android.synthetic.main.fragment_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "SearchFragment"
private const val SHARED_PREFERENCE_NAME = "weather_app_preferences"
private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val KEY = BuildConfig.API_KEY

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val weatherList: MutableList<City> = ArrayList()
    private val adapter = WeatherAdapter(weatherList)

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
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        view.recycler_view.adapter = adapter
        view.recycler_view.layoutManager = LinearLayoutManager(this.activity)
        view.recycler_view.setHasFixedSize(true)

        view.button_search.setOnClickListener {
            view.progress_bar.visibility = View.VISIBLE
            search(view)
        }

        Log.i(TAG, "onCreateView() - fragment loaded")
        return view
    }

    private fun search(view: View) {
        // Sleep para mostrar a progress bar
//        Thread.sleep(2000)
        val context = view.context
        if (isInternetAvailable(context)) {
            Log.i(TAG, "search() - Connection available")

            val prefs = activity?.getSharedPreferences(SHARED_PREFERENCE_NAME, 0)
            val units = prefs?.getString("Temperature", "metric").toString()
            val lang = prefs?.getString("Language", "en").toString()

            val city = view.edit_text_search.text.toString()

            Log.d(TAG, prefs.toString())
            Log.d(TAG, units)
            Log.d(TAG, lang)
            Log.d(TAG, city)

            makeRequest(view, city, units, lang)
        } else {
            Log.e(TAG, "search() - Connection not available")
            val toast = Toast.makeText(context, "No connection, Offline", Toast.LENGTH_LONG)
            toast.show()
        }

    }

    private fun makeRequest(view: View,city: String, units: String, lang: String) {
        weatherList.clear()
        Log.i(TAG, "makeRequest() - making request...")

        val api =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiRequest::class.java)

        api.requestWeatherData(city, units, lang, KEY).enqueue(object : Callback<WeatherList?> {
            override fun onResponse(call: Call<WeatherList?>, response: Response<WeatherList?>) {
                val response = response.body()
                view.progress_bar.visibility = View.INVISIBLE
                Log.i(TAG, "makeRequest() - request success")
                if (response != null) {
                    weatherList.addAll(response.list)
                    adapter.notifyDataSetChanged()
                    Log.i(TAG, "search() - Recycler view updated")
                }
            }

            override fun onFailure(call: Call<WeatherList?>, t: Throwable) {
                view.progress_bar.visibility = View.INVISIBLE
                Log.e(TAG, "makeRequest() - request failed")
            }

        })
    }

    /* -------- */
    @Suppress("DEPRECATION")
    private fun isInternetAvailable(context: Context): Boolean {
        Log.i(TAG, "isInternetAvailable() - Checking connection...")
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.getNetworkCapabilities(
                cm.activeNetwork
            )?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(
                        NetworkCapabilities.TRANSPORT_CELLULAR
                    ) -> true
                    else -> false
                }
            }
        } else {
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    result = true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    result = true
                }
            }
        }
        return result
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}