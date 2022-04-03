package com.lm.bot.di.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lm.bot.presentation.BotViewModel
import com.lm.bot.presentation.BotViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.reflect.KClass


@ExperimentalCoroutinesApi
@Module(includes = [ViewModelModules::class])
interface ViewModelFactoryModule {

    @Binds
    fun bindsViewModelFactory(factory: BotViewModelFactory): ViewModelProvider.Factory
}

@ExperimentalCoroutinesApi
@Module
interface ViewModelModules {
    @IntoMap
    @Binds
    @ViewModelKey(BotViewModel::class)
    fun bindsBotViewModel(viewModel: BotViewModel): ViewModel
}



@MapKey
@Target(AnnotationTarget.FUNCTION)
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
