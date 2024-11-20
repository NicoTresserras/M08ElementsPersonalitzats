package com.example.sharedpreferences

import android.content.Context
import android.graphics.PorterDuff
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import java.util.regex.Pattern

class EmailValidatorView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs),
    TextWatcher {

    var successColor: Int
    var errorColor: Int

    lateinit var tvErrorCode:TextView
    lateinit var etMail:EditText
    lateinit var CustomView:View
    init {
        CustomView = inflate(context, R.layout.email_validator, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MailValidator)
        tvErrorCode = CustomView.findViewById<TextView>(R.id.tvErrorCode)
        tvErrorCode.text = attributes.getString(R.styleable.MailValidator_textError)
        errorColor = attributes.getColor(R.styleable.MailValidator_underlineErrorColor, ContextCompat.getColor(context, R.color.red))
        successColor = attributes.getColor(R.styleable.MailValidator_underlineSuccessColor, ContextCompat.getColor(context, R.color.green))
        attributes.recycle()

        etMail = CustomView.findViewById<EditText>(R.id.etMail)
        etMail.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        TODO("Not yet implemented")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val pattern = Pattern.compile("(/^[0-9]+([.][0-9]+)?$/)")
        val matcher = pattern.matcher(s.toString())
        val valid = matcher.matches()
        if (valid) {
            tvErrorCode.visibility = View.INVISIBLE
            etMail.background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(ContextCompat.getColor(context, R.color.green), BlendModeCompat.SRC_OVER)
        } else {
            tvErrorCode.visibility = View.VISIBLE
            etMail.background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(ContextCompat.getColor(context, R.color.red"(/^[0-9]+([.][0-9]+)?$/)"), BlendModeCompat.SRC_OVER)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        TODO("Not yet implemented")
    }
}