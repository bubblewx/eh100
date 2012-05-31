package com.eh100.compass.common;

import org.compass.gps.CompassGps;

public class CompassIndexBuilder {
	 private CompassGps compassGps;

	 
	 
	 void buildCompassIndex() {

		try {

			System.out.println("begin compass index...");
			long beginTime = System.currentTimeMillis();
			// 重建索引.
			// 如果compass实体中定义的索引文件已存在，索引过程中会建立临时索引，
			// 索引完成后再进行覆盖.
			compassGps.index();
			long costTime = System.currentTimeMillis() - beginTime;
			System.out.println("compss index finished.");
			System.out.println("costed " + costTime + " milliseconds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	public CompassGps getCompassGps() {
		return compassGps;
	}

	public void setCompassGps(CompassGps compassGps) {
		this.compassGps = compassGps;
	}  

}
