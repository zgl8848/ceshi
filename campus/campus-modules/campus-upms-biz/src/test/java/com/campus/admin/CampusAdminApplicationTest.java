package com.campus.admin;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.apache.commons.io.IOUtils;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author campus
 * @date 2018/10/7
 * <p>
 * 加解密单元测试
 */
public class CampusAdminApplicationTest {

	@Test
	public void testJasypt() {
		// 对应application-dev.yml 中配置的根密码
		System.setProperty("jasypt.encryptor.password", "pigx");
		StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());

		//加密方法
		System.out.println(stringEncryptor.encrypt("pigx"));

		//解密方法
		System.out.println(stringEncryptor.decrypt("ltJPpR50wT0oIY9kfOe1Iw=="));
	}

	@Test
	public void testList() throws IOException {
		/*for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= i; j++) {
				System.out.print(i+" * "+j+" = " + i*j + '\t');
			}
			System.out.println();
		}*/

		/*FileReader fr = null;
		try {
			fr = new FileReader("C:\\Users\\admin\\Desktop\\girl.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		StringBuilder strb = new StringBuilder();
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			strb.append(line);
		}
		String result = strb.toString();
		int count = 0;
		int index = 0;
		while (true) {
			index = result.indexOf("@", index + 1);
			System.out.println(index);
			if (index > 0) {
				count++;
			} else {
				break;
			}
		}
		br.close();
		System.out.println(count);*/

		/*HashMap hashMap = new HashMap(16);
		String a = "dss";
		System.out.println(a.length());
		int[] datas = {5,3,4,1,2};

		for (int i = 0; i < datas.length; i++) {
			for (int j = i; j < datas.length; j++) {
				if (datas[j] > datas[i]) {
					int temp = datas[j];
					datas[j] = datas[i];
					datas[i] = temp;
				}
			}
		}
		for (int i : datas) {
			System.out.print(i);
		}*/

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aaa = sdf.format(new Date());
		System.out.println(aaa);


		Long b = 1l;
		Integer c = 1;
		System.out.println(b.equals(c));

	}
}
