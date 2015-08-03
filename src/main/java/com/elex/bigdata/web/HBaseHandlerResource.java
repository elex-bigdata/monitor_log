package com.elex.bigdata.web;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.elex.bigdata.monitor.MonitorHBaseLog;

/**
* @author yanbit
* @date 2015 2:42:45 PM
* 
*
*
*/

public class HBaseHandlerResource extends ServerResource{
	
	@Get  
    public String handler() { 
        return MonitorHBaseLog.execCmd();  
    } 
}
