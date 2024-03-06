package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFoods: RecyclerView
    private val list = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFoods = findViewById(R.id.rv_foods)
        rvFoods.setHasFixedSize(true)

        list.addAll(getListFoods())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvFoods.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvFoods.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListFoods(): ArrayList<Food> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listFood = ArrayList<Food>()
        for (i in dataName.indices) {
            val hero = Food(dataName[i], dataDescription[i], dataPhoto[i])
            listFood.add(hero)
        }
        return listFood
    }

    private fun showRecyclerList() {
//        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            rvFoods.layoutManager = GridLayoutManager(this, 2)
//        } else {
//            rvFoods.layoutManager = LinearLayoutManager(this)
//        }
        rvFoods.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListFoodAdapter(list)
        rvFoods.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Food) {
                showSelectedFood(data)
            }
        })
    }

    private fun showSelectedFood(food: Food) {
        Toast.makeText(this, "Anda memilih " + food.name, Toast.LENGTH_SHORT).show()
    }
}