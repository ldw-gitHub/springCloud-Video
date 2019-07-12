package com.itcast.dw.test.invoke;

import java.util.HashMap;
import java.util.Map;

public class SecKillImpl implements SeckillInterface {

	static Map<Long, Long> inventory;
	static {
		inventory = new HashMap<>();
		inventory.put(10000001L, 50L);
	}

	@Override
	public void secKill(String arg1, Long arg2) {
		// 最简单的秒杀，这里仅作为demo示例
		Long reduceInventory = reduceInventory(arg2);
		if(reduceInventory < 0){
			System.out.println("商品已售空！！！！！！！");
		}
	}

	// 模拟秒杀操作，姑且认为一个秒杀就是将库存减一，实际情景要复杂的多
	public Long reduceInventory(Long commodityId) {
		if(inventory.get(commodityId) <= 0){
			return -1L;
		}else{
			inventory.put(commodityId, inventory.get(commodityId) - 1);
		}
		return inventory.get(commodityId);
	}
}
