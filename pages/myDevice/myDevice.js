// pages/myDevice/myDevice.js
Page({

    /**
     * 页面的初始数据
     */
    data: {

    },
    backDevice(){
        wx.showModal({
          title: '系统提示',
          content: '您确定要归还吗？',
          complete: (res) => {
            if (res.cancel) {
              // console.log("取消")
            }
        
            if (res.confirm) {
              console.log("确定")
              wx.showLoading({
                title: '正在归还中',
                mask:true
              });
              //wx.request("api 接口")
              setTimeout(() => {
                wx.hideLoading();
                wx.showToast({
                  title: '归还成功',
                  icon:"success",
                  mask:true
                })
              }, 1000);
            }
          }
        })
      },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})