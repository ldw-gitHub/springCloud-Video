package com.itcast.dw.test.redis;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class RedisLock {/*

	@Autowired
	RedisUtils redis;
	@Autowired
	RedisService redisService;
	
	@Test
	public void tests(){
		String key = "number";
	}
	
	*//**
	 * 
	 * redis实现商品抢购
	 * @throws InterruptedException 
	 * @date 2019年6月20日
	 * @author liudawei
	 *//*
	@Test
	public void doBuySome() throws InterruptedException {
		long starTime = System.currentTimeMillis();
		initPrduct();
		initClient();
		printResult();
		long endTime = System.currentTimeMillis();
		long Time = endTime - starTime;
		System.out.println("程序运行时间： " + Time + "ms");
	}

	public void printResult() {
		Set<String> set = redis.smembers("clientList");
		int i = 1;
		for (String value : set) {
			System.out.println("第" + i++ + "个抢到商品，" + value + " ");
		}
	}

	public void initClient() {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		int clientNum = 100;// 模拟客户数目
		for (int i = 0; i < clientNum; i++) {
			cachedThreadPool.execute(new ClientThread(i,redis,redisService));
		}
		cachedThreadPool.shutdown();
		while (true) {
			if (cachedThreadPool.isTerminated()) {
				System.out.println("所有的线程都结束了！");
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void initPrduct() {
		int prdNum = 10;// 商品个数
		String key = "prdNum";
		String clientList = "clientList";// 抢购到商品的顾客列表
		if (redis.exists(key)) {
			redis.remove(key);
		}
		if (redis.exists(clientList)) {
			redis.remove(clientList);
		}
		redis.set(key, String.valueOf(prdNum));// 初始化RedisUtil.returnResource(jedis);}}
	}

*/}

/*class ClientThread implements Runnable {
	
	public ClientThread(){}
	String key = "prdNum";// 商品主键
	String clientList = "clientList";//// 抢购到商品的顾客列表主键
	String clientName;
	RedisUtils redis;
	RedisService redisService;

	public ClientThread(int num,RedisUtils redis,RedisService redisService) {
		clientName = "编号=" + num;
		this.redis = redis;
		this.redisService = redisService;
	}

	public void run() {
		try {
			Thread.sleep((int) (Math.random() * 5000));// 随机睡眠一下
		} catch (InterruptedException e1) {
		}
		redisService.redisTranction(key, clientName);
		while (true) {
			System.out.println("顾客:" + clientName + "开始抢商品");
			try {
				redis.watch(key);
				int prdNum = Integer.parseInt(redis.get(key)); // 当前商品个数
				if (prdNum > 0) {
					redis.multi();
					redis.set(key, String.valueOf(prdNum - 1));
					List result = redis.exec();
					if (result == null || result.isEmpty()) {
						System.out.println("悲剧了，顾客:" + clientName + "没有抢到商品");// 可能是watch-key被外部修改，或者是数据操作被驳回
					} else {
						System.out.println("result ===== " + result.get(0));
						redis.sadd(clientList, clientName);// 抢到商品记录一下
						System.out.println("好高兴，顾客:" + clientName + "抢到商品");
						break;
					}
				} else {
					System.out.println("悲剧了，库存为0，顾客:" + clientName + "没有抢到商品");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				redis.unwatch();
			}
		}
	}
}
*/