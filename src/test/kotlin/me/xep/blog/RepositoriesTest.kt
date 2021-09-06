package me.xep.blog

import me.xep.blog.domain.Article
import me.xep.blog.domain.User
import me.xep.blog.repository.ArticleRepository
import me.xep.blog.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
class RepositoriesTest(@Autowired val entityManager: TestEntityManager,
                       @Autowired val userRepository: UserRepository,
                       @Autowired val articleRepository: ArticleRepository) {

    @BeforeAll
    fun setup() {
        println(">>Setup")
    }


    @Test
    fun `When findById then return Article`() {
        val xep = User("xep","name","oh")
        entityManager.persist(xep)

        val article = Article("title", "headline", "content", xep)
        entityManager.persist(article)

        entityManager.flush()

        val found = articleRepository.findById(article.id!!)

        assertThat(found.isPresent).isTrue()
        assertThat(found.get()).isEqualTo(article)
    }

    @Test
    fun `When findById then return User`() {
        val xep = User("xep","name","oh")
        entityManager.persist(xep)
        entityManager.flush()

        val found = userRepository.findById(xep.login)

        assertThat(found.isPresent).isTrue()
        assertThat(found.get()).isEqualTo(xep)
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println(">> TODO")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}