package com.wuyson.todokotlin.component

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.service.controls.Control
import android.service.controls.ControlsProviderService
import android.service.controls.DeviceTypes
import android.service.controls.actions.BooleanAction
import android.service.controls.actions.ControlAction
import android.service.controls.templates.ControlTemplate
import io.reactivex.Flowable
import io.reactivex.processors.ReplayProcessor
import org.reactivestreams.FlowAdapters
import java.util.concurrent.Flow

/**
 *
 * @author: Wuyson
 * @date: 2020/12/30
 */

class MyControlService : ControlsProviderService() {
    var CONTROL_REQUEST_CODE = 100
    var UNIQUE_DEVICE_ID = "100"
    var STRUCTURE = "bathroom"
    var TITLE = "Wuyson设备"
    var SUBTITLE = "Wuyson手表"

    override fun createPublisherForAllAvailable(): Flow.Publisher<Control> {
        val context: Context = baseContext
        val i = Intent()
        val pi =
                PendingIntent.getActivity(
                        context, CONTROL_REQUEST_CODE, i,
                        PendingIntent.FLAG_UPDATE_CURRENT
                )
        val controls = mutableListOf<Control>()
        val control =
                Control.StatelessBuilder(UNIQUE_DEVICE_ID, pi)
                        // Required: The name of the control
                        .setTitle(TITLE)
                        // Required: Usually the room where the control is located
                        .setSubtitle(SUBTITLE)
                        // Optional: Structure where the control is located, an example would be a house
                        .setStructure(STRUCTURE)
                        // Required: Type of device, i.e., thermostat, light, switch
                        .setDeviceType(DeviceTypes.TYPE_THERMOSTAT) // For example, DeviceTypes.TYPE_THERMOSTAT
                        .build()
        controls.add(control)
        // Create more controls here if needed and add it to the ArrayList

        // Uses the RxJava 2 library
        return FlowAdapters.toFlowPublisher(Flowable.fromIterable(controls))
    }

    override fun createPublisherFor(controlIds: MutableList<String>): Flow.Publisher<Control> {
        val context: Context = baseContext
        lateinit var control: Control
        /* Fill in details for the activity related to this device. On long press,
         * this Intent will be launched in a bottomsheet. Please design the activity
         * accordingly to fit a more limited space (about 2/3 screen height).
         */
        val i = Intent(this, ComponentSetttingsActivity::class.java)
        val pi =
                PendingIntent.getActivity(context, CONTROL_REQUEST_CODE, i, PendingIntent.FLAG_UPDATE_CURRENT)
        var updatePublisher = ReplayProcessor.create<Control>()
        val controls = mutableListOf<Control>()

        if (controlIds.contains(UNIQUE_DEVICE_ID)) {
            control = Control.StatefulBuilder(UNIQUE_DEVICE_ID, pi)
                    // Required: The name of the control
                    .setControlTemplate(ControlTemplate.getNoTemplateObject())
                    .setTitle(TITLE)
                    // Required: Usually the room where the control is located
                    .setSubtitle(SUBTITLE)
                    // Optional: Structure where the control is located, an example would be a house
                    .setStructure(STRUCTURE)
                    // Required: Type of device, i.e., thermostat, light, switch
                    .setDeviceType(DeviceTypes.TYPE_THERMOSTAT) // For example, DeviceTypes.TYPE_THERMOSTAT
                    // Required: Current status of the device
                    .setStatus(Control.STATUS_OK) // For example, Control.STATUS_OK
                    .build()

            updatePublisher.onNext(control)
        }
        controls.add(control)

        // If you have other controls, check that they have been selected here

        // Uses the Reactive Streams API
        updatePublisher.onNext(control)
        return FlowAdapters.toFlowPublisher(Flowable.fromIterable(controls))
    }

    override fun performControlAction(controlId: String, action: ControlAction, consumer: java.util.function.Consumer<Int>) {
        val context: Context = baseContext

        val i = Intent(this, ComponentSetttingsActivity::class.java)
        val pi =
                PendingIntent.getActivity(context, CONTROL_REQUEST_CODE, i, PendingIntent.FLAG_UPDATE_CURRENT)
        var updatePublisher = ReplayProcessor.create<Control>()

        if (action is BooleanAction) {

            // Inform SystemUI that the action has been received and is being processed
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                consumer.accept(ControlAction.RESPONSE_OK)
            }

            // In this example, action.getNewState() will have the requested action: true for “On”,
            // false for “Off”.

            /* This is where application logic/network requests would be invoked to update the state of
             * the device.
             * After updating, the application should use the publisher to update SystemUI with the new
             * state.
             */
            var control = Control.StatefulBuilder(UNIQUE_DEVICE_ID, pi)
                    .setControlTemplate(ControlTemplate.getNoTemplateObject())
                    // Required: The name of the control
                    .setTitle(TITLE)
                    // Required: Usually the room where the control is located
                    .setSubtitle(SUBTITLE)
                    // Optional: Structure where the control is located, an example would be a house
                    .setStructure(STRUCTURE)
                    // Required: Type of device, i.e., thermostat, light, switch
                    .setDeviceType(DeviceTypes.TYPE_THERMOSTAT) // For example, DeviceTypes.TYPE_THERMOSTAT
                    // Required: Current status of the device
                    .setStatus(Control.STATUS_OK) // For example, Control.STATUS_OK
                    .build()

            // This is the publisher the application created during the call to createPublisherFor()
            updatePublisher.onNext(control)
        }
    }

}