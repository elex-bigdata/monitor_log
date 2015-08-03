package com.elex.bigdata.tools;

import org.junit.Test;

/**
* @author yanbit
* @date 2015 3:19:05 PM
* 
* test utils
* 
*/

public class UtilsTest {

	@Test
	public void testGetList() {
		System.out.println(Utils.getList("monitor.servers.ip").toString());
	}

	@Test
	public void testGetString() {
		System.out.println(Utils.getString("exec.cmd"));
	}

	@Test
	public void testGetInt() {
		System.out.println(Utils.getInt("exec.int"));
	}

}
