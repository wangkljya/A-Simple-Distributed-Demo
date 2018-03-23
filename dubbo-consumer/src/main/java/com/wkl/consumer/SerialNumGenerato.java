package com.wkl.consumer;

import org.junit.Test;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class SerialNumGenerato {
	private long workerId;
	private long datacenterId;   //workerId + datacenterId 构成工作机器id
    private long sequence;		//在同一毫秒下递增
    
    private long twepoch = 1288834974657L;
    @SuppressWarnings("unused")
	private long workerIdBits = 5L;
    @SuppressWarnings("unused")
	private long datacenterIdBits = 5L;
    private long sequenceBits = 12L;
    private long lastTimestamp = -1L;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    
//	public Order(long workerId, long datacenterId, long sequence) {
//		this.workerId = workerId;
//		this.datacenterId = datacenterId;
//		this.sequence = sequence;
//	}
	
	public long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}

	public long getDatacenterId() {
		return datacenterId;
	}

	public void setDatacenterId(long datacenterId) {
		this.datacenterId = datacenterId;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	//同步方法，防止同一线程下获取到相同的id
	public synchronized long getOrderId(){
		long timestamp = System.currentTimeMillis();  //返回当前时间的毫秒
		
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if(sequence == 0) {  //同一毫秒下4096个序列号已经用完
				timestamp = getNextMillis(timestamp);
			}
		} else {
			sequence = 0;
		}
		lastTimestamp = timestamp;
		return ((timestamp - twepoch) << 22) |
		        (datacenterId << 17) |
		        (workerId << 12) |
		        sequence;    //   64bit大小的二进制 大致为   41位时间戳   10位机器吗  12位机器序号
	}
	
	public long getNextMillis(long lastTime){
		long timestamp = System.currentTimeMillis();
		while(lastTime <= timestamp){
			timestamp = System.currentTimeMillis();
		}
		return timestamp;
	}
}
