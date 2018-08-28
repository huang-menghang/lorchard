package com.ysdevelop.lochard.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.common.utils
 * 
 * @Description 序列化工具类,将数据存入redis,需要将键值序列化,还有其它操作
 * 
 * @Date 2018年4月20日
 * 
 * @Version
 * 
 */
@SuppressWarnings("unchecked")
public class SerializeUtil {
	static final Class<?> CLAZZ = SerializeUtil.class;

	private static Logger logger = Logger.getLogger(SerializeUtil.class);

	/**
	 * 实现序列化
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] serialize(Object value) {

		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(value);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("serialize error " + JSONHelper.bean2json(value));
		} finally {
			close(bos);
			close(os);
		}
		return rv;
	}

	public static Object deserialize(byte[] in) {
		return deserialize(in, Object.class);
	}

	public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
		Object rv = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				rv = is.readObject();
			}

		} catch (Exception e) {
			logger.info("deserialize error");
		} finally {
			close(is);
			close(bis);
		}

		return (T) rv;
	}

	private static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				logger.info("close stream error");
				;
			}
		}
	}

}
