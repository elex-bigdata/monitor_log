package com.elex.bigdata.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elex.bigdata.monitor.MonitorUserInfo;
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

	// user info
	private String ipAddress;
	private String username;
	private String password;
	public static final int DEFAULT_SSH_PORT = 22;
	private Vector<String> stdout;
	
	// exec cmd default failure
	public static int returnCode = -1;

	public JsshTools(String ipAddress,String username,String password) {
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		stdout = new Vector<String>();
	}

	public int execute(final String command) {
		JSch jsch = new JSch();
		MonitorUserInfo userInfo = new MonitorUserInfo();
		Session session = null;
		Channel channel = null;
		try {
			LOG.info("start create session");
			session = jsch.getSession(username, ipAddress, DEFAULT_SSH_PORT);
			session.setPassword(password);

			session.setUserInfo(userInfo);
			session.connect();
			LOG.info("session connect complete");
			LOG.info("start create channel");
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			channel.setInputStream(null);
			BufferedReader input = new BufferedReader(new InputStreamReader(channel.getInputStream()));

			channel.connect();
			LOG.info("channel connect complete");
			LOG.info("The remote exec command is: " + command);
			// command output
			String line;
			while ((line = input.readLine()) != null) {
				stdout.add(line);
			}
			input.close();


		} catch (JSchException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (channel != null) {
				channel.disconnect();
				returnCode =channel.getExitStatus();
				if(returnCode == 0 ){
					LOG.info("The remote exec command is successful");	
				}else{
					LOG.error("The remote exec command is failure");
				}
			}
			if (session != null) {
				session.disconnect();
			}
		}
		return returnCode;
	}

	public Vector<String> getStandardOutput() {
		return stdout;
	}
}
