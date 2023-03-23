package com.igfasouza.components;

import java.io.IOException;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FirmataComponent {

    private IODevice device;
    private Boolean ledStateArduino = false;

    /**
     * This method is called during Spring's startup.
     *
     * @param event Event raised when an ApplicationContext gets initialized or
     * refreshed.
     */
    @EventListener
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        try {
            // You can check your USB port name on the Arduino IDE, on the OS you are using
            device = new FirmataDevice("/dev/ttyACM0");
            device.start();
            device.ensureInitializationIsDone();
        }catch(InterruptedException e){
            e.fillInStackTrace();
        }catch(IOException e){
            e.fillInStackTrace();
        }
        return;
    }

    public void led(){
        try{
            // Inverts boolean to switch the LED on and off, every time the REST API is called.
            ledStateArduino = !ledStateArduino;
            Pin pin = device.getPin(13);
            pin.setMode(Pin.Mode.OUTPUT);
            if(ledStateArduino == true){
                pin.setValue(1);
            }else{
                pin.setValue(0);
            }
        }catch(IOException e){
            e.fillInStackTrace();
        }
    }
    
}