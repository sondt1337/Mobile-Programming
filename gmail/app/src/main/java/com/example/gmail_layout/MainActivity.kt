package com.example.gmail_layout

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var lv: ListView

    private val gmailTitle = listOf("EduOnline.com", "Alex Johnson", "LearnPro.com", "Customer Support", "John from DevCo")

    private val gmailTime = listOf("3:15 PM", "1:40 PM", "11:55 AM", "10:05 AM", "8:45 AM")

    private val gmailText = listOf(
        "Only $25 for early bird - Limited spots...\nWant to master Web Development? Start...",
        "Your feedback is invaluable!\nTell us how we can improve. No images...",
        "Free courses and new updates...\nPhotoshop, SEO, JavaScript, Web Dev...",
        "Order confirmation - services update...\nCompany XYZ - https://www.xyz.com 3 Maple St...",
        "Introducing the New DevCreator!\nAll-new features to design and deploy..."
    )

    private val myList: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        lv = findViewById(R.id.lv)

        val random = Random()
        for (i in gmailTitle.indices) {
            val avatar = gmailTitle[i].first().uppercase()
            val colorAvatar = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setSize(50, 50)
                setColor(colorAvatar)
            }
            myList.add(User(avatar, gmailTitle[i], gmailTime[i],gmailText[i], isStarred = false, drawable))
        }

        val adapter = GmailAdapter(this, R.layout.gmail_item, myList)
        lv.adapter = adapter

    }
}