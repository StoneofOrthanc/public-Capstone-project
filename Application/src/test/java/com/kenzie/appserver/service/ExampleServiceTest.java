//package com.kenzie.appserver.service;
//
//import com.kenzie.appserver.repositories.ExampleRepository;
//import org.junit.jupiter.api.BeforeEach;
//
//import static org.mockito.Mockito.mock;
//
//public class ExampleServiceTest {
//    private ExampleRepository exampleRepository;
//    private ExampleService exampleService;
//    private HireServiceClient hireServiceClient;
//
//    @BeforeEach
//    void setup() {
//        exampleRepository = mock(ExampleRepository.class);
//        hireServiceClient = mock(HireServiceClient.class);
//        exampleService = new ExampleService(exampleRepository, hireServiceClient);
//    }
//    /** ------------------------------------------------------------------------
//     *  exampleService.findById
//     *  ------------------------------------------------------------------------ **/
//
////    @Test
////    void findById() {
////        // GIVEN
////        String id = randomUUID().toString();
////
////        ExampleRecord record = new ExampleRecord();
////        record.setId(id);
////        record.setName("concertname");
////
////        // WHEN
////        when(exampleRepository.findById(id)).thenReturn(Optional.of(record));
////        Example example = exampleService.findById(id);
////
////        // THEN
////        Assertions.assertNotNull(example, "The object is returned");
////        Assertions.assertEquals(record.getId(), example.getId(), "The id matches");
////        Assertions.assertEquals(record.getName(), example.getName(), "The name matches");
////    }
////
////    @Test
////    void findByConcertId_invalid() {
////        // GIVEN
////        String id = randomUUID().toString();
////
////        when(exampleRepository.findById(id)).thenReturn(Optional.empty());
////
////        // WHEN
////        Example example = exampleService.findById(id);
////
////        // THEN
////        Assertions.assertNull(example, "The example is null when not found");
////    }
//
//}
