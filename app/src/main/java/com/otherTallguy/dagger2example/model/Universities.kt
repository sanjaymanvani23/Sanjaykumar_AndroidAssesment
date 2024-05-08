package com.otherTallguy.dagger2example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Universities(

    @SerializedName("state-province")
    val stateProvince: String?,
    @SerializedName("domains")
    val domains: List<String>,
    @SerializedName("web_pages")
    val webPages: List<String>,
    @PrimaryKey
    @SerializedName("name")
    val name: String,
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String,
    @SerializedName("country")
    val country: String,
)