package com.example.tadeu17.dronetrack;

import android.content.ContextWrapper;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.net.*;

public class WakeOnLan {

    private static byte[] getMacBytes(String mac) throws IllegalArgumentException {
        Log.d("GetMacBytes", "method started");

        byte[] bytes = new byte[6];
        try {
            String hex;
            for (int i = 0; i < 6; i++) {
                hex = mac.substring(i*2, i*2+2);
                bytes[i] = (byte) Integer.parseInt(hex, 16);
                Log.d("GetMacbytes", "calculated");
                Log.d("GetMacBytes (bytes)", new String(bytes));
            }
        }
        catch (NumberFormatException e) {
            Log.e("GetMacBytes","error");
        }
        return bytes;
    }

    public static void wakeup(String broadcastIP, String mac) {
        Log.d("wakeup", "method started");
        if (mac == null) {
            Log.d("Mac error at wakeup", "mac = null");
            return;
        }

        try {
            byte[] macBytes = getMacBytes(mac);
            Log.d("wakeup (bytes)", new String(macBytes));
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }

            Log.d("wakeup", "calculating completed, sending...");
            InetAddress address = InetAddress.getByName(broadcastIP);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 9);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
            Log.d("wakeup", "Magic Packet sent");



        }
        catch (Exception e) {
            Log.e("wakeup", "error");
        }

    }
}
