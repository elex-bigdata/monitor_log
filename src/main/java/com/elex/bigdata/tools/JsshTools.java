package com.elex.bigdata.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author yanbit
 * @date 2015 3:28:55 PM
 * 
 *       java ssh tools exec cmd
 *
 */

public class JsshTools {

	private static final Log LOG = LogFactory.getLog(JsshTools.class);

	
	public static final int DEFAULT_SSH_PORT = 22;
	private Vector<String> stdout = new Vector<String>();
	
	// exec cmd default failure
	public static int returnCode = -1;

  public JsshTools() {
  }

  /**
   * @param command 
   * @param username
   * @param password
   * @param ipAddress 
   * @return 0 successful , other failure
   */
  public int execute(String command,String username,String password,String ipAddress) {
    JSch jsch = new JSch();
    MonitorUserInfo userInfo = new MonitorUserInfo();
    Session session = null;
    Channel channel = null;
    try {
      //LOG.info("start ssh create connection");
      session = jsch.getSession(username, ipAddress, DEFAULT_SSH_PORT);
      session.setPassword(password);
      session.setUserInfo(userInfo);
      session.connect();
      
      channel = session.openChannel("exec");
      ((ChannelExec) channel).setCommand(command);

      channel.setInputStream(null);
      BufferedReader input = new BufferedReader(new InputStreamReader(channel.getInputStream()));

      channel.connect();
      //LOG.info("ssh connect complete");
      //LOG.info("The remote exec command is: " + command);
      // command output
      stdout.clear();
      String line;
      while ((line = input.readLine()) != null) {
        stdout.add(line);
      }
      input.close();
//      if(returnCode == 0 ){
//        LOG.info("The remote exec command is successful");  
//      }else{
//        LOG.error("The remote exec command is failure");
//      }
    } catch (JSchException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (channel != null) {
        channel.disconnect();
      }
      returnCode =channel.getExitStatus();
      if (session != null) {
        session.disconnect();
      }
    }
    return returnCode;
  }

  /**
   * @return cmd StandardOutput
   */
  public Vector<String> getStandardOutput() {
    return stdout;
  }
}
