package com.mycheertribe.customviews

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import com.overflow.flowlayoutbutton.R


class FlowLayoutView : ConstraintLayout, FlowLayoutInterface {

    private var item_text: String? = null
    private var isSelected: Boolean? = null
    private lateinit var mTextView: TextView
    private lateinit var clickListener: FlowLayoutInterface
    private var mTextSize: Float = 16.0f
    private var mTextColor: Int? = null

    constructor(context: Context) : super(context) {
        initLayout(context)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.FlowLayoutView, 0, 0).apply()
        {
            try {
                val ta = context.obtainStyledAttributes(attrs, R.styleable.FlowLayoutView)
                item_text = ta.getString(R.styleable.FlowLayoutView_text)
                isSelected = ta.getBoolean(R.styleable.FlowLayoutView_isSelected, false)
                mTextSize = ta.getDimension(R.styleable.FlowLayoutView_textSize,mTextSize)
                mTextColor = ta.getColor(R.styleable.FlowLayoutView_textColor, context.resources.getColor(R.color.black)
                )
            } finally {
                recycle()
            }
        }
        initLayout(context)
    }

    constructor(
        context: Context,
        @Nullable attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initLayout(context)
    }

    fun initLayout(context: Context) {
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflate.inflate(R.layout.flowlayout_view, this)
        mTextView = view.findViewById(R.id.item_custom_flowlayout_tv)
        mTextView.text = item_text
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize)
        mTextView.setTextColor(mTextColor!!)
        setItemSelected(isSelected!!)

        view.setOnClickListener {
            clickListener.onClicked(mTextView.text.toString())
        }
    }

    fun setListner(listener: FlowLayoutInterface) {
        clickListener = listener
    }

    fun setItemSelected(status: Boolean) {
        if (status) {
            mTextView.background = context.getDrawable(R.drawable.bg_selected)
            mTextView.setTextColor(context.resources.getColor(R.color.white))
        } else {
            mTextView.background = null
            mTextView.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun onClicked(string: String) {
    }
}