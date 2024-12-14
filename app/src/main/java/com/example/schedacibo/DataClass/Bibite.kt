package com.example.schedacibo.DataClass

import android.os.Parcel
import android.os.Parcelable

data class Bibite(
    val id: Int? = 0,
    val immagine: String? = "",
    val nome: String? = "",
    val prezzo: String? = "",
    val tipologia: String? = "",
    val ingredienti: String? = "",
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readValue(Int::class.java.classLoader) as? Int,
        immagine = parcel.readString(),
        nome = parcel.readString(),
        prezzo = parcel.readString(),
        tipologia = parcel.readString(),
        ingredienti = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(immagine)
        parcel.writeString(nome)
        parcel.writeString(prezzo)
        parcel.writeString(tipologia)
        parcel.writeString(ingredienti)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bibite> {
        override fun createFromParcel(parcel: Parcel): Bibite {
            return Bibite(parcel)
        }

        override fun newArray(size: Int): Array<Bibite?> {
            return arrayOfNulls(size)
        }
    }
}
