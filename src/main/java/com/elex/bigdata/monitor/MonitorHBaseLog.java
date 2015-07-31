package com.elex.bigdata.monitor;

import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elex.bigdata.tools.JsshTools;
import com.elex.bigdata.tools.Utils;

/**
 * @author yanbit
 * @date 2015 2:44:26 PM
 * 
 *       monitor hbase log
 * 
 */

public class MonitorHBaseLog {

	private static final Log LOG = LogFactory.getLog(MonitorHBaseLog.class);

	public static void main(String[] args) {
		LOG.info("start monitor hbase task");
		long start = System.currentTimeMillis();
		execCmd();
		long lost = System.currentTimeMillis() - start;
		LOG.info("finish monitor hbase task");
		LOG.info("monitor hbase task running: " + lost + "");
	}

	public static String execCmd() {
		String username = Utils.getString("host.username");
		String passwd = Utils.getString("host.passwd");
		String cmd = Utils.getString("hbase.exec.cmd");
		List<Object> hosts = Utils.getList("hbase.monitor.servers.ip");

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(passwd) || StringUtils.isEmpty(cmd)
				|| hosts.size() == 0) {
			LOG.error("host.username and host.passwd and exec.hbase.cmd and monitor.servers.ip is must be not null");
			return "configisnull";
		}

		for (Object obj : hosts) {
			String ipAddress = (String) obj;
			LOG.info("cmd :"+cmd+" host :"+ipAddress);
			JsshTools ssh = new JsshTools();
			ssh.execute(cmd, username, passwd, ipAddress);
			Vector<String> error = ssh.getStandardOutput();
			for (String err : error) {
				if (!err.startsWith("ok")) {
					System.out.println(error);
					LOG.error("cmd :"+cmd+" host :"+ipAddress+" status :"+"failure");
					return "failure";
				}
			}
			LOG.info("cmd :"+cmd+" host :"+ipAddress+" status :"+"success");
		}
		return "ok";
	}
}
