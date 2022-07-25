package com.wayne.micrometer.demo.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class TaskAgent {

    public static void registerMBean() throws Exception{
        // 获取MBeanServer对象
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName taskName = new ObjectName("jmxBean:name=task");
        server.registerMBean(new Task("123456", 27), taskName); // 注册MBean
        System.out.println(" Task monitor....");
    }
}
