package com.app.jetpacknfctutorial

import android.app.DialogFragment
import android.app.Fragment
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.BottomSheetScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public object Coroutines {
    //region UI contexts
    fun main(work : suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main.immediate).launch {
            work()
        }
    fun main(activity : ComponentActivity, work : suspend ((scope : CoroutineScope) -> Unit)) =
        activity.lifecycleScope.launch {
            activity.getLifecycle().repeatOnLifecycle(Lifecycle.State.CREATED) {
                work(this)
            }
        }
//    fun main(fragment : Surface, work : suspend ((scope : CoroutineScope) -> Unit)) =
//        fragment.lifecycleScope.launch {
//            fragment.getLifecycle().repeatOnLifecycle(Lifecycle.State.STARTED) {
//                work(this)
//            }
//        }
//    fun main(fragment : DialogFragment, work : suspend ((scope : CoroutineScope) -> Unit)) =
//        fragment.lifecycleScope.launch {
//            fragment.getLifecycle().repeatOnLifecycle(Lifecycle.State.STARTED) {
//                work(this)
//            }
//        }
//    fun main(fragment : ComposeView, work : suspend ((scope : CoroutineScope) -> Unit)) =
//        fragment.lifecycleScope.launch {
//            fragment.getLifecycle().repeatOnLifecycle(Lifecycle.State.STARTED) {
//                work(this)
//            }
//        }
    //endregion
    //region I/O operations
    fun io(work : suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    fun io(viewModel : ViewModel, work : suspend (() -> Unit)) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            work()
        }
    }
    //endregion
    //region Uses heavy CPU computation
    fun default(work : suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Default).launch {
            work()
        }

    fun default(viewModel : ViewModel, work : suspend (() -> Unit)) =
        viewModel.viewModelScope.launch(Dispatchers.Default) {
            work()
        }
    //endregion
    //region No need to run on specific context
    fun unconfined(work : suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Unconfined).launch {
            work()
        }
    //endregion
}