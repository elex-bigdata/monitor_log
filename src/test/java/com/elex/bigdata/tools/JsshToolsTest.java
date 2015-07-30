package com.elex.bigdata.tools;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

/**
 * @author yanbit
 * @date 2015 4:07:42 PM
 * 
 *
 *
 */

public class JsshToolsTest {

	@Test
	public void test() {

		JsshTools ssh = new JsshTools();
		// ssh.execute("uname -s -r -v");
		// ssh.execute("cat
		// /home/yanbit/Desktop/tools/nginx-1.8.0/README");
		ssh.execute("ls -al /home/yanbit/", "yanbit", "hadoop","192.168.159.130");

		Vector<String> stdout = ssh.getStandardOutput();
		if(ssh.returnCode==0){
			System.out.println("-----------------");
			for (String str : stdout) {
				System.out.println(str);
			}	
		}
		
	}

}
