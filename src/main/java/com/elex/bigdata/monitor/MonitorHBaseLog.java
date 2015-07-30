package com.elex.bigdata.monitor;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elex.bigdata.tools.JsshTools;
import com.elex.bigdata.tools.Utils;

/**
 * @author yanbit
 * @date 2015 2:44:26 PM
 * 
 * monitor hbase log
 * 
 */

public class MonitorHBaseLog {

	private static final Log LOG = LogFactory.getLog(MonitorHBaseLog.class);

	public static void main(String[] args) {
		MonitorHBaseLog hbasehost = new MonitorHBaseLog();
		LOG.info("start monitor hbase task");
		long start = System.currentTimeMillis();
		hbasehost.execCmd();
		long lost = System.currentTimeMillis() - start;
		LOG.info("finish monitor hbase task");
		LOG.info("monitor hbase task running: "+lost+"");
	}

	public void execCmd() {
		String username = Utils.getString("host.username");
		String passwd = Utils.getString("host.passwd");
		String cmd = Utils.getString("exec.hbase.cmd");
		List<Object> hosts = Utils.getList("monitor.servers.ip");

		JsshTools ssh = new JsshTools();
		for (Object obj : hosts) {
			String ipAddress = (String) obj;
			//TODO result
			//TODO logs log ip cmd re
			ssh.execute(cmd, username, passwd, ipAddress);
		}
	}
}
