package com.example.exercisedemo

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    var db: UserDatabase = Room.databaseBuilder(
        getApplication(),
        UserDatabase::class.java, "database-user"
    ).build()

    private val userLiveData = MutableLiveData<List<User>>()

    private val userNameLiveData = Transformations.map(userLiveData) {
        it.map { user -> user.firstName + user.lastName }
    }

    val userNames: LiveData<List<String>>
        get() = userNameLiveData

    fun addUser(user: User) {
        CoroutineScope(IO).launch {
            db.userDao().addUser(user)
        }
    }

    fun getUsers() {
        CoroutineScope(IO).launch {
            userLiveData.postValue(db.userDao().getAll())
        }
    }

    class Factory(
        private val application: Application
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}