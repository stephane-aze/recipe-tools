package com.master.recipestools.iterator

interface Iterator<T> {
    fun hasNext(): Boolean
    fun next()
    fun current(): T
}

class History() {

    private val urls = mutableListOf<String>()
    //private val urls = arrayOfNulls<String?>(10)

    fun push(url: String) {
        urls.add(url)
    }

    fun pop(): String {
        return urls.removeLast()
    }

    fun getIterator(): Iterator<String> = ListIterator(urls)
}

class ListIterator(private val list: List<String>) : Iterator<String> {
    var index = 0

    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun current(): String {
        return list[index]
    }

    override fun next() {
        index++
    }
}
