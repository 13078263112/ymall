package com.ywc.ymall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 获取OSS上传授权返回结果
 * @author 嘟嘟~
 * @date 2020/5/2 11:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssPolicyResult {
    private String accessKeyId;
    private String policy;
    private String signature;
    private String dir;
    private String host;


}
