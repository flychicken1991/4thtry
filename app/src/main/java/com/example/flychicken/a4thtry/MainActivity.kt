package com.example.flychicken.a4thtry

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.jpeng.jptabbar.anno.NorIcons
import com.jpeng.jptabbar.anno.SeleIcons
import com.jpeng.jptabbar.anno.Titles



class MainActivity : AppCompatActivity() {
    @Titles
    private val mTitles = arrayOf("页面一", "页面二", "页面三", "页面四")

    @SeleIcons
    private val mSeleIcons = intArrayOf(R.drawable.tab1_selected, R.drawable.tab2_selected, R.drawable.tab3_selected, R.drawable.tab4_selected)

    @NorIcons
    private val mNormalIcons = intArrayOf(R.drawable.tab1_normal, R.drawable.tab2_normal, R.drawable.tab3_normal, R.drawable.tab4_normal)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_need.setOnClickListener{
            val intent = Intent (this,workhome::class.java)
            startActivity(intent)
        }
    }
}
