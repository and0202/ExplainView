# ExplainView
对View的讲解，从基础坐标系讲，逐步深入

###第一讲：坐标体系
	屏幕坐标体系:左上角为原点(0，0)，向右为X轴正值，向下为Y轴正值
		以800*480手机为例，右下角坐标为(480,800)
###第二讲：View创建时的生命周期：
	构造方法初始化：初始化相应属性
	onFinishInflate()：加载xml文件完成，View完成初始化。
	onAttachedToWindow()：将View绑定到Activity所在的Window，之后才开始真正的绘制操作
	onMeasure()：计算View的宽度、高度，会多次调用。
	onSizeChanged()：在视图的大小发生改变时调用该方法，会被多次调用。
	onLayout()：计算View在坐标系中位置
	onWindowFocusChanged() true：View获得焦点
	              如果期望实现进入Activity时弹出Dialog或PopupWindow，必须在此方法之后才不会报错。
	onDraw()：真正的开始绘制View
	         View绘制过程中会多次调用onMeasure()方法，来确定View的大小
	onWindowFocusChanged() true：View失去焦点
	onDetachedFromWindow()：Activity销毁时，View从Window上移除
	
	--------------------------------------------------
	获取View的宽度和高度的几种方案:
	推荐方案：
		view.post(runnable);
	其他方案：
		view.getViewTreeObserver().addOnPreDrawListener、
		或
		view.getViewTreeObserver().addOnGlobalLayoutListener
		或
		view.addOnLayoutChangeListener
 		注意：  以上三种方案都存在重复调用的问题，需要及时移除监听
 	其他思路：
		根据View创建时的生命周期可以知道，只要在onMeasure()方法之后都可以获得View的真实宽高

###第三讲：Canvas、Path、Matrix使用
#####Canvas使用示例：
![饼状图](http://upload-images.jianshu.io/upload_images/1494999-543d3e13cf97d002.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

	步骤
	1 获取宽高，计算出半径R
	2 移动到圆心
	3 根据角度绘制 『弧度』
	
----------------------------

![两个简单图形](http://upload-images.jianshu.io/upload_images/1494999-d9aabfa6e0b7c1e1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

	圆形：
	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width/2,height/2);

        // 绘制两个圆形
        canvas.drawCircle(0,0,MAX_RADIU,mPaint);
        canvas.drawCircle(0,0,MIN_RADIU,mPaint);
        for (int index =0;index < 20; index++){
        	//绘制直线
            canvas.drawLine(0,MIN_RADIU,0,MAX_RADIU,mPaint);
            canvas.rotate(20);
        }

    }
	

	正方形：
	 @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width/2,height/2);
        for (int index =0;index < 10; index++){
        	//关键方法
            canvas.scale(0.8f,0.8f);
            canvas.drawRect(rectF,mPaint);
        }
    }
----------------------------------
#####Path使用示例：
![饼状图](http://upload-images.jianshu.io/upload_images/1494999-f38b747f435f8e9a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


	雷达蜘蛛网：
	核心：Path的两个方法：
		moveTo(float x, float y) ——-- 设置下一条路径的起始点
		lineTo(float x, float y) ——-- 从上一个点到 (x, y) 点绘制一条直线
	步骤：
		数学知识：
		角度和弧度的区别：
			角度：两条射线从圆心向圆周射出，形成一个夹角和夹角正对的一段弧。当这段弧长正好等于圆周长的360分之一时，两条射线的夹角的大小为1度。
			弧度：两条射线从圆心向圆周射出，形成一个夹角和夹角正对的一段弧。当这段弧长正好等于圆的半径时，两条射线的夹角大小为1弧度。	
		有定义可知：圆所对应的弧长是半径的几倍，那么角的大小就是几弧度。
				   周角(360度)= 2π弧度
				   平角(180度)= π弧度
		1 绘制单个多边形
			使用canvas.drawPath()绘制多边形
		2 绘制多个多边形
		3 连线中心到末端
		4 绘制分值区域
			色值加透明度
	 
	 
	 参考：http://www.jianshu.com/p/590685091f58
	 	  http://www.jianshu.com/p/29f807426fde
	 	  角度和弧度定义及转换：http://developer.178.com/201103/95392224259.html



###第四讲：贝赛尔曲线实例：
#####圆变成心形实例：
![](http://upload-images.jianshu.io/upload_images/1494999-8e84775f9438bb14.gif?imageMogr2/auto-orient/strip)
	
	步骤如下：
		1 绘制坐标系()
  		2 绘制数据点和辅助线
    	3 绘制贝赛尔曲线，动态变更数据
   		4 把1，2步注释掉，即可。

#####一个酷炫的Loading
	步骤如下：
		1 绘制背景
		2 绘制进度条，所有部分
		3 绘制叶子
		4 绘制进度条，完成部分
		5 绘制遮罩
		6 绘制风扇
		
	 
######参考文章：
[一个绚丽的loading动效分析与实现！](http://blog.csdn.net/tianjian4592/article/details/44538605)

###第四讲：ListView源码研究
