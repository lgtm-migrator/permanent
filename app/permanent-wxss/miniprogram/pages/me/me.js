// miniprogram/pages/me/me.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfoData: {
      customersId: '',
      username: '',
      nickname: '',
      avatarUrl: '/images/user-unlogin.png',
      gender: ''
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              this.setData({
                userInfoData: {
                  nickname: res.userInfo.nickName,
                  avatarUrl: res.userInfo.avatarUrl,
                  gender: res.userInfo.gender
                }
              })
            }
          })
        }
      }
    })
  },

  onGetUserInfo: function(e) {
    if (!this.data.logged && e.detail.userInfo) {
      this.setData({
        userInfoData: {
          avatarUrl: e.detail.userInfo.avatarUrl,
          userInfo: e.detail.userInfo
        }
      })
    }
  },

  onLogin: function() {
    const self = this;
    if (!self.data.customersId){
      return
    }
    wx.login({
      success: function(res) {
        if (res.code) {
          wx.request({
            url: 'https://permanent.qianxunclub.com/api/auth/wxss/' + res.code,
            method: 'post',
            dataType: 'json',
            contentType: 'application/json;charset=utf-8',
            data: {
              nickname: self.data.userInfoData.nickname,
              avatarUrl: self.data.userInfoData.avatarUrl,
              gender: self.data.userInfoData.gender
            },
            success: function(res) {
              if (res.data.code == 0) {
                self.data.userInfoData.customersId = res.data.data.customersId
                self.data.userInfoData.username = res.data.data.username
                wx.setStorageSync("customers",res.data.data)
                wx.setStorageSync("sid", res.header['Set-Cookie'])
              } else {

              }
            },
            fail: function(res) {
              console.log(res)
            }
          })
        }
      },
      fail: function(res) {

      }
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})