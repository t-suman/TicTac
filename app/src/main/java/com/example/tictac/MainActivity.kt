package com.example.tictac

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayout
import android.view.View
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var card1:CardView?=null
    private var grid1:GridLayout?=null
    private var card2:CardView?=null
    private var play:Button?=null
    private var message:TextView?=null
    private var image:ImageView?=null
    private var arr= arrayOf(2,2,2,2,2,2,2,2,2)
    private var activePlayer=0
    private var gameIsOn=true

    private var winingPos= arrayOf(arrayOf(1,2,3), arrayOf(4,5,6), arrayOf(7,8,9), arrayOf(1,4,7), arrayOf(2,5,8),
        arrayOf(3,6,9), arrayOf(1,5,9), arrayOf(3,5,7))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        card1=findViewById(R.id.card1)
        card2=findViewById(R.id.card2)
        grid1=findViewById(R.id.grid)
        play=findViewById(R.id.button22)
        message=findViewById(R.id.textView21)

        playAgain(play as View)
    }
    public fun dropIn(view: View){
        if(gameIsOn) {
        image=view as ImageView
        var tag=Integer.parseInt(image?.getTag().toString())

            if (arr[tag - 1] == 2) {
                image?.setTranslationY(-500f)
                arr[tag - 1] = activePlayer
                if (activePlayer == 1) {
                    image?.setImageResource(R.drawable.cross)
                    activePlayer = 0
                } else {
                    image?.setImageResource(R.drawable.zero)
                    activePlayer = 1
                }
                image?.animate()?.translationYBy(500f)?.rotation(720f)?.setDuration(1000)

            }
            if (checkWin()) {
                gameIsOn = false
                if (activePlayer == 0) message?.text = "Congratulations CROSS. You won the game"
                else message?.text = "Congratulations ZERO. You won the game"
                card1?.alpha=0.8f
                var handler :Handler = Handler()
                handler.postDelayed( {
                    fun1()
                }, 2000)
            } else {
                if (checkGameOver()) {
                    gameIsOn = false
                    message?.text = "GAMEOVER - DRAWN"
                    card1?.alpha=0.8f
                    var handler :Handler = Handler()
                    handler.postDelayed( {
                        fun1()
                    }, 2000)
                }
            }
        }
    }
    public fun fun1(){
        card2?.visibility=View.VISIBLE
    }
    public fun playAgain(view:View){
        activePlayer=0
        gameIsOn=true
        card1?.alpha=1f
        card2?.visibility=View.INVISIBLE
        for( i in 0 until 9)arr[i]=2
        for(i in 0 until grid1!!.childCount){
            ((grid1?.getChildAt(i))as ImageView).setImageResource(0)
            ((grid1?.getChildAt(i))as ImageView).animate().rotation(-720f)?.setDuration(0)
        }
    }
    private fun checkWin(): Boolean {
        for(i in 0 until 8)
            if(arr[winingPos[i][0]-1]!=2&&arr[winingPos[i][0]-1]==arr[winingPos[i][1]-1]&&arr[winingPos[i][1]-1]==arr[winingPos[i][2]-1]) {
                return (true)
            }
        return false
    }
    private fun checkGameOver() :Boolean {
        for(i in 0 until 9)if(arr[i]==2)return false
        return true
    }

    }
