package com.elex.bigdata.web;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
* @author yanbit
* @date 2015 2:53:31 PM
* 
*
*
*/

public class HBaseApplication extends Application {   
    public Restlet createRoot() {     
        Router router = new Router(getContext());     
        router.attachDefault(HBaseHandlerResource.class);     
        return router;     
    }     
}
