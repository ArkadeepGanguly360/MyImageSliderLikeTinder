package com.development.tinderkotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var manager: CardStackLayoutManager? = null
    private var adapter: CardStackAdapter? = null
    var cardList = ArrayList<CardData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardList.add(
            CardData(
                ContextCompat.getDrawable(this, R.drawable.sample1)!!,
                "arka",
                "21",
                "Jhargram"
            )
        )
        cardList.add(
            CardData(
                ContextCompat.getDrawable(this, R.drawable.sample2)!!,
                "deep",
                "22",
                "Jhargram"
            )
        )
        cardList.add(
            CardData(
                ContextCompat.getDrawable(this, R.drawable.sample3)!!,
                "gora",
                "22",
                "Jhargram"
            )
        )
        cardList.add(
            CardData(
                ContextCompat.getDrawable(this, R.drawable.sample4)!!,
                "abc",
                "21",
                "Jhargram"
            )
        )
        cardList.add(
            CardData(
                ContextCompat.getDrawable(this, R.drawable.sample5)!!,
                "hgf",
                "21",
                "Jhargram"
            )
        )

        manager = CardStackLayoutManager(this, object : CardStackListener {
            override fun onCardDragging(direction: Direction, ratio: Float) {
                Log.d("", "onCardDragging: d=" + direction.name + " ratio=" + ratio)
            }

            override fun onCardSwiped(direction: Direction) {
                Log.d("", "onCardSwiped: p=" + manager!!.topPosition + " d=" + direction)
                if (direction == Direction.Right) {
                    Toast.makeText(this@MainActivity, "Direction Right", Toast.LENGTH_SHORT).show()
                }
                if (direction == Direction.Top) {
                    Toast.makeText(this@MainActivity, "Direction Top", Toast.LENGTH_SHORT).show()
                }
                if (direction == Direction.Left) {
                    Toast.makeText(this@MainActivity, "Direction Left", Toast.LENGTH_SHORT).show()
                }
                if (direction == Direction.Bottom) {
                    Toast.makeText(this@MainActivity, "Direction Bottom", Toast.LENGTH_SHORT).show()
                }

                // Paginating
                if (manager!!.topPosition == adapter!!.itemCount - 5) {
                    //paginate()
                }
            }

            override fun onCardRewound() {
                Log.d("", "onCardRewound: " + manager!!.topPosition)
            }

            override fun onCardCanceled() {
                Log.d("", "onCardRewound: " + manager!!.topPosition)
            }

            override fun onCardAppeared(view: View, position: Int) {
                val tv = view.findViewById<TextView>(R.id.item_name)
                Log.d("", "onCardAppeared: " + position + ", nama: " + tv.text)
            }

            override fun onCardDisappeared(view: View, position: Int) {
                val tv = view.findViewById<TextView>(R.id.item_name)
                Log.d("", "onCardAppeared: " + position + ", nama: " + tv.text)
            }
        })

        manager!!.setStackFrom(StackFrom.None)
        manager!!.setVisibleCount(3)
        manager!!.setTranslationInterval(8.0f)
        manager!!.setScaleInterval(0.95f)
        manager!!.setSwipeThreshold(0.3f)
        manager!!.setMaxDegree(20.0f)
        manager!!.setDirections(Direction.FREEDOM)
        manager!!.setCanScrollHorizontal(true)
        manager!!.setSwipeableMethod(SwipeableMethod.Manual)
        manager!!.setOverlayInterpolator(LinearInterpolator())
        adapter = CardStackAdapter(this, cardList/*addList() as ArrayList<ItemModel>*/)
        card_stack_view.setLayoutManager(manager)
        card_stack_view.setAdapter(adapter)
        card_stack_view.setItemAnimator(DefaultItemAnimator())
    }

  /*  private fun paginate() {
        val old: List<ItemModel> = adapter.getItems()
        val baru: List<ItemModel?> = ArrayList<Any?>(addList())
        val callback = CardStackCallback(old, baru)
        val hasil = DiffUtil.calculateDiff(callback)
        adapter.setItems(baru)
        hasil.dispatchUpdatesTo(adapter!!)
    }*/

}
