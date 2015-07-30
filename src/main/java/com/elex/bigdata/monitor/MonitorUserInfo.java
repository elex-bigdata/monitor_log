package com.elex.bigdata.monitor;

import com.jcraft.jsch.UserInfo;

/**
* @author yanbit
* @date 2015 3:59:37 PM
* 
*	answer yes
*
*/

public class MonitorUserInfo implements UserInfo {  
    private String password;  
  
    private String passphrase;  
  
    public String getPassphrase() {  
        return null;  
    }  
  
    public String getPassword() {  
        return null;  
    }  
  
    public boolean promptPassphrase(final String arg0) {  
        return false;  
    }  
  
    public boolean promptPassword(final String arg0) {  
        return false;  
    }  
  
    public boolean promptYesNo(final String arg0) {  
        if (arg0.contains("The authenticity of host")) {  
            return true;  
        }  
        return false;  
    }  
  
    public void showMessage(final String arg0) {  
    }  
} 
