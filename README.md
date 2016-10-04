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

	