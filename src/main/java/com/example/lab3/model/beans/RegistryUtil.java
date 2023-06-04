package com.example.lab3.model.beans;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class RegistryUtil {
    public static ObjectName getObjectName(Object bean, String name) throws MalformedObjectNameException {
        String packageName = bean.getClass().getPackage().getName();
        String type = bean.getClass().getSimpleName();
        return new ObjectName(packageName + ":type=" + type + ",name=" + name);
    }
    public static void register(Object bean, String name) {
        try {
            ManagementFactory.getPlatformMBeanServer().registerMBean(bean, RegistryUtil.getObjectName(bean, name));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void unregister(Object bean, String name) {
        try {
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(RegistryUtil.getObjectName(bean, name));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
