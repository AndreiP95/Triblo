package ro.andreip.triblo.generic.util

interface Mapper<T, R> {

    suspend fun map(from: T): R

}