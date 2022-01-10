package com.lowlevel.librarysheet.service.impl

import com.lowlevel.librarysheet.database.repository.impl.UserRepositoryImpl
import com.lowlevel.librarysheet.model.User
import com.lowlevel.librarysheet.service.UserService
import tornadofx.Controller

class UserServiceImpl : UserService, Controller() {

    private val userRepository = UserRepositoryImpl()

    override fun signUp(login: String, password: String): String {
        if (userRepository.findByLogin(login))  {
            println("User already exists")
            return "Пользователь уже существует"
        }
        userRepository.insert(login, password)
        println("Signed up successfully")
        return "Вы успешно зарегистрировались"
    }

    override fun logIn(login: String, password: String): String {
        if (!userRepository.findByLogin(login)) {
            println("User doesn't exist")
            return "Данного пользователя не существует"
        }
        if (password != userRepository.findPasswordByLogin(login)) {
            println("Wrong password")
            return "Неверный пароль"
        }
        User.login(login)
        User.password(password)
        println("Logged in successfully")
        return "Success"
    }

}