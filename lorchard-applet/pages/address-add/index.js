// pages/address/detail.js
var commonCityData = require('../../utils/city.js')
var api = require('../../config/api.js');
var util = require('../../utils/util.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    provinces: [],
    citys: [],
    districts: [],
    selProvince: '请选择',
    selCity: '请选择',
    selDistrict: '请选择',
    selProvinceIndex: 0,
    selCityIndex: 0,
    selDistrictIndex: 0,
    isDefault: 0,
    addressData: {},
    memberId: 0
  },


  //修改默认地址选择
  changeDefault: function () {
    if (this.data.isDefault == 1) {
      this.setData({
        isDefault: 0
      })
    } else {
      this.setData({
        isDefault: 1
      })
    }
    console.log(this.data)
  },

  //表单提交地址
  bindSave: function (e) {
    var that = this
    if (e.detail.value.consignee == '' || e.detail.value.consignee.length == 0) {
      wx.showModal({
        title: '提示',
        content: '联系人不能为空',
        showCancel: false,
        confirmColor: '#29aceb'
      })
    } else if (e.detail.value.mobile.length == 0) {
      wx.showModal({
        title: '提示',
        content: '手机号码不能为空',
        showCancel: false,
        confirmColor: '#29aceb'
      })
    } else if (e.detail.value.mobile.length != 11) {
      wx.showModal({
        title: '提示',
        content: '请输入11位手机号码',
        showCancel: false,
        confirmColor: '#29aceb'
      })
    } else if (this.data.selCity == '请选择' || this.data.selCity.length == 0) {
      wx.showModal({
        title: '提示',
        content: '请选择地址！',
        showCancel: false,
        confirmColor: '#29aceb'
      })
    } else if (e.detail.value.address == '' || e.detail.value.address.length == 0) {
      wx.showModal({
        title: '提示',
        content: '请输入地址！',
        showCancel: false,
        confirmColor: '#29aceb'
      })
    } else {
      var id = e.currentTarget.dataset.id;
      console.log(id == undefined)
      if (this.data.selDistrict == '请选择') {
        this.setData({
          selDistrict: ''
        })
      }
      if (id == undefined) {
        console.log("post")
        util.requestPost({
          url: api.AddressDetail,
          data: {
            mobile: e.detail.value.mobile,
            consignee: e.detail.value.consignee,
            province: this.data.selProvince,
            city: this.data.selCity,
            diatrict: this.data.selDistrict,
            address: e.detail.value.address,
            memberId: this.data.memberId,
            state: this.data.isDefault
          },
          success: function (res) {
            if (res.code == 0) {
              var callback = null;
              var addressId = res.data;
              if (that.data.orderNo != null) {
                wx.navigateTo({
                  url: '../order/detail?orderNo=' + that.data.orderNo + '&addressId=' + addressId,
                })
              } else {
                wx.navigateBack({
                  
                })
              }
              util.showWarn('添加成功', callback);
            }
          }
        });
      } else {
        util.requestPut({
          url: api.AddressDetail,
          data: {
            mobile: e.detail.value.mobile,
            consignee: e.detail.value.consignee,
            province: this.data.selProvince,
            city: this.data.selCity,
            diatrict: this.data.selDistrict,
            address: e.detail.value.address,
            memberId: this.data.memberId,
            state: this.data.isDefault,
            id: this.data.addressData.id
          },
          success: function (res) {
            console.log(res.data)
            if (res.code == 0) {
              wx.showModal({
                title: '提示',
                content: '操作成功',
                showCancel: false,
                confirmColor: '#29aceb',
                success: function () {
                  wx.navigateBack({
                   
                  })
                }
              })
            } else {

            }

          }
        });
      }
    }
  },

  //省市级联动
  initCityData: function (level, obj) {
    if (level == 1) {
      var pinkArray = [];
      for (var i = 0; i < commonCityData.cityData.length; i++) {
        pinkArray.push(commonCityData.cityData[i].name);
      }
      this.setData({
        provinces: pinkArray
      });
    } else if (level == 2) {
      var pinkArray = [];
      var dataArray = obj.cityList
      for (var i = 0; i < dataArray.length; i++) {
        pinkArray.push(dataArray[i].name);
      }
      this.setData({
        citys: pinkArray
      });
    } else if (level == 3) {
      var pinkArray = [];
      var dataArray = obj.districtList
      for (var i = 0; i < dataArray.length; i++) {
        pinkArray.push(dataArray[i].name);
      }
      this.setData({
        districts: pinkArray
      });
    }
  },
  bindPickerProvinceChange: function (event) {
    var selIterm = commonCityData.cityData[event.detail.value];
    this.setData({
      selProvince: selIterm.name,
      selProvinceIndex: event.detail.value,
      selCity: '请选择',
      selCityIndex: 0,
      selDistrict: '请选择',
      selDistrictIndex: 0
    })
    this.initCityData(2, selIterm)
  },
  bindPickerCityChange: function (event) {
    var selIterm = commonCityData.cityData[this.data.selProvinceIndex].cityList[event.detail.value];
    this.setData({
      selCity: selIterm.name,
      selCityIndex: event.detail.value,
      selDistrict: '请选择',
      selDistrictIndex: 0
    })
    this.initCityData(3, selIterm)
  },
  bindPickerChange: function (event) {
    var selIterm = commonCityData.cityData[this.data.selProvinceIndex].cityList[this.data.selCityIndex].districtList[event.detail.value];
    if (selIterm && selIterm.name && event.detail.value) {
      this.setData({
        selDistrict: selIterm.name,
        selDistrictIndex: event.detail.value
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    this.initCityData(1);
    this.setData({
      memberId: options.memberId
    })
    console.log(options.id == undefined)
    if (options.id != undefined) {
      this.initAddress(options.id);
    }
    if (options.orderNo != null) {
      this.setData({
        orderNo: options.orderNo
      })
    }

  },

  initAddress: function (id) {
    var that = this;
    util.requestGet({
      url: api.AddressDetail + "?id=" + id,
      success: function (res) {
        that.setData({
          addressData: res.data,
          selProvince: res.data.province,
          selCity: res.data.city,
          selDistrict: res.data.diatrict,
          isDefault: res.data.state,
        });
      }
    })
  }

})