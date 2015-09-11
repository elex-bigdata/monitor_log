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

  /**
   * @return ok or other
   */
  public static String execCmd() {
    Utils util = new Utils();
    String username = util.getString("host.username");
    String passwd = util.getString("host.passwd");
    String cmd = util.getString("hbase.exec.cmd");
    List<Object> hosts = util.getList("hbase.monitor.servers.ip");
    LOG.info("start execute " + "cmd :" + cmd + " host :" + hosts);
    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(passwd)
        || StringUtils.isEmpty(cmd) || hosts.size() == 0) {
      LOG.error(
          "host.username and host.passwd and exec.hbase.cmd and monitor.servers.ip is must be not null");
      return "configisnull";
    }
    JsshTools ssh = new JsshTools();
    for (Object obj : hosts) {
      String ipAddress = (String) obj;
      int count = 0;
      int ret = -1;
      while (ret != 0) {
        ret = ssh.execute(cmd, username, passwd, ipAddress);
        count++;
        if (count == 5) {
          return "cmd failure";
        }
        if (ret != 0) {
          LOG.info("cmd exec status failure ipAddress:" + ipAddress
              + " retry count:" + count + " ret:" + ret);
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }

      Vector<String> error = ssh.getStandardOutput();
      if (error.isEmpty()) {
        LOG.info("cmd :" + cmd + " host :" + hosts + " status :" + "success");
        return "ok";
      } else {
        LOG.error(
            "cmd :" + cmd + " host :" + ipAddress + " status :" + "failure");
        return "hbase failure";
      }
      // for (String err : error) {
      // if (!err.startsWith("ok")) {
      // System.out.println(error);
      // LOG.error("cmd :" + cmd + " host :" + ipAddress + " status :" +
      // "failure");
      // return "hbase failure";
      // }
      // }
    }
//    LOG.info("cmd :" + cmd + " host :" + hosts + " status :" + "success");
    return "ok";
  }

  /**
   * @return true reset success ,false reset failure
   */
  public static boolean execResetCmd() {
    Utils util = new Utils();
    String username = util.getString("host.username");
    String passwd = util.getString("host.passwd");
    String cmd = util.getString("hbase.exec.reset.cmd");
    List<Object> hosts = util.getList("hbase.monitor.servers.ip");
    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(passwd)
        || StringUtils.isEmpty(cmd) || hosts.size() == 0) {
      LOG.error(
          "host.username and host.passwd and hbase.exec.reset.cmd and monitor.servers.ip is must be not null");
      return false;
    }

    for (Object obj : hosts) {
      String ipAddress = (String) obj;
      // LOG.info("cmd :" + cmd + " host :" + ipAddress);
      JsshTools ssh = new JsshTools();
      int count = 0;
      int ret = -1;
      while (ret != 0) {
        ret = ssh.execute(cmd, username, passwd, ipAddress);
        count++;
        if (count == 5) {
          return false;
        }
        if (ret != 0) {
          LOG.info("cmd exec status failure retry count:" + count);
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
      LOG.info("cmd :" + cmd + " host :" + ipAddress + " status :" + "success");
    }
    return true;
  }
}
