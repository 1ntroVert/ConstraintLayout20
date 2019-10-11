package com.example.constraintlayout20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_main_06.*
import kotlinx.android.synthetic.main.activity_main_06.title as film_title
import android.text.method.ScrollingMovementMethod

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_06)

        description.movementMethod = ScrollingMovementMethod()
        film_title.setOnClickListener {
            if (motion_layout.currentState == R.id.film_01_details || motion_layout.currentState == R.id.film_02_details) return@setOnClickListener

            val endState = if (motion_layout.currentState == R.id.film_01_preview) R.id.film_01_details else R.id.film_02_details
            motion_layout.setTransition(motion_layout.currentState, endState)
            motion_layout.transitionToEnd()
        }
    }

    override fun onBackPressed() {
        if (motion_layout.currentState == R.id.film_01_details || motion_layout.currentState == R.id.film_02_details) {
            motion_layout.transitionToStart()

            motion_layout.setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {
                }

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                }

                override fun onTransitionChange(p0: MotionLayout?, startId: Int, endId: Int, progress: Float) {

                }

                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                    if (currentId == R.id.film_01_preview || motion_layout.currentState == R.id.film_02_preview) {
                        motion_layout.setTransitionListener(null)
                        motion_layout.setTransition(
                            motion_layout.currentState,
                            if (motion_layout.currentState == R.id.film_01_preview) R.id.film_02_preview else R.id.film_01_preview
                        )
                    }
                }
            })
        } else {
            super.onBackPressed()
        }
    }
}
