package com.ctop007.holeCard

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout


/**
 * @author ctop007
 * @date 2022/2/23 10:22 上午
 * @doc: 带孔的卡片视图
 * @link:
 */
open class HoleCardView : FrameLayout {

    companion object {
        const val ORIENTATION_HORIZONTAL = 0
        const val ORIENTATION_VERTICAL = 1

        const val SIDE_START = 0
        const val SIDE_END = 1
    }

    private lateinit var cardPaint: Paint
    private lateinit var dashLinePaint: Paint


    //卡片圆角
    private var radius: Int
    private var bgColor: Int

    //孔半径
    private var holeRadius: Int

    //圆孔偏移
    private var holeOffset: Int

    //圆孔与虚线的位置偏移
    private var lineOffset: Int

    //方向
    private var orientation: Int

    //开始方向
    private var startSide: Int

    //虚线是否可视
    private var dashVisible: Boolean

    //虚线高度
    private var dashRadius: Int


    //虚线间隔
    private var dashGap: Int

    //虚线颜色
    private var dashColor: Int

    //虚线边距
    private var dashLineMarginStart: Int
    private var dashLineMarginEnd: Int

    //view宽度
    private var cardWidth = 0

    //view的高度
    private var cardHeight = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.HoleCardView, defStyleAttr, 0)

        //卡片圆角
        radius = attr.getDimensionPixelSize(R.styleable.HoleCardView_hc_radius, dp2px(8f))
        bgColor = attr.getColor(R.styleable.HoleCardView_hc_color, Color.WHITE)

        //圆孔半径
        holeRadius = attr.getDimensionPixelSize(R.styleable.HoleCardView_hc_hole_radius, dp2px(8f))

        //圆孔偏移
        holeOffset = attr.getDimensionPixelSize(R.styleable.HoleCardView_hc_hole_offset, 0)
        lineOffset = attr.getDimensionPixelSize(R.styleable.HoleCardView_hc_offset, dp2px(20f))

        //方向
        orientation = attr.getInt(R.styleable.HoleCardView_hc_orientation, ORIENTATION_HORIZONTAL)
        startSide = attr.getInt(R.styleable.HoleCardView_hc_startSide, SIDE_START)

        //虚线可视
        dashVisible = attr.getBoolean(R.styleable.HoleCardView_hc_dash_visible, true)
        dashRadius = attr.getDimensionPixelSize(R.styleable.HoleCardView_hc_dash_radius, dp2px(1f))
        dashGap = attr.getDimensionPixelSize(R.styleable.HoleCardView_hc_dash_gap, dp2px(5f))
        dashColor = attr.getColor(R.styleable.HoleCardView_hc_dash_color, Color.GRAY)

        //虚线边距
        dashLineMarginStart = attr.getDimensionPixelSize(R.styleable.HoleCardView_hc_dash_margin_start, 0)
        dashLineMarginEnd = attr.getDimensionPixelSize(R.styleable.HoleCardView_hc_dash_margin_end, 0)


        attr.recycle()

        initView()
    }

    private fun initView() {

        cardPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        cardPaint.isDither = true
        cardPaint.color = bgColor
        cardPaint.style = Paint.Style.FILL

        dashLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        dashLinePaint.isDither = true
        dashLinePaint.color = dashColor
        dashLinePaint.strokeWidth = dashRadius.toFloat()
        dashLinePaint.style = Paint.Style.FILL

        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cardWidth = w
        cardHeight = h
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 设置画笔遮罩滤镜  ,传入度数和样式
        //cardPaint.maskFilter = BlurMaskFilter(radius.toFloat(), BlurMaskFilter.Blur.SOLID)
        //绘制背景
        canvas?.drawRoundRect(paddingLeft.toFloat(), paddingTop.toFloat(), cardWidth.toFloat() - paddingRight,
            cardHeight.toFloat() - paddingBottom, radius.toFloat(), radius.toFloat(), cardPaint)


        var r1x = 0f
        var r1y = 0f
        var r2x = 0f
        var r2y = 0f
        var startX = 0f
        var startY = 0f
        var endX = 0f
        var endY = 0f

        when {
            orientation == ORIENTATION_HORIZONTAL && startSide == SIDE_START -> {
                r1x = paddingLeft + lineOffset.toFloat()
                r1y = holeOffset.toFloat() + paddingTop
                r2x = r1x
                r2y = cardHeight.toFloat() - paddingBottom - holeOffset

                startX = r1x
                startY = r1y + holeRadius + dashLineMarginStart
                endX = r1x
                endY = r2y - holeRadius - dashLineMarginEnd
            }

            orientation == ORIENTATION_HORIZONTAL && startSide == SIDE_END -> {
                r1x = cardWidth - paddingRight - lineOffset.toFloat()
                r1y = holeOffset.toFloat() + paddingTop
                r2x = r1x
                r2y = cardHeight.toFloat() - paddingBottom - holeOffset

                startX = r1x
                startY = r1y + holeRadius + dashLineMarginStart
                endX = r1x
                endY = r2y - holeRadius - dashLineMarginEnd
            }

            orientation == ORIENTATION_VERTICAL && startSide == SIDE_START -> {
                r1x = paddingLeft + holeOffset.toFloat()
                r1y = paddingTop + lineOffset.toFloat()
                r2x = cardWidth - paddingRight - holeOffset.toFloat()
                r2y = r1y

                startX = r1x + holeRadius + dashLineMarginStart
                startY = r1y
                endX = r2x - holeRadius - dashLineMarginEnd
                endY = r1y
            }

            orientation == ORIENTATION_VERTICAL && startSide == SIDE_END -> {
                r1x = paddingLeft + holeOffset.toFloat()
                r1y = cardHeight - paddingBottom - lineOffset.toFloat()
                r2x = cardWidth - paddingRight - holeOffset.toFloat()
                r2y = r1y

                startX = r1x + holeRadius + dashLineMarginStart
                startY = r1y
                endX = r2x - holeRadius - dashLineMarginEnd
                endY = r1y
            }
        }

        // 设置画笔遮罩滤镜  ,传入度数和样式
        //cardPaint.maskFilter = BlurMaskFilter(radius.toFloat(), BlurMaskFilter.Blur.INNER)

        cardPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

        //绘制圆孔
        canvas?.drawCircle(r1x, r1y, holeRadius.toFloat(), cardPaint)
        canvas?.drawCircle(r2x, r2y, holeRadius.toFloat(), cardPaint)

        cardPaint.xfermode = null


        //绘制虚线
        if (dashVisible) {
            val path = Path()
            path.addCircle(0f, 0f, dashRadius / 2f, Path.Direction.CW)
            dashLinePaint.pathEffect = PathDashPathEffect(path, dashGap.toFloat(), 0f, PathDashPathEffect.Style.ROTATE)
            canvas?.drawLine(startX, startY, endX, endY, dashLinePaint)
        }

    }

    private fun dp2px(dp: Float): Int {
        return (dp * context.resources.displayMetrics.density + 0.5f).toInt()
    }


    /**
     * 获取卡片圆角
     * @return 圆角值
     * */
    fun getCardRadius(): Int {
        return this.radius
    }

    /**
     * 设置卡片圆角
     * @param radius 圆角值
     * */
    fun setCardRadius(radius: Int) {
        this.radius = radius
        this.invalidate()
    }


    /**
     * 获取背景颜色
     * @return
     * */
    fun getBgColor(): Int {
        return this.bgColor
    }

    /**
     * 设置卡片圆角
     * @param color 圆角值
     * */
    fun setBgColor(color: Int) {
        this.bgColor = color
        cardPaint.color = bgColor
        this.invalidate()
    }

    /**
     * 获取背景颜色
     * @return
     * */
    fun setCardRadius(): Int {
        return this.bgColor
    }


    /**
     * 设置穿孔圆的半径
     * @param radius
     * */
    fun setHoleRadius(radius: Int) {
        this.holeRadius = radius
        this.invalidate()
    }


    /**
     * 设置穿孔圆的偏移
     * @param offset 偏移量 , 如果 offset > 0 则向内偏移,否者向外偏移
     * */
    fun setHoleOffset(offset: Int) {
        this.holeOffset = offset
        this.invalidate()
    }

    /**
     * 设置空和虚线位置的偏移
     * @param offset 偏移量 ,
     * 如果 方向为 [HoleCardView.ORIENTATION_HORIZONTAL] 则该值表示与左/右边的距离偏量
     * 如果 方向为 [HoleCardView.ORIENTATION_VERTICAL] 则该值表示与顶/底部的距离偏量
     * */
    fun setPositionOffset(offset: Int) {
        this.lineOffset = offset
        this.invalidate()
    }

    /**
     * 设置内容方向
     * @param orientation 方向 [HoleCardView.ORIENTATION_HORIZONTAL] 或 [HoleCardView.ORIENTATION_VERTICAL]
     * 如果 方向为 [HoleCardView.ORIENTATION_HORIZONTAL] 则表示孔洞在上下两侧
     * 如果 方向为 [HoleCardView.ORIENTATION_VERTICAL] 则表示孔洞在左右两侧
     *
     * */
    fun setOrientation(orientation: Int) {
        this.orientation = orientation
        this.invalidate()
    }

    /**
     * 设置开始的一侧
     * @param side 方向 [HoleCardView.SIDE_START] 或 [HoleCardView.SIDE_END]
     * 如果 方向为 [HoleCardView.ORIENTATION_HORIZONTAL] 则 [HoleCardView.SIDE_START] 表示 顶部开始
     * 如果 方向为 [HoleCardView.ORIENTATION_VERTICAL] 则[HoleCardView.SIDE_START]表示 左边开始
     *
     * */
    fun setStartSide(side: Int) {
        this.startSide = side
        this.invalidate()
    }

    /**
     * 设置虚线是否可见
     * @param isVisible
     * */
    fun setDashVisible(isVisible: Boolean) {
        this.dashVisible = isVisible
        this.invalidate()
    }

    /**
     * 设置虚线圆的半径
     * @param radius
     * */
    fun setDashRadius(radius: Int) {
        this.dashRadius = radius
        this.invalidate()
    }

    /**
     * 设置虚线圆之间的间隔
     * @param gap
     * */
    fun setDashGap(gap: Int) {
        this.dashGap = gap
        this.invalidate()
    }

    /**
     * 设置虚线颜色
     * @param color
     * */
    fun setDashColor(color: Int) {
        this.dashColor = color

        this.invalidate()
    }

    /**
     * 设置虚线开始的边距 (与圆孔之间的边距)
     * @param startMargin
     * */
    fun setDashMarginStart(startMargin: Int) {
        this.dashLineMarginStart = startMargin
        this.invalidate()
    }

    /**
     * 设置虚线结束的边距 (与圆孔之间的边距)
     * @param endMargin
     * */
    fun setDashMarginEnd(endMargin: Int) {
        this.dashLineMarginEnd = endMargin
        this.invalidate()
    }

}