package com.soumikshah.futuramaquotes.di

import com.soumikshah.futuramaquotes.model.CharacterService
import com.soumikshah.futuramaquotes.model.QuotesService
import com.soumikshah.futuramaquotes.model.SearchQuoteService
import com.soumikshah.futuramaquotes.viewmodel.CharacterViewModel
import com.soumikshah.futuramaquotes.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service:QuotesService)

    fun inject(viewModel: ListViewModel)

    fun inject(service:CharacterService)

    fun inject(characterViewModel: CharacterViewModel)

    fun inject(service:SearchQuoteService)
}