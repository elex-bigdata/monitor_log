package web;

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
    public static void main(String[] args) throws Exception {    
        // Create a new Component.    
        Component component = new Component();    
        
        // Add a new HTTP server listening on port 8182.    
        component.getServers().add(Protocol.HTTP, 8182);    
        
        // Attach the sample application.    
        component.getDefaultHost().attach("/monitor/hbase/log",    
                new HBaseApplication());    
        
        // Start the component.    
        component.start();    
    }            
}
