package com.elex.bigdata.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 * @author yanbit
 * @date 2015 2:55:10 PM
 * 
 *
 *
 */

public class RestMain {
  private static final Log LOG = LogFactory.getLog(RestMain.class);

  public static void main(String[] args) throws Exception {
    // Create a new Component.
    Component component = new Component();

    // Add a new HTTP server listening on port 8282.
    component.getServers().add(Protocol.HTTP, 8282);

    // Attach the sample application.
    component.getDefaultHost().attach("/monitor/hbase/log",
        new HBaseApplication());
    component.getDefaultHost().attach("/monitor/hbase/log/reset",
        new HBaseResetApplication());

    // Start the component.
    component.start();

    LOG.info("starting the http server on port:" + 8282);

  }
}
