var wxpay = require('../../../utils/pay.js')
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp()
Page({
  data: {
    tabs: ["待付款", "待发货", "待收货", "退款中","已取消", "已完成"],
    tabClass: ["", "", "", "", ""],
    stv: {
      windowWidth: 0,
      lineWidth: 0,
      offset: 0,
      tStart: false
    },
    activeTab: 0,
    loadingStatus: false,
  },

  onLoad: function(options) {
    try {
      let {
        tabs
      } = this.data;
      var res = wx.getSystemInfoSync()
      this.windowWidth = res.windowWidth;
      this.data.stv.lineWidth = this.windowWidth / this.data.tabs.length;
      this.data.stv.windowWidth = res.windowWidth;
      this.setData({
        stv: this.data.stv
      })
      this.tabsCount = tabs.length;
    } catch (e) {}
  },

  onShow: function() {
    // 获取订单列表
    this.setData({
      loadingStatus: true
    })
    this.getOrderList()
  },

  getOrderList: function() {
    var that = this;
    var orderList = [];
    util.requestGet({
      url: api.OrderList,
      data: {
        merchantId: app.globalData.merchantId,
        memberId: app.globalData.memberId,
        limit: app.globalData.pageSize,
        page: app.globalData.page
      },
      success: function(res) {
        console.log("订单列表:", res.data)
        if (res.code == 0) {
          for (var i = 0; i < 6; i++) {
            var orderListByStatus = [];
            var isnull = true;
            for (var j = 0; j < res.data.length; j++) {
              var order = res.data[j];
              if (order.orderStatus == i) {
                orderListByStatus.push(order)
                isnull = false
              }
            }
            orderList.push({
              status: i,
              isnull: isnull,
              orderList: orderListByStatus
            })
          }
          that.setData({
            loadingStatus: false,
            orderList: orderList
          })
        }
      }
    })
  },

  orderDetail: function(e) {
    var orderNo = e.currentTarget.dataset.orderno;
    wx.navigateTo({
      url: "/pages/order-details/index?orderNo=" + orderNo
    })
  },

  cancelOrderTap: function(e) {
    var that = this;
    console.log(e.currentTarget.dataset)
    var orderNo = e.currentTarget.dataset.orderno;
    wx.showModal({
      title: '确定要取消该订单吗？',
      content: '',
      success: function(res) {
        if (res.confirm) {
          wx.showLoading();
          util.requestGet({
            url: api.OrderClose,
            data: {
              orderNo: orderNo
            },
            success: (res) => {
              wx.hideLoading();
              if (res.code == 0) {
                that.onShow();
              }
            }
          })
        }
      }
    })
  },

  toPayTap: function(e) {
    var orderNo = e.currentTarget.dataset.orderno;
    wxpay.wxpay(orderNo, "/pages/ucenter/index/index");
  },

  handlerStart(e) {
    console.log('handlerStart')
    let {
      clientX,
      clientY
    } = e.touches[0];
    this.startX = clientX;
    this.tapStartX = clientX;
    this.tapStartY = clientY;
    this.data.stv.tStart = true;
    this.tapStartTime = e.timeStamp;
    this.setData({
      stv: this.data.stv
    })
  },
  handlerMove(e) {
    console.log('handlerMove')
    let {
      clientX,
      clientY
    } = e.touches[0];
    let {
      stv
    } = this.data;
    let offsetX = this.startX - clientX;
    this.startX = clientX;
    stv.offset += offsetX;
    if (stv.offset <= 0) {
      stv.offset = 0;
    } else if (stv.offset >= stv.windowWidth * (this.tabsCount - 1)) {
      stv.offset = stv.windowWidth * (this.tabsCount - 1);
    }
    this.setData({
      stv: stv
    });
  },
  handlerCancel(e) {

  },
  handlerEnd(e) {
    console.log('handlerEnd')
    let {
      clientX,
      clientY
    } = e.changedTouches[0];
    let endTime = e.timeStamp;
    let {
      tabs,
      stv,
      activeTab
    } = this.data;
    let {
      offset,
      windowWidth
    } = stv;
    //快速滑动
    if (endTime - this.tapStartTime <= 300) {
      console.log('快速滑动')
      //判断是否左右滑动(竖直方向滑动小于50)
      if (Math.abs(this.tapStartY - clientY) < 50) {
        //Y距离小于50 所以用户是左右滑动
        console.log('竖直滑动距离小于50')
        if (this.tapStartX - clientX > 5) {
          //向左滑动超过5个单位，activeTab增加
          console.log('向左滑动')
          if (activeTab < this.tabsCount - 1) {
            this.setData({
              activeTab: ++activeTab
            })
          }
        } else if (clientX - this.tapStartX > 5) {
          //向右滑动超过5个单位，activeTab减少
          console.log('向右滑动')
          if (activeTab > 0) {
            this.setData({
              activeTab: --activeTab
            })
          }
        }
        stv.offset = stv.windowWidth * activeTab;
      } else {
        //Y距离大于50 所以用户是上下滑动
        console.log('竖直滑动距离大于50')
        let page = Math.round(offset / windowWidth);
        if (activeTab != page) {
          this.setData({
            activeTab: page
          })
        }
        stv.offset = stv.windowWidth * page;
      }
    } else {
      let page = Math.round(offset / windowWidth);
      if (activeTab != page) {
        this.setData({
          activeTab: page
        })
      }
      stv.offset = stv.windowWidth * page;
    }
    stv.tStart = false;
    this.setData({
      stv: this.data.stv
    })
  },
  ////////
  _updateSelectedPage(page) {
    console.log('_updateSelectedPage')
    let {
      tabs,
      stv,
      activeTab
    } = this.data;
    activeTab = page;
    this.setData({
      activeTab: activeTab
    })
    stv.offset = stv.windowWidth * activeTab;
    this.setData({
      stv: this.data.stv
    })
  },
  handlerTabTap(e) {
    console.log('handlerTapTap', e.currentTarget.dataset.index)
    this._updateSelectedPage(e.currentTarget.dataset.index);
  },
  //事件处理函数
  swiperchange: function(e) {
    //console.log('swiperCurrent',e.detail.current)
    let {
      tabs,
      stv,
      activeTab
    } = this.data;
    activeTab = e.detail.current;
    this.setData({
      activeTab: activeTab
    })
    stv.offset = stv.windowWidth * activeTab;
    this.setData({
      stv: this.data.stv
    })
  },
  toIndexPage: function() {
    wx.switchTab({
      url: "/pages/classification/index"
    });
  },
>>>>>>> 3c9efab559ef34a2ec451515ff0f9b458b258e89
})