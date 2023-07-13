package com.test.framework.mvvm.di.module

import com.test.framework.mvvm.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}