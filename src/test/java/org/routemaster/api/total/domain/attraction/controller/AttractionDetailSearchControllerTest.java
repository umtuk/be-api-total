package org.routemaster.api.total.domain.attraction.controller;

import org.junit.jupiter.api.Test;
import org.routemaster.api.total.domain.attraction.data.detail.CultureAttractionDetailVO;
import org.routemaster.api.total.domain.attraction.data.detail.TourAttractionDetailVO;
import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
import org.routemaster.api.total.domain.attraction.service.AttractionDetailSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AttractionDetailSearchControllerTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private AttractionDetailSearchService service;

    @Test
    public void testCommonDetailSearch() {
        Integer testContentId = 126508;
        StepVerifier.create(
                client.get().uri("/attraction/detail/common?contentId=" + testContentId)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(AttractionSearchVO.class)
                        .getResponseBody()

        ).assertNext(attractionSearchVO -> {
            assertNotNull(attractionSearchVO);
            assertEquals("0000", attractionSearchVO.getResultCode());
            assertEquals("OK", attractionSearchVO.getResultMessage());
            assertEquals(1, attractionSearchVO.getNumOfRows());
            assertEquals(1, attractionSearchVO.getPageNo());
            assertEquals(1, attractionSearchVO.getTotalCount());
            attractionSearchVO.getAttractions().forEach(detail -> {
                assertNotNull(detail);
                assertEquals(testContentId, detail.getContentId());
            });
        }).verifyComplete();
    }

    @Test
    public void testTourAttractionDetailSearch() {
        Integer testContentId = 1949905;
        StepVerifier.create(
                client.get().uri("/attraction/detail/tour?contentId=" + testContentId)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(TourAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionSearchVO -> {
            assertNotNull(attractionSearchVO);
            assertEquals("0000", attractionSearchVO.getResultCode());
            assertEquals("OK", attractionSearchVO.getResultMessage());
            assertEquals(1, attractionSearchVO.getNumOfRows());
            assertEquals(1, attractionSearchVO.getPageNo());
            assertEquals(1, attractionSearchVO.getTotalCount());
            assertNotNull(attractionSearchVO.getDetail());
        }).verifyComplete();
    }

    @Test
    public void testCultureAttractionDetailSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/detail/culture?contentId=2714751")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(CultureAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionDetailVO -> {
            assertNotNull(attractionDetailVO);
            assertEquals("0000", attractionDetailVO.getResultCode());
            assertEquals("OK", attractionDetailVO.getResultMessage());
            assertEquals(1, attractionDetailVO.getNumOfRows());
            assertEquals(1, attractionDetailVO.getPageNo());
            assertEquals(1, attractionDetailVO.getTotalCount());
            assertNotNull(attractionDetailVO.getDetail());
        }).verifyComplete();
    }

}
