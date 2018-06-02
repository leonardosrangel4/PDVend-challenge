package br.com.pdvend.pdvend.api

import android.os.Parcel
import android.os.Parcelable


data class RepositoryData(
        val items: List<Item>
)

data class Owner(
    val login: String
)

data class Item(
        val owner: Owner,
        val name: String,
        val open_issues: Int)

data class Issue(val number: Int,
                 val title: String,
                 val body: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(number)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Issue> {
        override fun createFromParcel(parcel: Parcel): Issue {
            return Issue(parcel)
        }

        override fun newArray(size: Int): Array<Issue?> {
            return arrayOfNulls(size)
        }
    }
}

data class PullRequestData(
    val number: Int,
    val state: String,
    val title: String,
    val body: String
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(number)
        parcel.writeString(state)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PullRequestData> {
        override fun createFromParcel(parcel: Parcel): PullRequestData {
            return PullRequestData(parcel)
        }

        override fun newArray(size: Int): Array<PullRequestData?> {
            return arrayOfNulls(size)
        }
    }
}