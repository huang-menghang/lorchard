var api = require('../config/api.js');
//日期格式化
const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}
//数字格式化
const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

// get 请求
function requestByGet(req) {
  wx.request({
    url: req.url,
    data: req.data,
    method: 'GET',
    success: function (res) {
      req.success(res.data)
    },
    fail: function (res) {
      console.log(res);
    }
  })

}

// post 请求
function requestByPost(req) {
  wx.request({
    url: req.url,
    data: req.data,
    header: {
      "content-type": "application/x-www-form-urlencoded"
    },
    method: 'POST',
    success: function (res) {
      req.success(res.data)
    },
    fail: function (res) {
      console.log(res);
    }
  })
}

//json格式的post请求
function requestJsonByPost(req) {
  wx.request({
    url: req.url,
    data: req.data,
    header: {
      "content-type": "application/json;charset=utf-8"
    },
    method: 'POST',
    dataType: 'json',
    success: function (res) {
      req.success(res.data)
    },
    fail: function (res) {
      console.log(res);
    }
  })
}

//delete请求
function requestByDelete(req) {
  wx.request({
    url: req.url,
    data: req.data,
    method: 'DELETE',
    success: function (res) {
      req.success(res.data)
    },
    fail: function (res) {
      console.log(res);
    }
  })
}

//put请求
function requestByPut(req) {
  wx.request({
    url: req.url,
    data: req.data,
    header: {
      "content-type": "application/x-www-form-urlencoded"
    },
    method: 'PUT',
    success: function (res) {
      req.success(res.data)
    },
    fail: function (res) {
      console.log(res);
    }
  })
}

/**
 * 检查微信会话是否过期
 */
function checkSession() {
  return new Promise(function (resolve, reject) {
    wx.checkSession({
      success: function () {
        resolve(true);
      },
      fail: function () {
        reject(false);
      }
    })
  });
}

/**
 * 调用微信登录
 */
function login() {
  return new Promise(function (resolve, reject) {
    wx.login({
      success: function (res) {
        if (res.code) {
          //登录远程服务器
          resolve(res);
        } else {
          reject(res);
        }
      },
      fail: function (err) {
        reject(err);
      }
    });
  });
}

function getUserInfo() {
  return new Promise(function (resolve, reject) {
    wx.getUserInfo({
      withCredentials: true,
      success: function (res) {
        resolve(res);
      },
      fail: function (err) {
        reject(err);
      }
    })
  });
}

function redirect(url) {

  //判断页面是否需要登录
  if (false) {
    wx.redirectTo({
      url: '/pages/auth/login/login'
    });
    return false;
  } else {
    wx.redirectTo({
      url: url
    });
  }
}

function showErrorToast(msg) {
  wx.showToast({
    title: msg,
    image: '/static/images/icon_error.png'
  })
}

module.exports = {
  formatTime: formatTime,
  requestGet: requestByGet,
  requestPost: requestByPost,
  requestDelete: requestByDelete,
  requestPut: requestByPut,
  requestJson: requestJsonByPost,
  redirect,
  showErrorToast,
  checkSession,
  login,
  getUserInfo,
}

