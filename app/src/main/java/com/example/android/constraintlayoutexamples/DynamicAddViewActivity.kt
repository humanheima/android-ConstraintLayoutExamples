package com.example.android.constraintlayoutexamples

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.constraint.Guideline
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_dynamic_add_view.*

class DynamicAddViewActivity : AppCompatActivity() {


    companion object {

        @JvmStatic
        fun launch(context: Context) {
            val intent = Intent(context, DynamicAddViewActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_dynamic_add_view)
        //addView()
        //addViewPartView()
        //changeLayoutParams()
        //changeConstraintSet()

        /* ivLeft.setOnClickListener {
             changeConstraintSet()
         }*/

        addGuideLine()

        //addViewUseConstraintSet()
    }

    /**
     * All view is dynamically added.
     */
    private fun addView() {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.id = R.id.cl_root
        constraintLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        //先设置根布局
        setContentView(constraintLayout)

        val ivLeft = ImageView(this)
        ivLeft.id = R.id.ivLeft
        ivLeft.scaleType = ImageView.ScaleType.CENTER_CROP
        ivLeft.setImageResource(R.drawable.lake)

        val ivLeftLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                0, 0)
        ivLeftLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)
        ivLeftLayoutParams.leftToLeft = R.id.cl_root
        ivLeftLayoutParams.dimensionRatio = "w,16:9"
        ivLeftLayoutParams.topToTop = R.id.tvRight
        ivLeftLayoutParams.bottomToBottom = R.id.tvRight

        ivLeft.layoutParams = ivLeftLayoutParams

        val tvRight = TextView(this)
        tvRight.id = R.id.tvRight
        tvRight.text = getString(R.string.lake_tahoe_title)
        tvRight.textSize = 30F
        val tvRightLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvRightLayoutParams.startToEnd = R.id.ivLeft
        tvRightLayoutParams.topToTop = R.id.cl_root
        tvRightLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)
        tvRightLayoutParams.topMargin = ScreenUtil.dpToPx(this, 16)

        tvRight.layoutParams = tvRightLayoutParams


        val tvBottom = TextView(this)
        tvBottom.id = R.id.tvBottom
        tvBottom.text = getString(R.string.lake_discription)
        val tvBottomLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                0,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvBottomLayoutParams.startToStart = R.id.cl_root
        tvBottomLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.endToEnd = R.id.cl_root
        tvBottomLayoutParams.marginEnd = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.topToBottom = R.id.tvRight
        tvBottomLayoutParams.topMargin = ScreenUtil.dpToPx(this, 24)

        tvBottomLayoutParams.bottomMargin = ScreenUtil.dpToPx(this, 8)

        tvBottom.layoutParams = tvBottomLayoutParams


        constraintLayout.addView(ivLeft)
        constraintLayout.addView(tvRight)
        constraintLayout.addView(tvBottom)


    }


    /**
     * Add View use ConstraintSet
     */
    private fun addViewUseConstraintSet() {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.id = R.id.cl_root
        constraintLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        //先设置根布局
        setContentView(constraintLayout)

        val constraintSet = ConstraintSet()

        val ivLeft = ImageView(this)
        ivLeft.id = R.id.ivLeft
        ivLeft.scaleType = ImageView.ScaleType.CENTER_CROP
        ivLeft.setImageResource(R.drawable.lake)

        constraintSet.connect(R.id.ivLeft, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START,
                ScreenUtil.dpToPx(this, 8))
        constraintSet.connect(R.id.ivLeft, ConstraintSet.END, R.id.tvRight, ConstraintSet.START)
        constraintSet.connect(R.id.ivLeft, ConstraintSet.TOP, R.id.tvRight, ConstraintSet.TOP)
        constraintSet.connect(R.id.ivLeft, ConstraintSet.BOTTOM, R.id.tvRight, ConstraintSet.BOTTOM)

        constraintSet.constrainHeight(R.id.ivLeft, 0)
        constraintSet.constrainWidth(R.id.ivLeft, 0)
        constraintSet.setDimensionRatio(R.id.ivLeft, "h,16:9")

        val tvRight = TextView(this)
        tvRight.id = R.id.tvRight
        tvRight.text = getString(R.string.lake_tahoe_title)
        tvRight.textSize = 30F

        constraintSet.constrainHeight(R.id.tvRight, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        constraintSet.constrainWidth(R.id.tvRight, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        constraintSet.connect(R.id.tvRight, ConstraintSet.START, R.id.ivLeft, ConstraintSet.END,
                ScreenUtil.dpToPx(this, 8))
        constraintSet.connect(R.id.tvRight, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP,
                ScreenUtil.dpToPx(this, 16))


        val tvBottom = TextView(this)
        tvBottom.id = R.id.tvBottom
        tvBottom.text = getString(R.string.lake_discription)

        constraintSet.connect(R.id.tvBottom, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START,
                ScreenUtil.dpToPx(this, 8))

        constraintSet.connect(R.id.tvBottom, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END,
                ScreenUtil.dpToPx(this, 8))

        constraintSet.connect(R.id.tvBottom, ConstraintSet.TOP, R.id.tvRight, ConstraintSet.BOTTOM,
                ScreenUtil.dpToPx(this, 24))

        //设置高度
        constraintSet.constrainHeight(R.id.tvBottom,ConstraintLayout.LayoutParams.WRAP_CONTENT)

        constraintLayout.addView(ivLeft)
        constraintLayout.addView(tvRight)
        constraintLayout.addView(tvBottom)

        TransitionManager.beginDelayedTransition(constraintLayout)

        constraintSet.applyTo(constraintLayout)
    }


    private fun addGuideLine() {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.id = R.id.cl_root
        constraintLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        //先设置根布局
        setContentView(constraintLayout)

        val guideline = Guideline(this)
        guideline.id = R.id.guideline

        val guideLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        guideLayoutParams.guidePercent = 0.4f
        guideLayoutParams.topToTop = R.id.cl_root
        guideLayoutParams.bottomToBottom = R.id.cl_root
        //注意
        guideLayoutParams.orientation = ConstraintLayout.LayoutParams.HORIZONTAL

        guideline.layoutParams = guideLayoutParams

        constraintLayout.addView(guideline)

        val ivLeft = ImageView(this)
        ivLeft.id = R.id.ivLeft
        ivLeft.scaleType = ImageView.ScaleType.CENTER_CROP
        ivLeft.setImageResource(R.drawable.lake)

        val ivLeftLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                0, 0)
        ivLeftLayoutParams.dimensionRatio = "h,16:9"
        ivLeftLayoutParams.bottomToTop = R.id.guideline
        ivLeftLayoutParams.startToStart = R.id.cl_root
        ivLeftLayoutParams.endToEnd = R.id.cl_root

        ivLeft.layoutParams = ivLeftLayoutParams

        constraintLayout.addView(ivLeft)

    }

    private fun addViewPartView() {
        val ivLeft = ImageView(this)
        ivLeft.id = R.id.ivLeft
        ivLeft.scaleType = ImageView.ScaleType.CENTER_CROP
        ivLeft.setImageResource(R.drawable.lake)

        val ivLeftLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                0, 0)
        ivLeftLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)
        ivLeftLayoutParams.leftToLeft = R.id.cl_root
        ivLeftLayoutParams.dimensionRatio = "w,16:9"
        ivLeftLayoutParams.topToTop = R.id.tvRight
        ivLeftLayoutParams.bottomToBottom = R.id.tvRight

        ivLeft.layoutParams = ivLeftLayoutParams

        val tvRight = TextView(this)
        tvRight.id = R.id.tvRight
        tvRight.text = getString(R.string.lake_tahoe_title)
        tvRight.textSize = 30F
        val tvRightLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvRightLayoutParams.startToEnd = R.id.ivLeft
        tvRightLayoutParams.topToTop = R.id.cl_root
        tvRightLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)
        tvRightLayoutParams.topMargin = ScreenUtil.dpToPx(this, 16)

        tvRight.layoutParams = tvRightLayoutParams


        val tvBottomLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                0,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvBottomLayoutParams.startToStart = R.id.cl_root
        tvBottomLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.endToEnd = R.id.cl_root
        tvBottomLayoutParams.marginEnd = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.topToBottom = R.id.tvRight
        tvBottomLayoutParams.topMargin = ScreenUtil.dpToPx(this, 24)

        tvBottomLayoutParams.bottomMargin = ScreenUtil.dpToPx(this, 8)

        //tvBottom.layoutParams = tvBottomLayoutParams


        cl_root.addView(ivLeft)
        cl_root.addView(tvRight)

    }

    private fun changeLayoutParams() {
        val ivLeftLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                0, 0)
        ivLeftLayoutParams.leftMargin = ScreenUtil.dpToPx(this, 8)
        ivLeftLayoutParams.rightMargin = ScreenUtil.dpToPx(this, 8)
        ivLeftLayoutParams.leftToLeft = R.id.cl_root
        ivLeftLayoutParams.rightToRight = R.id.cl_root
        //注意这里和上面的不同
        ivLeftLayoutParams.dimensionRatio = "h,16:9"
        ivLeftLayoutParams.topToTop = R.id.cl_root
        //ivLeft.layoutParams = ivLeftLayoutParams

        val tvRightLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvRightLayoutParams.leftToLeft = R.id.cl_root
        tvRightLayoutParams.topToBottom = R.id.ivLeft
        tvRightLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)
        tvRightLayoutParams.topMargin = ScreenUtil.dpToPx(this, 16)

        //tvRight.layoutParams = tvRightLayoutParams


        val tvBottomLayoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                0,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        tvBottomLayoutParams.startToStart = R.id.cl_root
        tvBottomLayoutParams.marginStart = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.endToEnd = R.id.cl_root
        tvBottomLayoutParams.marginEnd = ScreenUtil.dpToPx(this, 8)

        tvBottomLayoutParams.topToBottom = R.id.tvRight
        tvBottomLayoutParams.topMargin = ScreenUtil.dpToPx(this, 24)

        tvBottomLayoutParams.bottomMargin = ScreenUtil.dpToPx(this, 8)

        //tvBottom.layoutParams = tvBottomLayoutParams
    }

    private fun changeConstraintSet() {
        val constraintSet = ConstraintSet()
        //从根布局中克隆约束参数
        constraintSet.clone(cl_root)

        //清空控件原有的约束
        constraintSet.clear(R.id.ivLeft)
        constraintSet.clear(R.id.tvRight)
        constraintSet.clear(R.id.tvBottom)

        constraintSet.constrainWidth(R.id.ivLeft, 0)
        constraintSet.constrainHeight(R.id.ivLeft, 0)
        //设置ivLeft顶部和父布局顶部对齐
        constraintSet.connect(R.id.ivLeft, ConstraintSet.TOP, R.id.cl_root, ConstraintSet.TOP)
        constraintSet.connect(R.id.ivLeft, ConstraintSet.START, R.id.cl_root, ConstraintSet.START,
                ScreenUtil.dpToPx(this, 8))
        constraintSet.connect(R.id.ivLeft, ConstraintSet.END, R.id.cl_root, ConstraintSet.END,
                ScreenUtil.dpToPx(this, 8))
        //设置宽高比
        constraintSet.setDimensionRatio(R.id.ivLeft, "h,16:9")


        constraintSet.constrainWidth(R.id.tvRight, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        constraintSet.constrainHeight(R.id.tvRight, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        constraintSet.connect(R.id.tvRight, ConstraintSet.START, R.id.cl_root, ConstraintSet.START,
                ScreenUtil.dpToPx(this, 8))
        //设置控件居中
        //constraintSet.centerHorizontally(R.id.tvRight, ConstraintSet.PARENT_ID)
        constraintSet.connect(R.id.tvRight, ConstraintSet.TOP, R.id.ivLeft, ConstraintSet.BOTTOM,
                ScreenUtil.dpToPx(this, 24))


        constraintSet.constrainHeight(R.id.tvBottom, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        constraintSet.connect(R.id.tvBottom, ConstraintSet.START, R.id.cl_root, ConstraintSet.START,
                ScreenUtil.dpToPx(this, 8))
        constraintSet.connect(R.id.tvBottom, ConstraintSet.END, R.id.cl_root, ConstraintSet.END,
                ScreenUtil.dpToPx(this, 8))

        constraintSet.connect(R.id.tvBottom, ConstraintSet.TOP, R.id.tvRight, ConstraintSet.BOTTOM,
                ScreenUtil.dpToPx(this, 24))

        constraintSet.applyTo(cl_root)
        //设置一个动画效果，让约束改变平滑一点，这一步不是必须的
        TransitionManager.beginDelayedTransition(cl_root)
    }

}
