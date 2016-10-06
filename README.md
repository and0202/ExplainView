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

###第三讲：Paint、Canvas、Matrix使用
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
