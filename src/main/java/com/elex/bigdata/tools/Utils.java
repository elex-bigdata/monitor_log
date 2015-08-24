package com.elex.bigdata.tools;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author yanbit
 * @date 2015 2:59:49 PM
 * 
 */

public class Utils {
  private static final Log LOG = LogFactory.getLog(Utils.class);

  private Configuration config;

  public Utils() {
  }

  {
    try {
      config = new PropertiesConfiguration("server.properties");
    } catch (ConfigurationException e) {
      LOG.error("Load config: server.properties Failure");
      e.printStackTrace();
    }
  }

  public List<Object> getList(String key) {
    return config.getList(key);
  }

  public String getString(String key) {
    return config.getString(key);
  }

  public int getInt(String key) {
    return config.getInt(key);
  }
}
