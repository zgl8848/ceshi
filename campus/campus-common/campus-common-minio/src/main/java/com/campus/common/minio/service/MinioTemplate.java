package com.campus.common.minio.service;

import com.campus.common.minio.vo.MinioItem;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * minio 交互类
 *
 * @author campus
 */
@NoArgsConstructor
@AllArgsConstructor
public class MinioTemplate {

	private String endpoint;
	private String accessKey;
	private String secretKey;


	/**
	 * 创建bucket
	 *
	 * @param bucketName bucket名称
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#makeBucket
	 */
	public void createBucket(String bucketName) throws Exception {
		MinioClient client = getMinioClient();
		if (!client.bucketExists(bucketName)) {
			client.makeBucket(bucketName);
		}
	}

	/**
	 * 获取全部bucket
	 * <p>
	 * https://docs.minio.io/cn/java-client-api-reference.html#listBuckets
	 */
	public List<Bucket> getAllBuckets() throws Exception {
		return getMinioClient().listBuckets();
	}

	/**
	 * @param bucketName bucket名称
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#listBuckets
	 */
	public Optional<Bucket> getBucket(String bucketName) throws Exception {
		return getMinioClient().listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
	}

	/**
	 * @param bucketName bucket名称
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeBucket
	 */
	public void removeBucket(String bucketName) throws Exception {
		getMinioClient().removeBucket(bucketName);
	}

	/**
	 * 根据文件前置查询文件
	 *
	 * @param bucketName bucket名称
	 * @param prefix     前缀
	 * @param recursive  是否递归查询
	 * @return
	 * @throws Exception
	 */
	public List<MinioItem> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) throws Exception {
		List objectList = new ArrayList();
		Iterable<Result<Item>> objectsIterator = getMinioClient()
				.listObjects(bucketName, prefix, recursive);

		while (objectsIterator.iterator().hasNext()) {
			objectList.add(objectsIterator.iterator().next().get());
		}
		return objectList;
	}

	/**
	 * 获取文件外链
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param expires    过期时间 <=7
	 * @return url
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#getObject
	 */
	public String getObjectURL(String bucketName, String objectName, Integer expires) throws Exception {
		return getMinioClient().presignedGetObject(bucketName, objectName, expires);
	}

	/**
	 * 获取文件
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @return 二进制流
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#getObject
	 */
	public InputStream getObject(String bucketName, String objectName) throws Exception {
		return getMinioClient().getObject(bucketName, objectName);
	}

	/**
	 * 上传文件
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param stream     文件流
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
	 */
	public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
		getMinioClient().putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
	}

	/**
	 * 上传文件
	 *
	 * @param bucketName  bucket名称
	 * @param objectName  文件名称
	 * @param stream      文件流
	 * @param size        大小
	 * @param contextType 类型
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
	 */
	public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
		getMinioClient().putObject(bucketName, objectName, stream, size, contextType);
	}

	/**
	 * 获取文件信息
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
	 */
	public ObjectStat getObjectInfo(String bucketName, String objectName) throws Exception {
		return getMinioClient().statObject(bucketName, objectName);
	}

	/**
	 * 删除文件
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
	 */
	public void removeObject(String bucketName, String objectName) throws Exception {
		getMinioClient().removeObject(bucketName, objectName);
	}


	/**
	 * 获取minio 原生客户端
	 *
	 * @return
	 * @throws Exception
	 */
	public MinioClient getMinioClient() throws Exception {
		return new MinioClient(endpoint, accessKey, secretKey);
	}
}
