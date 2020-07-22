/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.samples.apps.sunflower.databinding.ActivityGardenBinding

class GardenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentView<ActivityGardenBinding>(this, R.layout.activity_garden)
        val tabLayout = binding.tabs


        arrayOf(tabLayout.newTab(), tabLayout.newTab())
            .mapIndexed { position, tab ->
                tab.setIcon(getTabIcon(position))
                tab.text = getTabTitle(position)
                tabLayout.addTab(tab)
            }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                val controller = findNavController(R.id.nav_host)
                controller.navigate(action(tab.position))
            }
        })
    }

    private fun action(position: Int): NavDirections {
        return when(position) {
            0 -> NavHostDirections.actionMyGarden()
            1 -> NavHostDirections.actionPlantsList()
            else -> throw IndexOutOfBoundsException()
        }
    }


    private fun getTabIcon(position: Int): Int {
        return when (position) {
            0 -> R.drawable.garden_tab_selector
            1 -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            0 -> getString(R.string.my_garden_title)
            1 -> getString(R.string.plant_list_title)
            else -> throw IndexOutOfBoundsException()
        }
    }
}