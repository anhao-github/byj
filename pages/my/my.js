// pages/my/my.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        u:{}
    },
    link(){
        wx.navigateTo({
          url: '../myDevice/myDevice',
        })
    },
    loginOut(){
        wx.removeStorageSync('u')
        wx.reLaunch({
          url: '../login/login',
        })
    },
    onShow(){
        this.setData({
            u:wx.getStorageSync('u')
        })
    }
})