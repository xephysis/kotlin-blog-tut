package me.xep.blog.repository

import me.xep.blog.domain.Article
import me.xep.blog.domain.User
import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository: CrudRepository<User, String> {
    fun findByLogin(login: String): User?
}