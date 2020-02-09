package com.android.testable.myapplication

class TestApplication :MyApplication() {

    override fun createRepositoryModule(): RepositoryModule {
        return TestRepositoryModule()
    }
}