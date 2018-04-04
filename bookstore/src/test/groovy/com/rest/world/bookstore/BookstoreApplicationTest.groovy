package com.rest.world.bookstore

import com.rest.world.bookstore.models.Book
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.autoconfigure.web.server.LocalManagementPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

import static java.util.Collections.emptyList
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = ["management.port=0"])

class BookstoreApplicationTest {
    @Autowired
    TestRestTemplate restTemplate

    @LocalServerPort
    private int serverPort;

    @LocalManagementPort
    private int adminPort;

    def getEntity(String uri, ParameterizedTypeReference<Collection> responseType) {
        def entity = restTemplate.exchange(uri, GET, null, responseType)
        assertEquals(entity.getStatusCode(), OK)
        return entity.getBody()
    }

    @Test
    void "should return empty books collection"() {
        def entity = this.getEntity("http://localhost:${serverPort}/api/books", new ParameterizedTypeReference<Collection<Book>>() {
        })
        assertEquals(entity, emptyList())
    }

    @Test
    void "should persist the book and return same with book id"() {
        def request = ["name"    : "Spring Boot",
                       "author"  : "Batman",
                       "category": "TECHNOLOGY"]

        def response = restTemplate.postForEntity("http://localhost:${serverPort}/api/books", request, Map.class)
        assertEquals(response.getStatusCode(), CREATED)

        def book = response.getBody()
        request.each { assertEquals("Verifying ${it.key}", it.value, book.get(it.key)) }
        assertNotNull(book.oid)

        def books = this.getEntity("http://localhost:${serverPort}/api/books", new ParameterizedTypeReference<Collection<Book>>() {
        })
        assertEquals(books.size(), 1)
    }

    @Test
    void "health check should reveal UP status"() {
        def response = restTemplate.getForEntity("http://localhost:${adminPort}/admin/health", Map.class)
        assertEquals(OK, response.getStatusCode())
        assertEquals('UP', response.getBody().status)
    }
}
