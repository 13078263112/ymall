package com.ywc.ymall.controller.oss.compent;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.ywc.ymall.vo.OssPolicyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author 嘟嘟~
 * @date 2020/5/2 11:44
 */

@Service
public class OssCompent {
    @Value("${aliyum.oss.policy.expire}")
    private int ALIYUN_OSS_EXPIRE;
    @Value("${aliyum.oss.maxSize}")
    private int ALIYUN_OSS_MAX_SIZE;
    @Value("${aliyum.oss.bucketName}")
    private String ALIYUN_OSS_BUCKET_NAME;
    @Value("${aliyum.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyum.oss.dir.prefix}")
    private String ALIYUN_OSS_DIR_PREFIX;
    @Value("${aliyum.oss.accessKeyId}")
    private String ALIYUN_OSS_KEY;
    @Value("${aliyum.oss.accessKeySecret}")
    private String ALIYUN_OSS_SECRET_KEY;
    @Autowired
    private OSSClient ossClient;

    /**
     * 签名生成
     */
    public OssPolicyResult policy() {
        OssPolicyResult result = new OssPolicyResult();
        // 存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dir = ALIYUN_OSS_DIR_PREFIX + sdf.format(new Date());
        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_EXPIRE * 1000;
        Date expiration = new Date(expireEndTime);
        // 文件大小
        long maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
        // 提交节点
        String action = "http://" + ALIYUN_OSS_BUCKET_NAME + "." + ALIYUN_OSS_ENDPOINT;
        try {
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            // 返回结果
            result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setHost(action);
        } catch (Exception e) {
        }
        return result;
    }
    @Bean
    public OSSClient ossClient(){
        return  new OSSClient(ALIYUN_OSS_ENDPOINT,ALIYUN_OSS_KEY,ALIYUN_OSS_SECRET_KEY);
    }
}
