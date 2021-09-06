package me.xep.blog.config

import me.xep.blog.domain.Article
import me.xep.blog.domain.User
import me.xep.blog.repository.ArticleRepository
import me.xep.blog.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class BlogConfiguration {

    //TODO 이건 좀 신기한데? 리턴타입이 왜 application runner 인거지
    @Bean
    fun databaseInitializer(
            userRepository: UserRepository,
            articleRepository: ArticleRepository
    ) = ApplicationRunner {
        val xep = userRepository.save(User("xep", "name", "oh"))
        articleRepository.save(
                Article(
                        title = "Reactor Bismuth is out",
                        headline = "Lorem ipsum",
                        content = "dolor sit amet",
                        author = xep,
                        addedAt = LocalDateTime.of(2021,9,1,0,0)
                )
        )
        articleRepository.save(
                Article(
                        title = "Reactor Aluminium has landed",
                        headline = "Lorem ipsum",
                        content = "dolor sit amet",
                        author = xep,
                        addedAt = LocalDateTime.of(2021,9,2,0,0)
                )
        )
    }

}