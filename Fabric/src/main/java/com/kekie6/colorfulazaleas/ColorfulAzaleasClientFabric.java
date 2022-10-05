package com.kekie6.colorfulazaleas;

import net.fabricmc.api.ClientModInitializer;

public class ColorfulAzaleasClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorfulAzaleasClient.init();
    }
}