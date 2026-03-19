package com.mdtg.robot.common.toolkit;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author WangYunwei [2026-03-19]
 */
public class CustomUtils {

    public static void main(String[] args) {

        CustomUtils utils = new CustomUtils();
        try {
            utils.getInetAddresses();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取网络地址
     */
    public static String getInetAddresses() throws SocketException {

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                // 过滤掉回环地址 (127.0.0.1) 和 IPv6 地址，只取 IPv4 非回环地址
                if (!addr.isLoopbackAddress() && !addr.isLinkLocalAddress() && addr instanceof Inet4Address) {
                    if ("Linux".equals(System.getProperty("os.name")) && "eth0".equals(ni.getName())) {
                        return addr.getHostAddress();
                    }else if ("Windows".equals(System.getProperty("os.name")) && "ethernet_32772".equals(ni.getName()) ) {
                        return addr.getHostAddress();
                    }
//                    System.out.println(System.getProperty("os.name"));
//                    System.out.println("Interface: " + ni.getName());
//                    System.out.println("IP Address: " + addr.getHostAddress());
                }
            }
        }
        return "127.0.0.1";
    }
}
