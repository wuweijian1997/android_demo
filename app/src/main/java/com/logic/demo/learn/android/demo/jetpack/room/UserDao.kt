package com.logic.demo.learn.android.demo.jetpack.room

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User) : Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers() : List<User>

    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age: Int) : List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteUsersByLastName(lastName: String): Int
}