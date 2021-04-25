package com.example.avaliacao1

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fruta(val nome: String, val beneficios: String, val imagem: Uri?, val insertion: Int) : Parcelable {
}