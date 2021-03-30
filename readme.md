支付通道账号

尼日利亚
https://dashboard.paystack.com/#/products
Mcalucky2020@gmail.com￼
Lucky@123


ssh root@106.14.159.75
Sr201424


Public key
pk_test_3f8a5fdb27dbc1f3369324c95f8da0621308c93a

Secret key
sk_test_20530788ad4a34a79328eccfaf8a4daf73f0b707

Callback
http://paymax.lianting.store/callback/

Pay-in 个人卡到公司账户。
Pay-out 个人提现。
免额支付。


始化化交易，打给谁，多少钱。

curl https://api.paystack.co/transaction/initialize
2-H "Authorization: Bearer YOUR_SECRET_KEY"
3-H "Content-Type: application/json"
4-d '{ email: "customer@email.com", amount: "20000" }'
5-X POST


{
"status": true,
"message": "Authorization URL created",
"data": {
"authorization_url": "https://checkout.paystack.com/s79qrjh0rtadhlf",
"access_code": "s79qrjh0rtadhlf",
"reference": "yviqp2ha4k"
}
}

{
"status": true,
"message": "Authorization URL created",
"data": {
"authorization_url": "https://checkout.paystack.com/51z2bz5lchk581r",
"access_code": "51z2bz5lchk581r",
"reference": "i4c28s854t"
}
}


用户支付完成了，返回了

http://paymax.lianting.store/callback/?trxref=yviqp2ha4k&reference=yviqp2ha4k

服务器去验证一下对不对。

curl https://api.paystack.co/transaction/verify/:reference
2-H "Authorization: Bearer YOUR_SECRET_KEY"
3-X GET




墨西哥
https://docs.rapyd.net/build-with-rapyd
mergingfintech@gmail.com
China@123









### 蓝鹊广告投放功能需求列表

```
yaque.mobi
```

+ 广告主
    + 入驻基本信息
    + 投放广告范围
        + 车的位置
            + 是否开启投放
            + 提交物料图片
            + 提交二维码地址
                + 二维码扫码统计
        + 投放开始/结束时间
        + 最多投放车辆
        + 车辆自动续费
    + 查看投放车辆
        + 正在寄送物料
        + 投放进行中
            + 第一半月审查上传
            + 第二半月审查上传
        + 投放结束
        + 联系司机
    + 充值扣费
    + 通知管理
        + 有司机接单
        + 司机已寄送物料
        + 司机已贴审查
        + 司机半月审查
        + 司机投放完成
    + 投递反馈
+ 司机端
    + 邀请有奖励
        + 直推奖励
        + 间推奖励
        + 最多奖励时长 1 年
    + 入驻基本信息
    + 接收广告投放
    + 物料寄送地址
        + 首次寄
        + 补寄
    + 上传审查实景照片
    + 收益列表
        + 直推收益
        + 间推收益
        + 广告收益
    + 收益提现
    + 通送管理
        + 接单成功
        + 寄送物料
        + 半月待审查
        + 广告投放完成
    + 投诉反馈
+ 管理平台
    + 广告规格管理
        + 广告可选位置
        + 广告尺寸
        + 投放城市
        + 价格
            + 价格活动期
    + 寄送管理
        + 司机地址
        + 寄送状态
        + 物流号
    + 入驻管理
        + 广告主
        + 司机
    + 投放管理
        + 按广告主
    + 收益管理
        + 广告主
        + 司机
        + 平台
    + 审查管理
        + 未上传审查
        + 按司机
    + 通知管理
        + 广告主入驻
        + 广告主发布投放
        + 广告主投诉反馈
        + 有司机入驻
        + 有司机接单
        + 司机物料已收到
        + 司机物料已贴上传审查
        + 司机投放完成
        + 司机需要提现
        + 司机投诉反馈
