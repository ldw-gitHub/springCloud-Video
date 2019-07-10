package com.itcast.dw.test.redis;

//@Service
public class RedisService {/*
	
	@Autowired
	RedisUtils rediss;
	
	public String redisTranction(String key,String clientName){
		String clientList = "clientList";
		
		SessionCallback<String> sessionCallback = new SessionCallback<String>() {
			@Override
			public String execute(RedisOperations redis) throws DataAccessException {
				try {
					redis.watch(key);
					int prdNum = Integer.parseInt((String)redis.opsForValue().get(key)); // 当前商品个数
					if (prdNum > 0) {
						redis.multi();
						redis.opsForValue().set(key, String.valueOf(prdNum - 1));
						List result = redis.exec();
						if (result == null || result.isEmpty()) {
							System.out.println("悲剧了，顾客:没有抢到商品");
						} else {
							System.out.println("result ===== " + result.get(0));
							redis.opsForSet().add(clientList, clientName);// 抢到商品记录一下
							System.out.println("好高兴，顾客:抢到商品");
						}
					} else {
						System.out.println("悲剧了，库存为0，顾客:没有抢到商品");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					redis.unwatch();
				}
				return "1";
			}
		};
		
		rediss.execute(sessionCallback);
		return null;
	}

*/}
