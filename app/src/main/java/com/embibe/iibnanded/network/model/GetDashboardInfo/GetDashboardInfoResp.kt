package com.embibe.iibnanded.network.model.GetDashboardInfo

import java.util.HashMap

import com.embibe.iibnanded.network.utils.BaseResponse
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("test_id", "test_name", "test_total_time", "no_of_questions", "test_status")
class GetDashboardInfoResp : BaseResponse() {

    @JsonProperty("test_id")
    @get:JsonProperty("test_id")
    @set:JsonProperty("test_id")
    var testId: String? = null
    @JsonProperty("test_name")
    @get:JsonProperty("test_name")
    @set:JsonProperty("test_name")
    var testName: String? = null
    @JsonProperty("test_total_time")
    @get:JsonProperty("test_total_time")
    @set:JsonProperty("test_total_time")
    var testTotalTime: String? = null
    @JsonProperty("no_of_questions")
    @get:JsonProperty("no_of_questions")
    @set:JsonProperty("no_of_questions")
    var noOfQuestions: String? = null
    @JsonProperty("test_status")
    @get:JsonProperty("test_status")
    @set:JsonProperty("test_status")
    var testStatus: String? = null
    @JsonIgnore
    private val additionalProperties = HashMap<String, Any>()

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties.put(name, value)
    }

}
