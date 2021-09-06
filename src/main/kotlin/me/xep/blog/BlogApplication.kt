package me.xep.blog

import me.xep.blog.properties.BlogProperties
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
//이거 등록 안해주면 어떻게 되는거였더라
//https://github.com/spring-projects/spring-boot/issues/18674
@EnableConfigurationProperties(BlogProperties::class, BlogProperties::class)
class BlogApplication

fun main(args: Array<String>) {
	runApplication<BlogApplication>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}
}
