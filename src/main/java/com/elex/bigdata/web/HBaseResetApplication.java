package com.elex.bigdata.web;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class HBaseResetApplication extends Application {   
    public Restlet createRoot() {     
        Router router = new Router(getContext());     
        router.attachDefault(HBaseResetHandlerResource.class);     
        return router;     
    }     
}
