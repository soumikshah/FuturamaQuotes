package com.soumikshah.futuramaquotes.di

import com.soumikshah.futuramaquotes.model.QuotesService
import com.soumikshah.futuramaquotes.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service:QuotesService)

    fun inject(viewModel: ListViewModel)
}