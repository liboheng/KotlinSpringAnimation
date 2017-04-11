package com.example.fq.myspringanimation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import java.util.ArrayList

class MainActivity : Activity() {
    private var mLettersLayout: LinearLayout? = null
    private var mLetterAnims: ArrayList<SpringAnimation>? = null
    private var mDescTitleTextView: TextView? = null
    private var mSignInLayout: LinearLayout? = null
    private var mSignUpBtn: Button? = null
    private var mLeftLogoImg: ImageView? = null
    private var mRightLogoImg: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get the screen height.
        val dm = resources.displayMetrics
        val screenHeight = dm.heightPixels
        // letters about 'Converse'
        mLettersLayout = findViewById(R.id.letter_layout) as LinearLayout
        mLetterAnims = ArrayList<SpringAnimation>()
        for (i in 0..mLettersLayout!!.childCount - 1) {
            val letterView = mLettersLayout!!.getChildAt(i)
            letterView.translationY = screenHeight.toFloat()
            val letterAnimY = SpringAnimation(letterView, SpringAnimation.TRANSLATION_Y, 0f)
            letterAnimY.spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
            letterAnimY.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
            mLetterAnims!!.add(letterAnimY)
        }

        // text about 'Native messaging'
        mDescTitleTextView = findViewById(R.id.desc_title_textview) as TextView
        mDescTitleTextView!!.translationY = 500f
        mDescTitleTextView!!.alpha = 0f
        val descTitleAnimY = SpringAnimation(mDescTitleTextView, DynamicAnimation.TRANSLATION_Y, 0f)
        descTitleAnimY.spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
        descTitleAnimY.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY

        val descTitleAlphaAnim = ObjectAnimator.ofFloat(0f, 1f)
        descTitleAlphaAnim.duration = 300
        descTitleAlphaAnim.addUpdateListener { valueAnimator -> mDescTitleTextView!!.alpha = valueAnimator.animatedValue as Float }

        // the button of sign up
        mSignUpBtn = findViewById(R.id.sign_up_btn) as Button
        mSignUpBtn!!.translationY = 500f
        val signUpBtnAnimY = SpringAnimation(mSignUpBtn, SpringAnimation.TRANSLATION_Y, 0f)
        signUpBtnAnimY.spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
        signUpBtnAnimY.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
        // the bottom text about 'Have an account? sign in'
        mSignInLayout = findViewById(R.id.signin_layout) as LinearLayout
        mSignInLayout!!.translationY = 500f
        val signInLayoutAnimY = SpringAnimation(mSignInLayout, SpringAnimation.TRANSLATION_Y, 0f)
        signInLayoutAnimY.spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
        signInLayoutAnimY.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
        // top logo by left
        mLeftLogoImg = findViewById(R.id.left_logo_imageview) as ImageView
        mLeftLogoImg!!.translationY = 400f
        mLeftLogoImg!!.alpha = 0f
        val leftLogoAnimY = SpringAnimation(mLeftLogoImg, SpringAnimation.TRANSLATION_Y, 0f)
        leftLogoAnimY.spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
        leftLogoAnimY.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
        leftLogoAnimY.setStartVelocity(-2000f)
        // top logo by right
        mRightLogoImg = findViewById(R.id.right_logo_imageview) as ImageView
        mRightLogoImg!!.translationY = 400f
        mRightLogoImg!!.alpha = 0f
        val rightLogoAnimY = SpringAnimation(mRightLogoImg, SpringAnimation.TRANSLATION_Y, 0f)
        rightLogoAnimY.spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
        rightLogoAnimY.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
        rightLogoAnimY.setStartVelocity(-2000f)

        val logoAlphaAnim = ObjectAnimator.ofFloat(0f, 1f)
        logoAlphaAnim.duration = 600
        logoAlphaAnim.addUpdateListener { valueAnimator ->
            mLeftLogoImg!!.alpha = valueAnimator.animatedValue as Float
            mRightLogoImg!!.alpha = valueAnimator.animatedValue as Float
        }
        mRightLogoImg!!.postDelayed({
            leftLogoAnimY.start()
            mRightLogoImg!!.postDelayed({ rightLogoAnimY.start() }, 150)
            mDescTitleTextView!!.postDelayed({
                descTitleAlphaAnim.startDelay = 100
                descTitleAlphaAnim.start()
                descTitleAnimY.start()
                signUpBtnAnimY.start()
                signInLayoutAnimY.start()
            }, 300)
            for (letterAnim in mLetterAnims!!) {
                mLettersLayout!!.postDelayed({ letterAnim.start() }, (12 * mLetterAnims!!.indexOf(letterAnim)).toLong())
            }
            logoAlphaAnim.start()
        }, 1000)
    }

}
