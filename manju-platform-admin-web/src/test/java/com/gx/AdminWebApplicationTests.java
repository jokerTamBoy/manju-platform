package com.manjushira;

import com.manjushira.kits.shiro.ShiroKit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminWebApplicationTests {

	@Test
	public void testMd5() {
		//盐（用户名+随机数）
		String username = "sd_admin";
		String salt = ShiroKit.getRandomSalt(16);
		//原密码
		String password = "12345678";
		String encodedPassword = ShiroKit.md5(password, username + salt);
		System.out.println("这个是保存进数据库的密码:" + encodedPassword);
		System.out.println("这个是保存进数据库的盐:" + salt);
	}

	@Test
	public void test1() throws Exception {
		WatchService watchService
				= FileSystems.getDefault().newWatchService();

		Path path = Paths.get(System.getProperty("user.home"));

		path.register(
				watchService,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println(
						"Event kind:" + event.kind()
								+ ". File affected: " + event.context() + ".");
			}
			key.reset();
		}
	}
}
