package com.elex.bigdata.web;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.elex.bigdata.monitor.MonitorHBaseLog;

/**
 * @author yanbit
 *
 */
public class HBaseResetHandlerResource extends ServerResource {
	@Get  
    public boolean handler() { 
        return MonitorHBaseLog.execResetCmd();  
    } 
}
