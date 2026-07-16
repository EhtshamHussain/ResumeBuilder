package com.example.resumebuilder.data.repository
import com.example.resumebuilder.data.local.dao.UserDao
import com.example.resumebuilder.data.local.entity.UserEntity
import com.example.resumebuilder.domain.model.User
import com.example.resumebuilder.domain.repository.AuthRepository
class AuthRepositoryImpl(
    private val userDao: UserDao
) : AuthRepository {
    override suspend fun login(name: String, email: String, password: String): Result<User> {
        return try {
            val userEntity = userDao.login(email, password)
            if (userEntity != null) {
                Result.success(userEntity.toDomain())
            } else {
                Result.failure(Exception("Invalid email or password"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun signup(name: String, email: String, password: String): Result<User> {
        return try {
            if (isEmailExists(email)) {
                return Result.failure(Exception("Email already exists"))
            }
            val userEntity = UserEntity(
                name = name,
                email = email,
                password = password
            )
            userDao.insert(userEntity)
            val savedUser = userDao.getUser(email)
            Result.success(savedUser!!.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun isEmailExists(email: String): Boolean {
        return userDao.getUser(email) != null
    }
}
fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email
    )
}