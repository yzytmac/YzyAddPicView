使用步骤：
1、在布局文件中添加
2、在activity中findviewbyid
3、重写onActivityForResult方法，在里面调用addPicture()，传入选择图片到uri
4、控件默认是4列，可选8张图，点击加号默认打开系统相册。
调用setMaxCounts（）方法可以设置图片数量;
调用setCustomClickCallBack（）方法可以修改点击加号后的响应事件，如去拍照;
调用setNumColumns（）方法可以修改列数，也可以在布局文件中使用numcolums自定义属性设置列数
- 效果图  
![](https://raw.githubusercontent.com/yzytmac/AddPicView/master/device-2017-05-11-135700.png)
