package com.example.homework1.sensor

abstract class MeasurableSensor(
    protected val sensorType: Int
) {
    protected var onSensorValuesChanged: ((Array<Float>) -> Unit)? = null

    abstract val doesSensorExist: Boolean

    abstract suspend fun startListening()
    abstract fun stopListening()

    fun setOnSensorValuesChangedListener(listener: (Array<Float>) -> Unit) {
        onSensorValuesChanged = listener
    }
}