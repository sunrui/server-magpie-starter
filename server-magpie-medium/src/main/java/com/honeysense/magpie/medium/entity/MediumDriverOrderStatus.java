package com.honeysense.magpie.medium.entity;

public enum MediumDriverOrderStatus {
    /**
     * 提交审请中
     */
    APPLYING,
    /**
     * 审核被拒绝
     */
    APPLY_REFUSE,
    /**
     * 寄送物料中
     */
    MATERIAL_SEND,
    /**
     * 认证第一次上传
     */
    CERTIFY_FIRST_UPLOAD,
    /**
     * 认证第一次上传认证
     */
    CERTIFY_FIRST_VERIFY,
    /**
     * 认证第一次上传失败
     */
    CERTIFY_FIRST_FAILED,
    /**
     * 认证第二次上传
     */
    CERTIFY_SECOND_UPLOAD,
    /**
     * 认证第二次上传认证
     */
    CERTIFY_SECOND_VERIFY,
    /**
     * 认证第二次上传失败
     */
    CERTIFY_SECOND_FAILED,
    /**
     * 认证第三次上传
     */
    CERTIFY_THIRD_UPLOAD,
    /**
     * 认证第三次上传认证
     */
    CERTIFY_THIRD_VERIFY,
    /**
     * 认证第三次上传失败
     */
    CERTIFY_THIRD_FAILED,
    /**
     * 订单被取消
     */
    CANCEL,
    /**
     * 订单完成
     */
    FINISH
}
