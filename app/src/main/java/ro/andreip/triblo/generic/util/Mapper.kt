package ro.andreip.triblo.generic.util

interface Mapper<T, R> {

    suspend fun map(item: T): R

}