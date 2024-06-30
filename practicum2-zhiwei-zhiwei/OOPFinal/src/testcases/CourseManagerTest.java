//package testcases;
//
//
//import dao.CourseDAO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.*;
//
//class CourseManagerTest {
//
//    @Mock
//    private CourseDAO courseDAO;
//
//    @InjectMocks
//    private CourseManager courseManager;
//
//    @BeforeEach
//    void setUp() {
//        // Initialize CourseManager and mocks
//        courseManager = CourseManager.getInstance(); // Ensure this adheres to the singleton pattern
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddCourse() {
//        // Given
//        Course course = new Course("CS101", "Introduction to Computer Science");
//
//        // When
//        courseManager.addCourse(course);
//
//        // Then
//        verify(courseDAO, times(1)).addCourse(course.getCourseNum(), course.getCourseName());
//    }
//
//
//}
//
//
