package io.mockk.gh

import io.mockk.Runs
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify

class Issue265Test {

    val exampleRepository = mockk<ExampleRepository>()

    val exampleService = ExampleService(exampleRepository)

    val ldt = LocalDateTime.of(2019, 3, 1, 0,0)

    @Test
    fun exampleSuccess() {
        every { exampleRepository.insert(User(1, "expect", ldt, ldt)) } just Runs

        exampleService.save(User(1, "expect", ldt, ldt))

        verify { exampleRepository.insert(User(1, "expect", ldt, ldt)) }
    }
//
//    @Test
//    fun exampleError() {
//        every { exampleRepository.insert(User(1, "expect", any(), any())) } just Runs
//
//        exampleService.save(User(1, "expect", ldt, ldt))
//
//        verify { exampleRepository.insert(User(1, "expect", any(), any())) }
//    }

    @Test
    fun exampleError2() {
        every { exampleRepository.insert(User(1, "expect", ldt, any())) } just Runs

        exampleService.save(User(1, "expect", ldt, ldt))

        verify { exampleRepository.insert(User(1, "expect", ldt, any())) }
    }
}

data class User(val id: Long, val name: String, val createTimestamp: LocalDateTime, val updateTimestamp: LocalDateTime)

class ExampleRepository {
    fun insert(user: User) {

    }
}

class ExampleService(val exampleRepository: ExampleRepository) {

    fun save(user: User) {
        exampleRepository.insert(user)
    }
}