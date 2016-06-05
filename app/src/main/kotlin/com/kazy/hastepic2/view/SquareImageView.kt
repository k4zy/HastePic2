package com.kazy.hastepic2.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView

class SquareImageView : ImageView {

    var preventLayout = false

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
    }

    override fun requestLayout() {
        if (!preventLayout) {
            super.requestLayout()
        }
    }

    override fun setImageDrawable(drawable: Drawable?) {
        preventLayout = true
        super.setImageDrawable(drawable)
        preventLayout = false
    }
}
