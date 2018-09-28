var util = require('util.js');
var api = require('../config/api.js')
function wxpay(orderNo, redirectUrl) {
  util.requestGet({
    url: api.OrderPrePay,
    data:{
      orderNo: orderNo
    },
    success: function (res) {
        console.log("订单提交的结果",res)
      if (res.code == 0) {
        //发起支付
        wx.requestPayment({
          timeStamp: res.data.timeStamp,
          nonceStr: res.data.nonceStr,
          package: res.data.packageValue,
          signType: res.data.signType,
          paySign: res.data.paySign,
          fail: function (res) {
            console.log(res);
            util.showWarn('支付失败:');
          },
          success: function () {
            var message = '支付成功';
            var callback = function () {
              wx.reLaunch({
                url: redirectUrl
              });
            }
            util.requestGet({
              url: api.SuccessPay,
              data:{
                orderNo: orderNo
              },
              success:function(){
                console.log("支付成功")
              }
            })
            util.showWarn(message, callback);
          }
        })
      } else {
        util.showWarn(res.msg);
      }
    }
  })
}

module.exports = {
  wxpay: wxpay
}
