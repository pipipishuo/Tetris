package com.example.demo2

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

class StateObserver: DefaultLifecycleObserver {
    override fun onPause(owner: LifecycleOwner) {
        Game.setPause(true)
    }
}