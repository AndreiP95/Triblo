package data.repository

import data.remote.DemoApi
import domain.repository.DemoRepository

class DemoRepositoryImpl(
    private val api : DemoApi
) : DemoRepository {

}