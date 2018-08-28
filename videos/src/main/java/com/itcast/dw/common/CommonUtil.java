package com.itcast.dw.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static String getSessionKey() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public static String getDataFormat(Date date, String formatString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(date);
	}

	public static byte[] inputStreamToByteArray(InputStream in) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024 * 4];
			int n = 0;
			while ((n = in.read(buffer)) > 0) {
				out.write(buffer, 0, n);
			}
			return out.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
